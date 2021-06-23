package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class AtualizaFilmeRequest {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String nome;
}
