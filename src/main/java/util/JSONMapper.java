package util;

import java.io.IOException;

import org.neo4j.driver.v1.Record;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import datastructures.PostBody;

public class JSONMapper {
	
	public PostBody jsonToPostBody(String json) {
		ObjectMapper om = new ObjectMapper();
		PostBody pb = null;
		try {
			JsonNode jsonNode = om.readTree(json);
			pb = om.readValue(json, PostBody.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pb;

	}
	
	public String recordToJson (Record r) {
		
		return null;
	}
}
