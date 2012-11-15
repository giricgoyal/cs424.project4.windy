package db;

import java.util.ArrayList;

import processing.core.PApplet;

import types.*;

/**
 * Call database queries from here
 * @author Shi
 *
 */
public class QueryManager {
	DatabaseManager db;
	
	public QueryManager(PApplet context){
		db = new DatabaseManager(context);
	}
	
	public ArrayList<DataPos> getDataPos_By_Date_Word(int date, String word) {
		String filters = getFilterDate(date) + " and "+getFilterWord(word);
		return db.getDataPos(filters);
	}
	
	public ArrayList<DataPid> getDataPid_By_Date_Word(int date, String word) {
		String filters = getFilterDate(date) + " and "+getFilterWord(word);
		return db.getDataPid(filters);
	}
	
	public ArrayList<DataPos> getDataPos_By_Data_Pid(int date, int pid) {
		String filters = getFilterDate(date) + getFilterPid(pid);
		return db.getDataPos(filters);
	}
	
	public ArrayList<DataPos> getDataPos_By_Date_TimeRange_Word(int date, int hour1, int hour2, String word) {
		String filters = getFilterDate(date) + " and "+getFilterTimeRange(hour1,hour2)+" and "+getFilterWord(word);
		return db.getDataPos(filters);
	}
	
	public String[] getAllText_By_Date_TimeRange_Word(int date, int hour1, int hour2, String word) {
		String filters = getFilterDate(date) + " and "+getFilterTimeRange(hour1,hour2)+" and "+getFilterWord(word);
		return db.getAllText(filters);
	}
	
	public String[] getAllText_By_Date_TimeRange(int date, int hour1, int hour2) {
		String filters = getFilterDate(date) + " and "+getFilterTimeRange(hour1,hour2);
		return db.getAllText(filters);
	}
	
	public DataWeather[] getAllWeather() {
		return db.getAllWeather();
	}

	//get Filters
	private String getFilterDate(int date) {
		return "day = "+date;
	}
	
	private String getFilterWord(String word) {
		return "text like '%"+word+"%'";
	}
	
	private String getFilterPid(int pid) {
		return "and pid = "+pid;
	}
	
	private String getFilterTimeRange(int hour1, int hour2) {
		return "hour >= "+hour1+" and hour < "+hour2;
	}
}
