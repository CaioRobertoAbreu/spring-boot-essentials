package academy.devdojo.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AtualizaFilmeRequest {

    private Long id;
    private String nome;
}
