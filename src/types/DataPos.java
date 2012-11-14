package types;

public class DataPos {
	private float lat;
	private float lon;
	
	public DataPos(float _lat, float _lon) {
		lat = _lat;
		lon = _lon;
	}
	
	public float getLatitude() {
		return lat;
	}

	public float getLongitude() {
		return lon;
	}
	
	public float getByIndex(int index) {
		switch (index){
			case 0: return getLatitude();
			case 1: return getLongitude();
		}
		return 0;
	}
}
