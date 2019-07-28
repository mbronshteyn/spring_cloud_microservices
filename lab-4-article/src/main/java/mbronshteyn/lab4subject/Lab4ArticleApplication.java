package mbronshteyn.lab4subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Lab4ArticleApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab4ArticleApplication.class, args);
	}

}
