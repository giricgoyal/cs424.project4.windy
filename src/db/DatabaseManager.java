package db;
import java.util.ArrayList;

import main.Utilities;

import processing.core.PApplet;
import types.DataPid;
import types.DataPos;
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
			query = "select lat, lon from microblog where "+
					filters;
			System.out.println(query);
			msql.query(query);
			while (msql.next()) {
				array.add(new DataPos(msql.getFloat("lat"),msql.getFloat("lon")));
			}
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
			return temp;
		}
		return null;
	}
}
