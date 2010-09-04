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





public class GitActivityCollector {
//	Logger logger = Logger.getLogger(SVNActivityCollector.class);
	
	public void process(Repository repo) {

		
	}
	
	class GitLogEntryHandler {

		
	}
	
	public static void main(String[] args) {
		GitActivityCollector act = new GitActivityCollector();
		Repository repo = new Repository();
		repo.type="SVN";
		repo.uri="http://dmo-compact.googlecode.com/svn/trunk";
		act.process(repo);
	}
}
