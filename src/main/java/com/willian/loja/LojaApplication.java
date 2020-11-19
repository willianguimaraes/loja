package com.willian.loja;

import com.willian.loja.Repository.*;
import com.willian.loja.domain.*;
import com.willian.loja.enums.EstadoPagamento;
import com.willian.loja.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class LojaApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

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

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1,prod2,prod3,prod4));

		Estado est1 = Estado.builder().nome("São Paulo").build();
		Estado est2 = Estado.builder().nome("Minas Gerais").build();

		Cidade cid1 = Cidade.builder().nome("São Paulo").estado(est1).build();
		Cidade cid2 = Cidade.builder().nome("Uberlandia").estado(est2).build();
		Cidade cid3 = Cidade.builder().nome("Campinas").estado(est1).build();
		Cidade cid4 = Cidade.builder().nome("Sorocaba").estado(est1).build();

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3,cid4));

		Usuario usuario1 = Usuario.builder()
				.nome("Maria Silva")
				.email("mariaSilva@gmail.com")
				.cpfCnpj("123456789")
				.tipoCliente(TipoCliente.PESSOAFISICA)
				.build();

		usuario1.setTelefones(Arrays.asList("4399999-0001", "4333339999"));

		Endereco e1 = Endereco.builder()
				.logradouro("Rua FLores")
				.numero("300")
				.complemento("APTO 301")
				.bairro("Jardim")
				.cep("38220834")
				.usuario(usuario1)
				.cidade(cid2)
				.build();

		Endereco e2 = Endereco.builder()
				.logradouro("Av Mattos")
				.numero("104")
				.complemento("sala 900")
				.bairro("Centro")
				.cep("38772898")
				.usuario(usuario1)
				.cidade(cid3)
				.build();

		usuario1.setEnderecos(Arrays.asList(e1,e2));

		usuarioRepository.saveAll(Arrays.asList(usuario1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = Pedido.builder()
				.instante(sdf.parse("30/09/2017 10:32"))
				.usuario(usuario1)
				.endereco(e1)
				.build();

		Pedido ped2 = Pedido.builder()
				.instante(sdf.parse("10/10/2017 19:30"))
				.usuario(usuario1)
				.endereco(e2)
				.build();

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6 );
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);

		usuario1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
	}
}
