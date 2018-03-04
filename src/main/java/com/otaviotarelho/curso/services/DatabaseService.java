package com.otaviotarelho.curso.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DatabaseService {
	
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
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void instantiateDatabase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama, Mesae Banho");
		Categoria cat4 = new Categoria(null, "Eletronico");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoracao");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de Escritorio", 100.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV True Color", 1800.00);
		Produto p8 = new Produto(null, "Roçadeira", 80.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 100.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat2.getProdutos().addAll(Arrays.asList(p5,p6));
		cat2.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat2.getProdutos().addAll(Arrays.asList(p8));
		cat2.getProdutos().addAll(Arrays.asList(p9, p10));
		cat2.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		EstadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		
		Cliente cliente = new Cliente(null, "Maria Silva", "otarelho@ucdavis.edu", "11111111111", TipoCliente.PESSOAFISICA, passwordEncoder.encode("teste123"));
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
