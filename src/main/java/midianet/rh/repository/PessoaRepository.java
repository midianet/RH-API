package midianet.rh.repository;

import midianet.rh.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {

    Optional<Pessoa> findByTelefone(String telefone);

}
