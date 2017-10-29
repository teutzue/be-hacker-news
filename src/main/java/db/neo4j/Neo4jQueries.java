package db.neo4j;

public class Neo4jQueries implements Neo4jQueryInterface {

	@Override
	public String addPost() {
		return "CREATE (:Post { post_title: {post_title}, \n" + 
				" post_text: {post_text} , \n" + 
				" hanesst_id: {hanesst_id}, \n" + 
				" post_type: {post_type}, \n" + 
				" post_parent: {post_parent}, \n" + 
				" username: {username}, \n" + 
				" pwd_hash: {pwd_hash}, \n" + 
				" post_url: {post_url}})";
	}

	@Override
	public String getPostsLimit() {
		
		return "MATCH (post:Post) return post limit {limit}";
	}

}
