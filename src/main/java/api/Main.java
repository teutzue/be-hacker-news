package api;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main 
{
	public static void main(String[] args) throws IOException 
    {
       // ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/resources/Beans.xml");
    //	SpringApplication app= new SpringApplication(Main.class, args);
		new SpringApplication(Main.class).run(args);
    	System.out.println();
    	System.out.println("----------------Application is ready------------------");
    	System.out.println();
       // SpringApplication.run(Application.class, args);
    }
}
