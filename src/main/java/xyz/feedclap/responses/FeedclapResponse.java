package xyz.feedclap.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedclapResponse<T> {
	private String status;
	private String code;
	private String message;
	private T data;

	public FeedclapResponse(String status,String code,String message, T data){
		this.status = status;
		this.code = code;
		this.message = message;
		this.data = data;
	}


}