package com.willian.loja;

import com.willian.loja.Repository.CategoriaRepository;
import com.willian.loja.Repository.ProdutoRepository;
import com.willian.loja.domain.Categoria;
import com.willian.loja.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class LojaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = Categoria.builder().nome("Informatica").build();
		Categoria cat2 = Categoria.builder().nome("Escritorio").build();

		Produto prod1 = Produto.builder().nome("Caneta").preco(2.00).categorias(Arrays.asList(cat2)).build();
		Produto prod2 = Produto.builder().nome("Teclado").preco(20.00).categorias(Arrays.asList(cat1)).build();
		Produto prod3 = Produto.builder().nome("Mouse").preco(10.00).categorias(Arrays.asList(cat1)).build();
		Produto prod4 = Produto.builder().nome("Papel").preco(50.00).categorias(Arrays.asList(cat2)).build();

		cat1.setProdutos(Arrays.asList(prod2,prod3));
		cat2.setProdutos(Arrays.asList(prod1,prod4));


//		prod1.getCategorias().add(cat2);
//		prod2.getCategorias().add(cat1);
//		prod3.getCategorias().add(cat1);
//		prod4.getCategorias().add(cat2);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));


	}
}
