package com.willian.loja.DTO;

import com.willian.loja.enums.TipoCliente;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioNewDto implements Serializable {

    private String nome;
    private String email;
    private String cpfCnpj;
    private TipoCliente tipoCliente;

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;

    private String telefone1;
    private String telefone2;
    private String telefone3;

    private Integer cidadeId;

    public UsuarioNewDto(){

    }
}
