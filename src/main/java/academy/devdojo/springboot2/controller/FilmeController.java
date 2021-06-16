package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Filme;
import academy.devdojo.springboot2.service.FilmeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Filme>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(filmeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Filme> findById(@PathVariable long id) {
        return ResponseEntity.ok(filmeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Filme> save(@RequestBody Filme filme) {
        return new ResponseEntity<>(filmeService.save(filme), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        filmeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody Filme filme) {
        filmeService.replace(id, filme);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
