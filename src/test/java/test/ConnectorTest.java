package test;
import static org.junit.Assert.*;

import org.junit.Test;

import db.neo4j.Neo4jConnector;

public class ConnectorTest {

	@Test
	public void testUrl() {
		Neo4jConnector conn = new Neo4jConnector();
		assertEquals("bolt://178.62.100.149:1234", conn.getUrl());
	}

}
