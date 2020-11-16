package com.willian.loja.service;

import com.willian.loja.Repository.CategoriaRepository;
import com.willian.loja.domain.Categoria;
import com.willian.loja.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " +Categoria.class.getName()));
    }

}
