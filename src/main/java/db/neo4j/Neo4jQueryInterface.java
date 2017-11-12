package db.neo4j;

public interface Neo4jQueryInterface {

	public default String addPostQuery() {
		return "CREATE (:Post { post_title: {post_title}, \n" + 
				" post_text: {post_text} , \n" + 
				" hanesst_id: {hanesst_id}, \n" + 
				" post_type: {post_type}, \n" + 
				" post_parent: {post_parent}, \n" + 
				" username: {username}, \n" + 
				" pwd_hash: {pwd_hash}, \n" + 
				" post_url: {post_url},"
				+ "timestamp: {timestamp}})";
	}
	
	public default String getPostsBySiteQuery() {

		return "MATCH (post:Post) WHERE post.post_url CONTAINS {site} RETURN post order by post.timestamp desc";
	}

	public default String getPostsLimitQuery() {

		return "MATCH (post:Post) return post order by post.timestamp desc limit {limit} ";
	}

	public default String addUserQuery() {

		return "CREATE (:User {user_name:{user_name}, user_pwd:{user_pwd}})";
	}

	public default String logInQuery() {
		return "MATCH (u:User) WHERE u.user_name={user_name} and u.user_pwd={user_pwd} return u";
	}
}
