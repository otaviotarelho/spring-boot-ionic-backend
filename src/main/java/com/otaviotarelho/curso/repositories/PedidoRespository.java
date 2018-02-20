package com.otaviotarelho.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otaviotarelho.curso.domain.Pedido;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Integer> {

}
