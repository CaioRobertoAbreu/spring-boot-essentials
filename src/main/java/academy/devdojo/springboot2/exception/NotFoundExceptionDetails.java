package academy.devdojo.springboot2.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class NotFoundExceptionDetails {

    private int status;
    private LocalDateTime timestamp;
    private String message;

}
