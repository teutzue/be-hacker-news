package db.neo4j;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Values;

import datastructures.PostBody;
import util.StatusMonitor;

public class MyNeo4jMapper {

	private Neo4jConnector connector = new Neo4jConnector();
    private Neo4jQueries queries = new Neo4jQueries();
//    CREATE (p:Post { post_title: "Woz Interview: the early days of Apple", 
//    	 post_text: "", 
//    	 hanesst_id: 3, 
//    	 post_type: "story", 
//    	 post_parent: -1, 
//    	 username: "phyllis", 
//    	 pwd_hash: "fyQgkcLMD1", 
//    	 post_url: "http://www.foundersatwork.com/stevewozniak.html"})
    public boolean persistPost(PostBody pb) {
    		Session s = connector.getSession();
    		
    		
    		Map<String,Object> map= new HashMap();
    		System.out.println(pb.getPost_text() + "sup boi");
        map.put("post_text", pb.getPost_text());
        map.put("post_title", pb.getPost_title());
        map.put("hanesst_id", pb.getHanesst_id());
        map.put("post_type", pb.getPost_type());
        System.out.println(pb.getPost_type() + " cute post type boi");
        map.put("post_parent", pb.getPost_parent());
        map.put("username", pb.getUsername());
        map.put("pwd_hash", pb.getPwd_hash());
        map.put("post_url", pb.getPost_url());
    		
    		s.run(queries.addPost(), map);
    		
    		
    		
    		s.close();
    		
    		StatusMonitor.setLastPostId(pb.getHanesst_id());
    		return false;
    }
}
