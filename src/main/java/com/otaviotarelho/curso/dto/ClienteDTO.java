package com.otaviotarelho.curso.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message="Preenchimento do Nome obrigatório.")
	@Length(min=5, max=120, message="O tamanho do nome deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento do E-mail obrigatório.")
	@Email(message="E-mail inválido")
	private String email;
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getNome();
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
