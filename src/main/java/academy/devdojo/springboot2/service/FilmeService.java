package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.Filme;
import academy.devdojo.springboot2.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;

    public List<Filme> listAll() {
       return repository.findAll();
    }

    public Filme findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filme not Found"));
    }

    public Filme save(Filme filme) {
        repository.save(filme);
        return filme;
    }

    public void delete(long id) {
        findById(id);
        repository.deleteById(findById(id).getId());
    }

    public void replace(Long id, Filme filme) {
        findById(id);
        filme.setId(id);
        repository.save(filme);
    }
}
