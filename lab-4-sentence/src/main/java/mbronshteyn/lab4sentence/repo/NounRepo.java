package mbronshteyn.lab4sentence.repo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient( "LAB-4-NOUN" )
public interface NounRepo {
  @RequestMapping( method = RequestMethod.GET, value = "/")
  String getItem();
}
