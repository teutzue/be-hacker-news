package db.neo4j;

public interface Neo4jQueryInterface {

	public String addPost() ;

	public String getPostsBySite();
	
	public String getPostsLimit();
	
	public String addUser();
	
	public String logIn();
}
