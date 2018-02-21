package com.otaviotarelho.curso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Pedido;
import com.otaviotarelho.curso.repositories.PedidoRespository;
import com.otaviotarelho.curso.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRespository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("object não encontrado");
		}
		
		return obj;
	}
}
