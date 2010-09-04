package repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Repository;

import org.apache.log4j.Logger;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;





public class SVNActivityCollector {
//	Logger logger = Logger.getLogger(SVNActivityCollector.class);
	private ArrayList<Map<String, Object>> commitsData;
	
	static {
		DAVRepositoryFactory.setup();
	}
	
	public List<Map<String,Object>> process(Repository repo) {

		try {
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repo.uri));
			long rev = repository.getLatestRevision();
//			logger.info("Processing " + repo.uri);
			commitsData = new ArrayList<Map<String,Object>>();
			repository.log(null, 0,rev, true, false, new MyISVNLogEntryHandler(repo,commitsData));
			return commitsData;
		} catch(SVNException e) {
//			logger.error("Error at svn process", e);
		}
		return null;
	}
	
	class MyISVNLogEntryHandler implements ISVNLogEntryHandler {

		private Repository repository;
		private List<Map<String,Object>> commits;

		public MyISVNLogEntryHandler(Repository repository, List<Map<String, Object>> commitsData) {
			this.repository = repository;
			this.commits = commitsData;
		}
		
		public void handleLogEntry(SVNLogEntry logMessage) throws SVNException {
			String author = logMessage.getAuthor();
			
			Date date = logMessage.getDate();
			String revision = Long.toString(logMessage.getRevision());
			
			
			Map<String,Object> info = new HashMap();
			info.put("date", date.toString());
			info.put("message", logMessage.getMessage());
			info.put("author", author);
			List<String> added = new ArrayList<String>();
			List<String> modified = new ArrayList<String>();
			List<String> deleted = new ArrayList<String>();
			List<String> replaced = new ArrayList<String>();

			Map<String, SVNLogEntryPath> changedPaths = logMessage.getChangedPaths();
			for (Map.Entry<String, SVNLogEntryPath> entries : changedPaths.entrySet()) {
				SVNLogEntryPath value = entries.getValue();
				String type = value.getType()+"";
				String path = entries.getKey();
				
				if ("A".equals(type)) {
					added.add(path);
				} else if ("M".equals(type)) {
					modified.add(path);
				} else if ("D".equals(type)) {
					deleted.add(path);
				} else if ("R".equals(type)) {
					replaced.add(type);
				}
			}
			info.put("added", added);
			info.put("modified", modified);
			info.put("deleted", deleted);
			info.put("replaced", replaced);
			info.put("revision", revision);
			//ADD THE COMMIT TO THE REPO
			commits.add(info);
		}
	}
	
	public static void main(String[] args) {
		SVNActivityCollector act = new SVNActivityCollector();
		Repository repo = new Repository();
		repo.type="SVN";
		repo.uri="http://dmo-compact.googlecode.com/svn/trunk";
		act.process(repo);
	}
}
