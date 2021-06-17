package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.AtualizaFilmeRequest;
import academy.devdojo.springboot2.domain.CadastraFilmeRequest;
import academy.devdojo.springboot2.domain.Filme;
import academy.devdojo.springboot2.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;

    public List<Filme> listAll() {
       return repository.findAll();
    }

    public Filme findByIdOrThrowException(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Filme not Found"));
    }

    public Filme save(CadastraFilmeRequest filmeRequest) {
        Filme filme = Filme.builder()
                .nome(filmeRequest.getNome())
                .build();

        repository.save(filme);
        return filme;
    }

    public void delete(long id) {
        repository.deleteById(findByIdOrThrowException(id).getId());
    }

    public void replace(AtualizaFilmeRequest filmeRequest) {
        findByIdOrThrowException(filmeRequest.getId());
        Filme filme = Filme.builder()
                .id(filmeRequest.getId())
                .nome(filmeRequest.getNome())
                .build();

        repository.save(filme);
    }
}
