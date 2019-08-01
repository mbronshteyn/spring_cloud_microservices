package mbronshteyn.lab4sentence.controller;

import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class SentenceController {

  @Autowired
  @Qualifier( "LoadBalanced" )
  public RestTemplate restTemplate;

  @GetMapping("/sentence")
  public @ResponseBody
  String getSentence() {
    return
      getWord("LAB-4-SUBJECT") + " "
        + getWord("LAB-4-VERB") + " "
        + getWord("LAB-4-ARTICLE") + " "
        + getWord("LAB-4-ADJECTIVE") + " "
        + getWord("LAB-4-NOUN") + ".\n"
      ;
  }

  public String getWord(String service) {
        return restTemplate.getForObject( "http://" + service, String.class );
  }
}
