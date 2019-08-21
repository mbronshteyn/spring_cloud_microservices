package mbronshteyn.lab4sentence.model;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {

    private String name = "CustomException";

    public String getName() {
        return name;
    }

}
