package com.otaviotarelho.curso.dto;

import java.io.Serializable;

import com.otaviotarelho.curso.domain.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	
	public EstadoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
