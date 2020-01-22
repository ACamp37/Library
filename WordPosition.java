package library;

public class WordPosition {
	private int position;
	private String word;
	
	WordPosition(String word, int position) {
		this.word = word;
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public String getWord() {
		return word;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isWordPosition = getClass().isInstance(o); //Lines 22 and 23 the same as line 24. 
		if (!isWordPosition) return false;
		//if (!getClass().isInstance(o)) return false;
		//if o is not a instance of getClass aka WordPosition, return false. 
		WordPosition wp = (WordPosition)o;
		return position == wp.position && word.equals(wp.word);
		//position == both this objects position and word 
	}
}
