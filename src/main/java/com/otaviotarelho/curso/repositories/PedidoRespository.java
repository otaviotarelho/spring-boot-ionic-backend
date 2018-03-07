package com.otaviotarelho.curso.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.domain.Pedido;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Integer> {
	
	@Transactional(readOnly=true)
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}
