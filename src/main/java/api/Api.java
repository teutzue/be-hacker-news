package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datastructures.PostBody;
import db.neo4j.MyNeo4jMapper;
import util.JSONMapper;
import util.StatusMonitor;

@CrossOrigin
@RestController
public class Api {

	private MyNeo4jMapper mapper = new MyNeo4jMapper();
	private JSONMapper jsonmap = new JSONMapper();

	@Autowired
	public Api() {
	}

	@RequestMapping("/test")
	public String echo(@RequestParam(value = "echo") String echo) {
		return echo;
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public String post(@RequestBody String json) {

		PostBody post = jsonmap.jsonToPostBody(json);
		mapper.persistPost(post);
		return (post.getPost_parent() + " " + post.getPost_url() + " " + post.getUsername() + " "
				+ StatusMonitor.getLastPostId());
	}

	@RequestMapping(path = "/getPosts", method = RequestMethod.GET)
	public List<PostBody> getPosts(@RequestParam(value = "limit") int limit) {
		return mapper.getPostsLimit(limit);
	}

	@RequestMapping("/status")
	public String status() {
		return StatusMonitor.getStatus();
	}

	@RequestMapping("/latest")
	public int latest() {
		return StatusMonitor.getLastPostId();
	}

}
