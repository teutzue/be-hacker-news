package db.neo4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;
import org.neo4j.driver.v1.exceptions.ClientException;
import org.neo4j.driver.v1.exceptions.NoSuchRecordException;
import org.neo4j.driver.v1.types.Node;

import datastructures.Post;
import datastructures.PostBody;
import datastructures.User;
import util.StatusMonitor;

public class MyNeo4jMapper implements Neo4jQueryInterface {

	private Neo4jConnector connector = new Neo4jConnector();
	private Neo4jUtil util = new Neo4jUtil();
	private static final Logger logger = LogManager.getLogger("logstash");

	public boolean persistPost(PostBody pb) {

		Session s = connector.getSession();

		Map<String, Object> map = new HashMap();

		map.put("post_text", pb.getPost_text());
		map.put("post_title", pb.getPost_title());
		
		if (pb.getHanesst_id()==0 || pb.getHanesst_id()==null)
		{
			StatementResult result=s.run(generateHanesst_idQuery());
			Record record = result.single();
			
			pb.setHanesst_id(record.get("id").asInt());
		}
		map.put("hanesst_id", pb.getHanesst_id());
		map.put("post_type", pb.getPost_type());
		map.put("post_parent", pb.getPost_parent());
		map.put("username", pb.getUsername());
		map.put("pwd_hash", pb.getPwd_hash());
		map.put("post_url", pb.getPost_url());
		map.put("timestamp", pb.getTimestamp().doubleValue());
		
		if (pb.getPost_parent()==-1) s.run(addPostQuery(), map);
		else s.run(addCommentQuery(), map);
		
		
		s.close();

		StatusMonitor.setLastPostId(pb.getHanesst_id());
		//logger.info("Post: "+pb.getPost_title()+" of user "+pb.getUsername()+" has been created successfully");
		

		return true;
	}
	
//	public void getComments(int hanesst_id) {
//		
//		HashMap<Integer, PostBody> commentsMap = new HashMap<>();
//				
//		Session s = connector.getSession();
//		StatementResult result = s.run(getCommentsQuery(hanesst_id));
//		
//		Record record = result.next();
//		
//		Map<String, Object> map = record.asMap();
//		
//		Node mainStory = (Node) map.get("story");
//		List<Node> comments = (List<Node>) map.get("comments");
//		
//		for (Node node : comments) {
//			PostBody comment = new PostBody(node);
//			commentsMap.put(comment.getPost_parent(), comment);
//			//System.out.println((new PostBody(node)).toString());
//		}
//		
//		createHiererchy(hanesst_id, commentsMap);
//		
//		Long count = (Long) map.get("numberOfcomments");
//		
//		PostBody pb = new PostBody(mainStory);
//		
//		System.out.println(count);
//		System.out.println(pb.toString());
//		
//		
//		
//		Set<String> keys = map.keySet();
//		
//		for (String string : keys) {
//			System.out.println(string);
//		}
//		
//		s.close();
//	}
	
	private void createHiererchy(int topHanesst_id, ArrayList<PostBody> comments, PostBody topPostBody ) {
		Post topPost = new Post(null, topPostBody);
		
		while (!comments.isEmpty())
		{
			
		}
	}

	public List<PostBody> getPostsLimit(int limit) {
		Session s = connector.getSession();
		if (limit>=9999) logger.warn("Very heavy request. Stop being a dick.");

		StatementResult result = s.run(getPostsLimitQuery(limit));
		List<PostBody> list = util.castMultiplePostNodesToList(result);

		s.close();
		return list;
	}

	public List<PostBody> getPostsBySite(String site) {
		Session s = connector.getSession();

		StatementResult result = s.run(getPostsBySiteQuery(), Values.parameters("site", site));
		List<PostBody> list = util.castMultiplePostNodesToList(result);

		s.close();
		return list;
	}

	public User addUser(User u) {

		Session s = connector.getSession();
		Map<String, Object> map = new HashMap();

		map.put("user_name", u.getUser_name());
		map.put("user_pwd", u.getUser_pwd());

		try {
			s.run(addUserQuery(), map);
			s.close();
			
		} catch (ClientException e) {
			// handle it properly
			logger.error(e+" "+e.code());
			
			return null;
		}

		logger.info("User "+u.getUser_name()+" has been created");
		return u;
	}

	public User logIn(User u) {

		Session s = connector.getSession();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("user_name", u.getUser_name());
		map.put("user_pwd", u.getUser_pwd());

		StatementResult result = s.run(logInQuery(), map);

		Record record = null;
		try {
			record = result.single();

		} catch (NoSuchRecordException e) {
			s.close();
			e.printStackTrace();
			return null;
		}

		Node n = record.get("u").asNode();
		Map<?, ?> resultMap = n.asMap();

		u.setUser_name((String) resultMap.get("user_name"));

		u.setUser_pwd((String) resultMap.get("user_pwd"));

		if (!u.getUser_name().equals(null) && !u.getUser_pwd().equals(null)) {
			s.close();
			
			logger.info("User "+u.getUser_name()+" has been logged in!");
			return u;
		}

		s.close();
		return null;
	}
}
