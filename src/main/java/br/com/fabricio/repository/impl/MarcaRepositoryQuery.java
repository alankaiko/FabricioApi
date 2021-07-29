package br.com.fabricio.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fabricio.modelo.Marca;
import br.com.fabricio.repository.filtro.MarcaFiltro;

public interface MarcaRepositoryQuery {
	public Page<Marca> Filtrando(MarcaFiltro filtro, Pageable pageable);
}
