package db.neo4j;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;

import datastructures.PostBody;

public class Neo4jUtil {

	public List<PostBody> castMultiplePostNodesToList(StatementResult result) {

		List<PostBody> list = new ArrayList<>();

		while (result.hasNext()) {

			Record record = result.next();
			Node n = record.get("post").asNode();

			list.add(new PostBody(n));
		}
		return list;
	}
}
