package api;

import datastructures.PostBody;
import datastructures.User;
import db.neo4j.MyNeo4jMapper;
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
	public String echo(@RequestParam(value = "echo") String echo) {
		return echo;
	}

	@RequestMapping(path = "/post", method = RequestMethod.POST)
	public String post(@RequestBody String json) {
		StatusMonitor.incrementCounter();

		PostBody post = jsonmap.jsonToPostBody(json);
		mapper.persistPost(post);
		return (post.getPost_parent() + " " + post.getPost_url() + " " + post.getUsername() + " "
				+ StatusMonitor.getLastPostId());
	}

	@RequestMapping(path = "/getPosts", method = RequestMethod.GET)
	public List<PostBody> getPosts(@RequestParam(value = "limit") int limit) {
        StatusMonitor.incrementCounter();

        return mapper.getPostsLimit(limit);
	}
	
	@RequestMapping(path = "/addUser", method = RequestMethod.POST)
	public boolean addUser(@RequestBody String json) {
        StatusMonitor.incrementCounter();

		User u = jsonmap.jsonToUser(json);
		return mapper.addUser(u);
	}
	
	@RequestMapping(path = "/logIn", method = RequestMethod.POST)
	public boolean logIn(@RequestBody String json) {
        StatusMonitor.incrementCounter();

		User u = jsonmap.jsonToUser(json);
		return mapper.logIn(u);
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
