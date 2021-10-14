package xyz.feedclap.theclaps.Responses;

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

	public FeedclapResponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
