package garbage.test;

import org.neo4j.driver.v1.Session;

import datastructures.PostBody;
import datastructures.User;
import db.neo4j.MyNeo4jMapper;
import db.neo4j.Neo4jConnector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Neo4jConnector nc = new Neo4jConnector();
//		Session s = nc.getSession();
//		System.out.println(nc.getSession().isOpen());
//		s.close();
		
		MyNeo4jMapper mapper = new MyNeo4jMapper();
		User u = new User();
		u.setUser_name("adammm");
		u.setUser_pwd("adammm");
	//	u.setUser_name("dude64");
	//	u.setUser_pwd("mude");
		System.out.println(mapper.logIn(u));
		
		

	}
//	{"username": "<string>", 
//	 "post_type": "<string>", 
//	 "pwd_hash": "<string>", 
//	 "post_title": "<string>",
//	 "post_url": "<string>", 
//	 "post_parent": <int>, 
//	 "hanesst_id": <int>, 
//	 "post_text": "<string>"}
//	{"post_title": "Woz Interview: the early days of Apple", 
//		 "post_text": "", 
//		 "hanesst_id": 3, 
//		 "post_type": "story", 
//		 "post_parent": -1, 
//		 "username": "phyllis", 
//		 "pwd_hash": "fyQgkcLMD1", 
//		 "post_url": "http://www.foundersatwork.com/stevewozniak.html"}
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
