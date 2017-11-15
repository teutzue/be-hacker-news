package util;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Log {

	private String message;
	private String path;
	private String id = UUID.randomUUID().toString();
	
	public Log() {
		
	}
	
	public Log (String path, String message) {
		this.message=message;
		this.path=path;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getCanonicalName());
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
