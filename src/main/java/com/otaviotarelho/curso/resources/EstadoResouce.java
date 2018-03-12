package com.otaviotarelho.curso.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.otaviotarelho.curso.domain.Cidade;
import com.otaviotarelho.curso.domain.Estado;
import com.otaviotarelho.curso.dto.CidadeDTO;
import com.otaviotarelho.curso.dto.EstadoDTO;
import com.otaviotarelho.curso.services.CidadeService;
import com.otaviotarelho.curso.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResouce {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> estados = estadoService.findAll();
		
		List<EstadoDTO> list = estados.stream().map(x-> new EstadoDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{estado_id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estado_id) {
		List<Cidade> cidades = cidadeService.findAll(estado_id);
		
		List<CidadeDTO> list = cidades.stream().map(x-> new CidadeDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
}
