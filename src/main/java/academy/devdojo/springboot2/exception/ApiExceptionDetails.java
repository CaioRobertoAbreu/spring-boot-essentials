package academy.devdojo.springboot2.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class ApiExceptionDetails {

    private int status;
    private LocalDateTime timestamp;
    private String message;
    private List<Error> erros = new ArrayList<>();

    public ApiExceptionDetails(int status, LocalDateTime timestamp, String message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public void addError(Error error){
        erros.add(error);
    }


}
