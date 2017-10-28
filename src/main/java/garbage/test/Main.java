package garbage.test;

import org.neo4j.driver.v1.Session;

import db.neo4j.Neo4jConnector;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Neo4jConnector nc = new Neo4jConnector();
		Session s = nc.getSession();
		System.out.println(nc.getSession().isOpen());
		s.close();
		
		

	}

}
