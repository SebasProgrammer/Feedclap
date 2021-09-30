package xyz.feedclap.exceptions;

public class FeedclapException extends Exception {
    private final String code;
	private final int responseCode;

	public FeedclapException(String code, int responseCode, String message){
		super(message);
		this.code=code;
		this.responseCode=responseCode;
	}
    
}
