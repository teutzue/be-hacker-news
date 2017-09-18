package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api 
{
    @Autowired
    public Api()
    {
		System.out.println("hey");
	}
    
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping("/1")
    public String echo(@RequestParam(value="echo") String echo)
    {
    		return echo;
    }
    
    
}
