package mbronshteyn.lab4sentence.controller;

import mbronshteyn.lab4sentence.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceController {

  @Autowired
  SubjectRepo subjectRepo;

  @Autowired
  VerbRepo verbRepo;

  @Autowired
  ArticleRepo articleRepo;

  @Autowired
  AdjectiveRepo adjectiveRepo;

  @Autowired
  NounRepo nounRepo;

  @GetMapping("/sentence")
  public @ResponseBody String getSentence() {
    return
        subjectRepo.getItem() + " "
        + verbRepo.getItem() + " "
        + articleRepo.getItem() + " "
        + adjectiveRepo.getItem() + " "
        + nounRepo.getItem() + ".\n"
      ;
  }
}
