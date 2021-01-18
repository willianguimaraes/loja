package com.willian.loja.DTO;

import com.willian.loja.domain.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5, max = 80, message = "Tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(Categoria categoria){
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}
