package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DisplayName("FilmeRepository")
@ActiveProfiles(profiles = "test")
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("Deve salvar um filme")
    void deveSalvarUmFilme(){

        var filmeSalvo = repository.save(new Filme(null, "Amanhecer parte 1"));

        assertNotNull(filmeSalvo);
    }

    @Test
    @DisplayName("Deve encontrar filmes pesquisando por parte dele")
    void deveEncontrarFilmesPorParteDoNome(){

        repository.saveAll(retornaListaFilmes());
        var filmesEncontrados = repository.findByNomeContaining("Amanhecer parte");

        assertNotNull(filmesEncontrados);
        assertEquals(2, filmesEncontrados.size());
    }


    private List<Filme> retornaListaFilmes(){

        return Arrays.asList(
                new Filme(1L, "Amanhecer parte 1"),
                new Filme(2L, "Uma noite no museu"),
                new Filme(2L, "Amanhecer parte 2")
        );
    }
}