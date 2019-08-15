package mbronshteyn.lab4sentence;

import mbronshteyn.lab4sentence.model.CustomException;
import mbronshteyn.lab4sentence.model.ErrorMessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = { Exception.class } )
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        ErrorMessage errorMessage = ErrorMessage.Builder.newInstance()
                .withMessage(ex.getLocalizedMessage())
                .withTimestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>( errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler( value = { CustomException.class } )
    public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request){

        ErrorMessage errorMessage = ErrorMessage.Builder.newInstance()
                .withMessage(ex.getName() )
                .withTimestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>( errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
