package db.neo4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;
import org.neo4j.driver.v1.types.Node;

import datastructures.PostBody;
import util.StatusMonitor;

public class MyNeo4jMapper {

	private Neo4jConnector connector = new Neo4jConnector();
	private Neo4jQueries queries = new Neo4jQueries();

	public boolean persistPost(PostBody pb) {
		Session s = connector.getSession();

		Map<String, Object> map = new HashMap();
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

	public List<PostBody> getPostsLimit(int limit) {
		Session s = connector.getSession();
		List<PostBody> list = new ArrayList<>();

		StatementResult result = s.run(queries.getPostsLimit(), Values.parameters("limit", limit));

		while (result.hasNext()) {
			Record record = result.next();

			PostBody p = new PostBody();
			Node n = record.get("post").asNode();
			Map resultMap = n.asMap();

			p.setPost_title((String) resultMap.get("post_title"));

			p.setPost_text((String) resultMap.get("post_text"));

			p.setHanesst_id(Math.toIntExact((Long) resultMap.get("hanesst_id")));

			p.setPost_type((String) resultMap.get("post_type"));

			p.setPost_parent(Math.toIntExact((Long) resultMap.get("post_parent")));

			p.setUsername((String) resultMap.get("username"));

			p.setPwd_hash((String) resultMap.get("pwd_hash"));

			p.setPost_url((String) resultMap.get("post_url"));
			list.add(p);

		}

		s.close();
		return list;
	}
}
