package com.willian.loja.service;

import com.willian.loja.Repository.PedidoRepository;
import com.willian.loja.domain.Pedido;
import com.willian.loja.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido findById(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Pedido.class.getName()));
    }

}