package garbage.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.driver.v1.Session;

import api.Api;
import datastructures.PostBody;
import datastructures.User;
import db.neo4j.MyNeo4jMapper;
import db.neo4j.Neo4jConnector;

public class Main {

	private static final Logger logger = LogManager.getLogger("logstash");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=0;i<=50;i++)
		{
			logger.info("{\"user_name\": \"fuper6\", \n" + 
					" \"user_pwd\": \"fgfg\"}");
		}


		System.out.println();
		

	}

	private static PostBody body() {
		PostBody pb = new PostBody();
		pb.setPost_title("Woz Interview: the early days of Apple");
		pb.setPost_text("");
		pb.setHanesst_id(3);
		pb.setPost_parent(-1);
		pb.setUsername("phyllis");
		pb.setPwd_hash("fyQgkcLMD1");
		pb.setPost_url("http://www.foundersatwork.com/stevewozniak.html");
		
		
		pb.setPost_type("story");
		
		return pb;
	}

}
