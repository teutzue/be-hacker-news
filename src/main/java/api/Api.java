package api;

import datastructures.PostBody;
import datastructures.User;
import db.neo4j.MyNeo4jMapper;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.JSONMapper;
import util.StatusMonitor;

import java.util.List;

@CrossOrigin
@RestController
public class Api {

	private MyNeo4jMapper mapper = new MyNeo4jMapper();
	private JSONMapper jsonmap = new JSONMapper();
	private ApiUtil util = new ApiUtil();

	@Autowired
	public Api() {
	}

	@RequestMapping("/test")
	public String echo() {
		return "test call";
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody String json) {
		if (StatusMonitor.isOperational()) {
			Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();
			StatusMonitor.incrementCounter();

			PostBody post = jsonmap.jsonToPostBody(json);
			if (!util.validatePost(post)) {
				requestTimer.observeDuration();
				return ResponseEntity.status(400).body("Request is invalid. Check Hanesst_id, post_parent, "
						+ "post_title, post_type, pwd_hash and username ");
			}
			post.setTimestamp(System.currentTimeMillis());
			mapper.persistPost(post);

			requestTimer.observeDuration();
			return ResponseEntity.status(201).body((post.getPost_parent() + " " + post.getPost_url() + " " + post.getUsername() + " "
					+ StatusMonitor.getLastPostId()));
		}
		return ResponseEntity.status(423).body("Application is under update");
	}

	@RequestMapping(path = "/getPosts", method = RequestMethod.GET)
	public List<PostBody> getPosts(@RequestParam(value = "limit") int limit) {
		Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();
		StatusMonitor.incrementCounter();

		List<PostBody> posts = mapper.getPostsLimit(limit);

		requestTimer.observeDuration();
		return posts;
	}

	@RequestMapping(path = "/from", method = RequestMethod.GET)
	public List<PostBody> getPostsBySite(@RequestParam(value = "site") String site) {
		Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();
		StatusMonitor.incrementCounter();

		List<PostBody> posts = mapper.getPostsBySite(site);

		requestTimer.observeDuration();
		return posts;
	}

	@RequestMapping(path = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody String json) {
		if (StatusMonitor.isOperational()) {
			Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();

			StatusMonitor.incrementCounter();

			User u = jsonmap.jsonToUser(json);
			
			if (!util.validateUser(u)) {
				requestTimer.observeDuration();
				return ResponseEntity.status(400).body(u);
			}

			User addedUser = mapper.addUser(u);
			if (addedUser==null)
			{
				return ResponseEntity.status(400).body(u);
			}

			requestTimer.observeDuration();
			return ResponseEntity.status(201).body(u);
		}
		return ResponseEntity.status(500).body(null);
	}

	@RequestMapping(path = "/logIn", method = RequestMethod.POST)
	public User logIn(@RequestBody String json) {
		Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();

		StatusMonitor.incrementCounter();

		User u = jsonmap.jsonToUser(json);
		User loggedUser = mapper.logIn(u);

		requestTimer.observeDuration();

		return loggedUser;
	}

	@RequestMapping(path = "/status", method = RequestMethod.GET)
	public String status() {
		StatusMonitor.incrementCounter();

		return StatusMonitor.getStatus();
	}

	@RequestMapping(path = "/latest", method = RequestMethod.GET)
	public int latest() {
		StatusMonitor.incrementCounter();

		return StatusMonitor.getLastPostId();
	}
	
	@RequestMapping("/changeStatus/update")
	public boolean setUpdate() {

		StatusMonitor.setUpdate();
		return true;
	}
	
	@RequestMapping("/changeStatus/alive")
	public boolean setAlive() {

		StatusMonitor.setAlive();
		return true;
	}
	

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		DefaultExports.initialize();
		return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
	}

}
