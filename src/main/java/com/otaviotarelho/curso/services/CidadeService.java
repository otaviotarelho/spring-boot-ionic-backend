package com.otaviotarelho.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Cidade;
import com.otaviotarelho.curso.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findAll(Integer id){
		return cidadeRepository.findCidades(id);
	}
	
}
