package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository {
	public static String type;
	public static String uri;
	public static long revision;
	public static ArrayList<Map<String, Object>> commits;
	public Repository(){
		commits= new ArrayList<Map<String,Object>>();
		revision=0;
	}
}
