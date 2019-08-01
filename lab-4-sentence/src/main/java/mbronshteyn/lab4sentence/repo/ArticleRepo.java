package mbronshteyn.lab4sentence.repo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient( "LAB-4-ARTICLE" )
public interface ArticleRepo {
  @RequestMapping( method = RequestMethod.GET, value = "/")
  String getItem();
}
