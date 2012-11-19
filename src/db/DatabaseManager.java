package db;
import java.util.ArrayList;

import processing.core.PApplet;
import types.*;
import de.bezier.data.sql.MySQL;

public class DatabaseManager {
	private MySQL msql;

	public DatabaseManager(PApplet context) {
		String user = "windy";
		String pass = "windywindy";
		String database = "p4";
		String localhost = "project4dbinstance.chestadraypc.us-east-1.rds.amazonaws.com";
		msql = new MySQL(context, localhost, database, user, pass);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Retreive 
	 * 
	 * @return
	 */
	public ArrayList<DataPos> getDataPos(String filters) {
		ArrayList<DataPos> array  = new ArrayList<DataPos>();
		String query;
		if (msql.connect()) {
			if (filters.length() > 0) {
				query = "select pid, day, hour, min, lat, lon, text from microblog where "+
						filters;
			}
			else {
				query = "select pid, day, hour, min, lat, lon, text from microblog";
			}
			System.out.println(query);
			msql.query(query);
			while (msql.next()) {
				array.add(new DataPos(msql.getInt("pid"), msql.getInt("day"), msql.getInt("hour"), msql.getInt("min"),msql.getFloat("lat"),msql.getFloat("lon"), msql.getString("text")));
			}
			msql.close();
			System.out.println("done!");
		}
		return array;
	}
	public ArrayList<DataPid> getDataPid(String filters) {
		ArrayList<DataPid> array = new ArrayList<DataPid>();
		String query;
		if (msql.connect()) {
			query = "select pid from microblog where "+
					filters;
			System.out.println(query);
			msql.query(query);
			while (msql.next()) {
				array.add(new DataPid(msql.getInt("pid")));
			}
			msql.close();
			System.out.println("done!");
		}
		return array;
	}
	
	public ArrayList<DataLocation> getAllLocation() {
		ArrayList<DataLocation> array = new ArrayList<DataLocation>();
		String query;
		if (msql.connect()) {
			query = "select, id, name, category, cid, parentId from loactions";
			System.out.println(query);
			while(msql.next()){
				array.add(new DataLocation(msql.getInt("id"), msql.getString("name"), msql.getString("category"), msql.getInt("cid"), msql.getInt("parentId")));
			}
			msql.close();
			System.out.println("done locations!!");
		}
		return array;
	}
	
	
	public String[] getAllText(String filters) {
		String str = "";
		ArrayList<String> array = new ArrayList<String>();
		String query;
		if (msql.connect()) {
			query = "select text from microblog where "+filters;
			System.out.println(query);
			msql.query(query);
			while (msql.next()) {
				array.add(new String(msql.getString("text")));
			}
			for (String arr : array) {
				str = str+arr+" ";
			}
			String reg = "[,\\.\\s;!?]+";
			String[] temp = str.split(reg); // temp contains all words
			msql.close();
			for (int i=0;i<temp.length;i++) {
				temp[i] = temp[i].toLowerCase();
			}
			System.out.println("done!");
			return temp;
		}
		return null;
	}
	
	public DataWeather[] getAllWeather() {
		DataWeather[] array = new DataWeather[21];
		String query;
		if (msql.connect()) {
			query = "select date, weather, wspeed, direction from weather order by date";
			System.out.println(query);
			msql.query(query);
			int i=0;
			while (msql.next()) {
				array[i] = new DataWeather(msql.getString("weather"), msql.getString("wspeed"), msql.getString("direction"));
				i++;
			}
			msql.close();
			System.out.println("done!");
			return array;
		}
		return null;
	}
	
	
	public DataCountPair[] getCount(String key, String filters) {
		DataCountPair[] array = new DataCountPair[21];
		for (int day=0;day<21;day++) {
			int[] cnt = new int[48];
			String query;
			if (msql.connect()) {
				query = "select halfhour, totalcount from microblogcount where day = "+day+" and "+filters;
				System.out.println(query);
				msql.query(query);
				while (msql.next()) {
					cnt[msql.getInt("halfhour")]=msql.getInt("totalcount");
				}
				array[day] = new DataCountPair(key, cnt);
				msql.close();
				System.out.println("done!");
			}
		}
		return array;
	}
}
