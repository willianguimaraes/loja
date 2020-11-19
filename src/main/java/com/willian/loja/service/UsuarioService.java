package com.willian.loja.service;

import com.willian.loja.Repository.UsuarioRepository;
import com.willian.loja.domain.Usuario;
import com.willian.loja.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Usuario.class.getName()));
    }

}