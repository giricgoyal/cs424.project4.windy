package main;

public class WordCountPair {
	private String word;
	private int count;
	
	public WordCountPair(String word) {
		this.word = word;
		count = 1;
	}
	
	public String getWord() {
		return word;
	}
	
	public int getCount() {
		return count;
	}
	
	public void countInc() {
		count++;
	}
}
