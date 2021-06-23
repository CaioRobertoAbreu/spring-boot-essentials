package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CadastraFilmeRequest {

    @NotBlank
    private String nome;
}
