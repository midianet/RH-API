package midianet.rh.service;


import midianet.rh.domain.Pessoa;
import lombok.RequiredArgsConstructor;
import midianet.rh.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    @Transactional
    public Integer create(final Pessoa pessoa){
        pessoa.setId(null);
        if(Objects.nonNull(pessoa.getTelefone())){
            repository.findByTelefone(pessoa.getTelefone()).ifPresent(p -> {
                throw new RuntimeException("Telefone já utilizado anteriormente");
            });
        }
        return repository.save(pessoa).getId();
    }

    public Pessoa findById(final Integer id){
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não existe:" + id));
    }

    @Transactional
    public void update(final Integer id, final Pessoa pessoa){
        final var persistent = findById(id);
        BeanUtils.copyProperties(pessoa,persistent, "id");
        repository.save(persistent);
    }

    @Transactional
    public void deleteById(final Integer id){
        final var persistent = findById(id);
        repository.delete(persistent);
    }

    public List<Pessoa> findAll(){
        return repository.findAll();
    }

}
