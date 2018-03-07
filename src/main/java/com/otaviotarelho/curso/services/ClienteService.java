package com.otaviotarelho.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Cidade;
import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.domain.Endereco;
import com.otaviotarelho.curso.domain.enums.Perfil;
import com.otaviotarelho.curso.domain.enums.TipoCliente;
import com.otaviotarelho.curso.dto.ClienteDTO;
import com.otaviotarelho.curso.dto.ClienteNewDTO;
import com.otaviotarelho.curso.repositories.CidadeRepository;
import com.otaviotarelho.curso.repositories.ClienteRepository;
import com.otaviotarelho.curso.repositories.EnderecoRepository;
import com.otaviotarelho.curso.security.UserSpringSecurity;
import com.otaviotarelho.curso.services.exceptions.AuthorizationException;
import com.otaviotarelho.curso.services.exceptions.DataIntegrityException;
import com.otaviotarelho.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	public Cliente find(Integer id) {
		
		UserSpringSecurity user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		Cliente obj = repo.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("object não encontrado");
		}
		
		return obj;
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}


	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);			
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir há entidades relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageResquest = new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageResquest);
	}
	
	public Cliente formDTO(ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente formDTO(ClienteNewDTO objDTO) {
		 Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), passwordEnconder.encode(objDTO.getSenha()));
		 Cidade cidade = cidadeRepository.getOne(objDTO.getCidade());
		 Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		 cliente.getEnderecos().add(endereco);
		 cliente.getTelefones().add(objDTO.getTelefone1());
		 
		 if(objDTO.getTelefone2() != null) {
			 cliente.getTelefones().add(objDTO.getTelefone2());
		 }
		 
		 if(objDTO.getTelefone3() != null) {
			 cliente.getTelefones().add(objDTO.getTelefone3());
		 }
		 
		 return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
