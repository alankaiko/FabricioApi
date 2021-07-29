package br.com.fabricio.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fabricio.modelo.Modelo;
import br.com.fabricio.repository.filtro.ModeloFiltro;

public interface ModeloRepositoryQuery {
	public Page<Modelo> Filtrando(ModeloFiltro filtro, Pageable pageable);
}
