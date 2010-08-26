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
	Logger logger = Logger.getLogger(SVNActivityCollector.class);
	
	static {
		DAVRepositoryFactory.setup();
	}
	
	public void process(Repository repo) {

		try {
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repo.uri));
			logger.info("Processing " + repo.uri);

			repository.log(null, 1, 0L, true, false, new MyISVNLogEntryHandler(repo));
		} catch(SVNException e) {
			logger.error("Error at svn process", e);
		}
	}
	
	class MyISVNLogEntryHandler implements ISVNLogEntryHandler {

		private Repository repository;
		
		public MyISVNLogEntryHandler(Repository repository) {
			this.repository = repository;
		}
		
		public void handleLogEntry(SVNLogEntry logMessage) throws SVNException {
			String author = logMessage.getAuthor();
			if(author == null) return;
			
			Date date = logMessage.getDate();
			String revision = Long.toString(logMessage.getRevision());
			
			
			Map<Object, Object> commit = new HashMap();
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

			//ADD THE COMMIT TO THE REPO
			commit.put(""+revision,info);
		}
	}
}
