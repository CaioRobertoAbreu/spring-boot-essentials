package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.AtualizaFilmeRequest;
import academy.devdojo.springboot2.domain.CadastraFilmeRequest;
import academy.devdojo.springboot2.domain.Filme;
import academy.devdojo.springboot2.service.FilmeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("filmes")
@Log4j2
@RequiredArgsConstructor
public class FilmeController {

    private final DateUtil dateUtil;
    private final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<Page<Filme>> list(@PageableDefault(size = 5, page = 0, sort = "nome")
                                            Pageable pageable) {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(filmeService.listAll(pageable));
    }

    @GetMapping("/find")
    public ResponseEntity<List<Filme>> findByName(@RequestParam(required = true) String nome){
        List<Filme> filmes = filmeService.findByName(nome);

        return ResponseEntity.ok(filmes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Filme> findById(@PathVariable long id) {
        return ResponseEntity.ok(filmeService.findByIdOrThrowException(id));
    }

    @PostMapping
    public ResponseEntity<Filme> save(@RequestBody @Valid CadastraFilmeRequest filme) {
        return new ResponseEntity<>(filmeService.save(filme), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        filmeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@Valid @RequestBody AtualizaFilmeRequest filme) {
        filmeService.replace(filme);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
