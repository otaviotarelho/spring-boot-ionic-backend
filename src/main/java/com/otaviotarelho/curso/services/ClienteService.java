package com.otaviotarelho.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.dto.ClienteDTO;
import com.otaviotarelho.curso.repositories.ClienteRepository;
import com.otaviotarelho.curso.services.exceptions.DataIntegrityException;
import com.otaviotarelho.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("object não encontrado");
		}
		
		return obj;
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
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
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
