package academy.devdojo.springboot2.service;

import academy.devdojo.springboot2.domain.AtualizaFilmeRequest;
import academy.devdojo.springboot2.domain.CadastraFilmeRequest;
import academy.devdojo.springboot2.domain.Filme;
import academy.devdojo.springboot2.exception.NotFoundException;
import academy.devdojo.springboot2.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;

    public Page<Filme> listAll(Pageable pageable) {
       return repository.findAll(pageable);
    }

    public List<Filme> listAllNonPageable() {
        return repository.findAll();
    }

    public List<Filme> findByName(String nome){
        return repository.findByNomeContaining(nome);
    }

    public Filme findByIdOrThrowException(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Filme not Found"));
    }

    @Transactional
    public Filme save(CadastraFilmeRequest filmeRequest) {
        var filme = Filme.builder()
                .nome(filmeRequest.getNome())
                .build();

        repository.save(filme);
        return filme;
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(findByIdOrThrowException(id).getId());
    }

    @Transactional
    public void replace(AtualizaFilmeRequest filmeRequest) {
        findByIdOrThrowException(filmeRequest.getId());
        var filme = Filme.builder()
                .id(filmeRequest.getId())
                .nome(filmeRequest.getNome())
                .build();

        repository.save(filme);
    }

}
