package garbage.test;

import datastructures.post.PostBody;
import db.neo4j.MyNeo4jMapper;

public class Main {

	
	public static void main(String[] args) {
		MyNeo4jMapper mapper = new MyNeo4jMapper();

		//mapper.getComments(445);
		

		System.out.println(mapper.editUser("pg", "lol6", "lol7"));
		

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
