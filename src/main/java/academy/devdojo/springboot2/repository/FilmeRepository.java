package academy.devdojo.springboot2.repository;

import academy.devdojo.springboot2.domain.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {


//    @Query("select f from filmes f where f.nome like %:nome% ")
    List<Filme> findByNomeContaining(String nome);
}
