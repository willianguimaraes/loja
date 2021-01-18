package com.willian.loja.DTO;

import com.willian.loja.domain.Usuario;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5, max =120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Email(message = "Email Invalido")
    private String email;

    public UsuarioDTO(Usuario obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

}
