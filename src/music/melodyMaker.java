package music;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import models.Repository;
import repositories.SVNActivityCollector;

public class melodyMaker {
	static void getData(String uri){
		Repository repo = new Repository();
		repo.uri=uri;
		repo.type="SVN";
		SVNActivityCollector actv = new SVNActivityCollector();
		repo.commits =  (ArrayList<Map<String, Object>>) actv.process(repo);
		System.out.println(repo.commits);
		analyzeCommits(repo.commits);
		
	}
	

	private static void analyzeCommits(ArrayList<Map<String, Object>> commits){
		int count = commits.size();
		DateTimeFormatter parser = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss");
		String d = commits.get(0).get("date").toString();
		DateTime firstDate = parser.parseDateTime(d);
		d = commits.get(commits.size()-1).get("date").toString();
		DateTime lastDate = parser.parseDateTime(d);
		System.out.println(firstDate.toString());
		System.out.println(lastDate.toString());

		
	}
	public static void main(String[] args) {
		String uri="http://dmo-compact.googlecode.com/svn/trunk";
		getData(uri);
	}
}
