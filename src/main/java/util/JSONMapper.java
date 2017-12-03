package util;

import java.io.IOException;

import org.neo4j.driver.v1.Record;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import datastructures.EditUserDTO;
import datastructures.User;
import datastructures.post.PostBody;

public class JSONMapper {
	
	//Generics are for real programmers
	public <T> T jsonToType(Class<T> typeParameterClass, String json) {
		ObjectMapper om = new ObjectMapper();
		
		T output = null;
		try {
			output = om.readValue(json, typeParameterClass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	public String recordToJson (Record r) {
		
		return null;
	}
}
