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
	
	public default String addCommentQuery() {
		return "Match (p:Post) \n" 
				+ "Where p.hanesst_id={post_parent} \n" 
				+ "CREATE (:Post { post_title: {post_title}, post_text: {post_text} ,\n" 
				+ "hanesst_id: {hanesst_id},post_type: {post_type}, "
				+ "post_parent: p.hanesst_id, username: {username}, "
				+ "pwd_hash: {pwd_hash}, post_url: {post_url}, "
				+ "timestamp: {timestamp}})<-[par:Parent]-(p)";
	}
	
	public default String getPostsBySiteQuery() {

		return "MATCH (post:Post) WHERE post.post_url CONTAINS {site} RETURN post order by post.timestamp desc";
	}

	public default String getPostsLimitQuery(Integer limit) {

		return "MATCH (post:Post) where post.post_parent = -1 return post order by post.timestamp desc limit "+limit;
	}

	public default String addUserQuery() {

		return "CREATE (:User {user_name:{user_name}, user_pwd:{user_pwd}})";
	}

	public default String logInQuery() {
		return "MATCH (u:User) WHERE u.user_name={user_name} and u.user_pwd={user_pwd} return u";
	}
	
	public default String getCommentsQuery(int hanesst_id) {
		return "MATCH (parent:Post{hanesst_id:"+hanesst_id+"})-[:Parent *1..]->(child:Post) "
				+ "return parent as story,collect(child) as comments, count(child) as numberOfcomments";
	}
	
	public default String generateHanesst_idQuery() {
		return "MATCH (idc:IdCounter)\n" + 
				"SET idc.counter=idc.counter-1 \n" + 
				"RETURN idc.counter as id;";
	}
}
