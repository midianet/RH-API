package midianet.rh.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank
    @Column(name = "tx_nome",nullable = false ,length = 80)
    private String nome;

    @Size(max = 11, min = 3)
    @Column(length = 11,unique = true)
    private String telefone;

}
