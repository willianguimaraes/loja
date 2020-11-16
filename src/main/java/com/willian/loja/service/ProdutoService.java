package com.willian.loja.service;

import com.willian.loja.Repository.ProdutoRepository;
import com.willian.loja.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Produto findById(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }
    public List<Produto> findByCategoria(Integer id){
        List<Produto> produtos = produtoRepository.findByCategoria(id);
        return produtos;
    }

}
