package library;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookWorm {

	public static void main(String[] args) {
		try {
			Book mobyDick = Book.fromFile("mobydick.txt", "Moby Dick", 
					"Herman Melville");
			System.out.println("Book is loaded!");
			System.out.println(mobyDick);
			int count = mobyDick.wordCount("Leviathan", true);
			System.out.printf("The word 'Leviathan' occurs %d times.", count);
			ArrayList<WordPosition> positions = 
					mobyDick.getWordPositions("Leviathan", false);
			for(WordPosition wp : positions) {
				System.out.printf("%s:\t%d\r\n", wp.getWord(), wp.getPosition());
			}
			/*
			 * Map<String,Integer> counts = mobyDick.wordCounts(5);
			 * //LinkedHashMap<String,Integer> sortedMap = new LinkedHashMap();
			 * counts.entrySet() .stream() .sorted(Map.Entry.comparingByValue())
			 * //.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
			 * .forEachOrdered(x -> System.out.printf("%s\t%d\r\n", x.getKey(),
			 * x.getValue())); //sortedMap.forEach((k,v) -> System.out.printf("%s\t%d\r\n",
			 * k,v));
			 * 
			 * System.out.printf("%d words found:\r\n", counts.size());
			 */		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry - that book is not available.");
		}
	}
	
	private static void myFirstBook() {
		Book book = new Book("My First Book!", "Me", "This is my first book!");
		System.out.println("My book is titled: " + book.title() 
				+ " by " + book.author());
		System.out.println(book.text());
		
		try {
			Book emptyBook = new Book("     ", null, "    ");
		}
		catch(IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
			System.out.println("My bad!");
		}
	}

}
