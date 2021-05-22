package com.thiagoribeiro.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagoribeiro.algalog.domain.exception.NegocioException;
import com.thiagoribeiro.algalog.domain.model.Cliente;
import com.thiagoribeiro.algalog.domain.repository.ClienteRepository;

@Service
public class CatalogoClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
	}
	
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(clienteExist -> !clienteExist.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente com esse e-mail");
		}
		
		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
