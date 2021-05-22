package com.thiagoribeiro.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.thiagoribeiro.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.thiagoribeiro.algalog.domain.model.Entrega;
import com.thiagoribeiro.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
	
}