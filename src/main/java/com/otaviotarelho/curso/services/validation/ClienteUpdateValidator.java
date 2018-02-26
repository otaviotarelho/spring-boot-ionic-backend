package com.otaviotarelho.curso.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.otaviotarelho.curso.domain.Cliente;
import com.otaviotarelho.curso.domain.enums.TipoCliente;
import com.otaviotarelho.curso.dto.ClienteDTO;
import com.otaviotarelho.curso.repositories.ClienteRepository;
import com.otaviotarelho.curso.resources.exceptions.FieldMessage;
import com.otaviotarelho.curso.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
 		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if(cliente != null && !cliente.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
