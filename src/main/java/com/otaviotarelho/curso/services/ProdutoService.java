package com.otaviotarelho.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Categoria;
import com.otaviotarelho.curso.domain.Produto;
import com.otaviotarelho.curso.repositories.CategoriaRepository;
import com.otaviotarelho.curso.repositories.ProdutoRepository;
import com.otaviotarelho.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("object não encontrado");
		}
		
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageResquest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageResquest);
	}
}
