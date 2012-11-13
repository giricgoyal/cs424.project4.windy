package db;

import java.util.ArrayList;

import processing.core.PApplet;

import types.DataPid;
import types.DataPos;

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
	
	public ArrayList<DataPos> getDataPosByDateAndWord(int date, String word) {
		String filters = getFilterDate(date) + " and "+getFilterWord(word);
		return db.getDataPos(filters);
	}
	
	public ArrayList<DataPid> getDataPidByDateAndWord(int date, String word) {
		String filters = getFilterDate(date) + " and "+getFilterWord(word);
		return db.getDataPid(filters);
	}
	
	public ArrayList<DataPos> getDataPosByDataAndPid(int date, int pid) {
		String filters = getFilterDate(date) + getFilterPid(pid);
		return db.getDataPos(filters);
	}
	
	public ArrayList<DataPos> getDataPosByDateAndTimeRangeAndWord(int date, int hour1, int hour2, String word) {
		String filters = getFilterDate(date) + " and "+getFilterTimeRange(hour1,hour2)+" and "+getFilterWord(word);
		return db.getDataPos(filters);
	}

	//Filters
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
