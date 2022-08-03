package midianet.rh.controller;


import midianet.rh.domain.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import midianet.rh.service.PessoaService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;


    @GetMapping
    public List<Pessoa> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable final Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final Pessoa pessoa, final HttpServletResponse response) {
        final var id = service.create(pessoa);
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri().toString());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final Integer id, @Valid @RequestBody final Pessoa pessoa){
        service.update(id,pessoa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id){
        service.deleteById(id);
    }

}
