package com.willian.loja.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
     joinColumns = @JoinColumn(name = "produto_id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "itemPedidoPk.produto")
    private List<ItemPedido> itens = new ArrayList<>();


    public Produto (String nome, Double preco){
        this.nome = nome;
        this.preco = preco;
    }
    
    public List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<>();
        for (ItemPedido itemPedido : itens ) {
            lista.add(itemPedido.getProduto());
        }
        return lista;
    }
}
