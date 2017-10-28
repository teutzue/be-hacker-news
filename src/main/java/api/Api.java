package api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import datastructures.PostBody;
import util.StatusMonitor;

@CrossOrigin
@RestController
public class Api {
	
    @Autowired
    public Api() {
        System.out.println("hey");
    }

    @RequestMapping("/test")
    public String echo(@RequestParam(value = "echo") String echo) {
        return echo;
    }
    
    @RequestMapping(path="/post", method=RequestMethod.POST)
    public String post(@RequestBody PostBody post) {
    	
    		StatusMonitor.setLastPostId(post.getHanesst_id());
    		return (post.getPost_parent()+" "+ post.getPost_url()+" "+ post.getUsername()+" "+ StatusMonitor.getLastPostId());
    	//	return "Ok";
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
