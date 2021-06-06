package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Filme;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository {
    List<Filme> listAll();
}
