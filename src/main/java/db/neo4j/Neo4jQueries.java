package db.neo4j;

public class Neo4jQueries implements Neo4jQueryInterface {

//	CREATE (p:Post { post_title: "Woz Interview: the early days of Apple", 
//		 post_text: "", 
//		 hanesst_id: 3, 
//		 post_type: "story", 
//		 post_parent: -1, 
//		 username: "phyllis", 
//		 pwd_hash: "fyQgkcLMD1", 
//		 post_url: "http://www.foundersatwork.com/stevewozniak.html"})
	
	public String addPost() {
		// TODO Auto-generated method stub
		return "CREATE (:Post { post_title: {post_title}, \n" + 
				" post_text: {post_text} , \n" + 
				" hanesst_id: {hanesst_id}, \n" + 
				" post_type: {post_type}, \n" + 
				" post_parent: {post_parent}, \n" + 
				" username: {username}, \n" + 
				" pwd_hash: {pwd_hash}, \n" + 
				" post_url: {post_url}})";
	}

}
