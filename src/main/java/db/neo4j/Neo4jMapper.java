package db.neo4j;

import org.neo4j.driver.v1.Session;

public class Neo4jMapper {

	private Neo4jConnector connector = new Neo4jConnector();
    private Neo4jQueries queries = new Neo4jQueries();
    
    public boolean persistPost() {
    		Session s = connector.getSession();
    		s.close();
    		return false;
    }
}
