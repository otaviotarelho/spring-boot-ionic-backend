package com.otaviotarelho.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.otaviotarelho.curso.domain.Categoria;
import com.otaviotarelho.curso.dto.CategoriaDTO;
import com.otaviotarelho.curso.repositories.CategoriaRepository;
import com.otaviotarelho.curso.services.exceptions.DataIntegrityException;
import com.otaviotarelho.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("object não encontrado");
		}
		
		return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);			
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		PageRequest pageResquest = new PageRequest(page, linePerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageResquest);
	}
	
	public Categoria formDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
}
