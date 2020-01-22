package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
public class Book {
	private String title;
	private String author;
	private String text;
	
	public static Book fromFile(String filePath, 
			String title, 
			String author) throws IOException {
		File file = new File(filePath);
		FileInputStream input = new FileInputStream(file);
		byte[] buffer = new byte[(int)file.length()];
		input.read(buffer);
		String text = new String(buffer, "UTF-8");
		return new Book(title, author, text);
	}
	
	public Book(String title, String author, String text) {
		if (title == null || title.trim().length() == 0)
			throw new IllegalArgumentException("All books must have a title.");
		if (author == null || author.trim().length() == 0)
			throw new IllegalArgumentException("All books must have an author");
		if (text == null || text.trim().length() == 0)
			throw new IllegalArgumentException("All books must have text.");
		this.title = title;
		this.author = author;
		this.text = text;
	}
	
	public String title() {
		return title;
	}
	
	public String author() {
		return author;
	}
	
	public String text() {
		return text;
	}

	public int wordCount(String word) {
		return wordCount(word, true); //we used this method to allow others to use it, we just wanted the behavior of the case insensative by passing true. 
	}
	
	public int wordCount(String word, boolean caseSensitive) {
		return getWordPositions(word, caseSensitive).size();
	}
	
	public ArrayList<WordPosition> getWordPositions(String word, boolean caseSensitive) {
		ArrayList<WordPosition> r = new ArrayList<WordPosition>();
		int position = caseSensitive ? text.indexOf(word) :
			StringUtils.indexOfIgnoreCase(text, word);
		while(position >= 0) {
			String foundWord = text.substring(position, position + word.length());
			WordPosition wp = new WordPosition(foundWord, position);
			r.add(wp);
			int nextPos = position + word.length();
			position = caseSensitive ? text.indexOf(word, nextPos) :
				StringUtils.indexOfIgnoreCase(text, word, nextPos);
		}
		return r;
	}
	
	public Map<String, Integer> wordCounts(int minWordLength) {
		String[] words = text.split("\\s+");
		Map<String,Integer> map = new TreeMap<String,Integer>();
		for(String w : words) { 
			String lcw = w.trim();
			if (lcw.endsWith(".")) { //lcw represents lower case word. 
				lcw = lcw.substring(0, lcw.length()-1);
			}
			if (lcw.length() >= minWordLength) {
				lcw = lcw.toLowerCase(); //This is where it becomes lower case. 
				if (!map.containsKey(lcw)) map.put(lcw, 1); else //if map doesn;t contain key, put a key there and add a value of one to it.  
					map.put(lcw, map.get(lcw) + 1); // if map has key, get it and then add that value. 
			}
		}
		return map;
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\" by %s.", title, author);
	}
}