package com.otaviotarelho.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.otaviotarelho.curso.domain.Categoria;
import com.otaviotarelho.curso.domain.Cidade;
import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.domain.Endereco;
import com.otaviotarelho.curso.domain.Estado;
import com.otaviotarelho.curso.domain.ItemPedido;
import com.otaviotarelho.curso.domain.Pagamento;
import com.otaviotarelho.curso.domain.PagamentoComBoleto;
import com.otaviotarelho.curso.domain.PagamentoComCartao;
import com.otaviotarelho.curso.domain.Pedido;
import com.otaviotarelho.curso.domain.Produto;
import com.otaviotarelho.curso.domain.enums.EstadoPagamento;
import com.otaviotarelho.curso.domain.enums.TipoCliente;
import com.otaviotarelho.curso.repositories.CategoriaRepository;
import com.otaviotarelho.curso.repositories.CidadeRepository;
import com.otaviotarelho.curso.repositories.ClienteRepository;
import com.otaviotarelho.curso.repositories.EnderecoRepository;
import com.otaviotarelho.curso.repositories.EstadoRepository;
import com.otaviotarelho.curso.repositories.ItemPedidoRepository;
import com.otaviotarelho.curso.repositories.PagamentoRepository;
import com.otaviotarelho.curso.repositories.PedidoRespository;
import com.otaviotarelho.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository EstadoRepository;
	@Autowired
	private ClienteRepository cienteRepository;
	@Autowired 
	private EnderecoRepository enderecoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired 
	private PedidoRespository pedidoRespository;
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		EstadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "11111111111", TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("123244356", "2423543535"));
		
		Endereco end1 = new Endereco(null, "Rua Maria do Carmo", "445", "Casa 02", "Jardim Casqueir", "11533050", cliente, c2);
		Endereco end2 = new Endereco(null, "Av Ana Costa", "255", "Mezanino", "Gonzaga", "11060001", cliente, c2);
		
		cliente.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		cienteRepository.save(Arrays.asList(cliente));
		enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 18:32"), cliente, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 16:09"), cliente, end2);
		
		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 12);
		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2017 00:00"));
		
		ped1.setPagamento(pg1);
		ped2.setPagamento(pg2);
		
		cliente.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRespository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pg1,pg2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00D, 1, 2000.00D);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00D, 1, 80.00D);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100D, 1, 500.00D);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}
}
