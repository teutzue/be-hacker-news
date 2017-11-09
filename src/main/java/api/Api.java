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
import org.springframework.web.bind.annotation.*;
import util.JSONMapper;
import util.StatusMonitor;

import java.util.List;

@CrossOrigin
@RestController
public class Api {

	private MyNeo4jMapper mapper = new MyNeo4jMapper();
	private JSONMapper jsonmap = new JSONMapper();

	@Autowired
	public Api() {
	}

	@RequestMapping("/test")
	public String echo() {
		return "test call";
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public String post(@RequestBody String json) {
		Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();

		StatusMonitor.incrementCounter();

		PostBody post = jsonmap.jsonToPostBody(json);
		mapper.persistPost(post);

		requestTimer.observeDuration();
		return (post.getPost_parent() + " " + post.getPost_url() + " " + post.getUsername() + " "
				+ StatusMonitor.getLastPostId());
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
	public User addUser(@RequestBody String json) {
		Summary.Timer requestTimer = StatusMonitor.getRequestlatency().startTimer();

		StatusMonitor.incrementCounter();

		User u = jsonmap.jsonToUser(json);

		User addedUser = mapper.addUser(u);

		requestTimer.observeDuration();
		return addedUser;
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

	@RequestMapping("/status")
	public String status() {
		StatusMonitor.incrementCounter();

		return StatusMonitor.getStatus();
	}

	@RequestMapping("/latest")
	public int latest() {
		StatusMonitor.incrementCounter();

		return StatusMonitor.getLastPostId();
	}

	@Bean
	ServletRegistrationBean servletRegistrationBean() {
		DefaultExports.initialize();
		return new ServletRegistrationBean(new MetricsServlet(), "/metrics");
	}

}
