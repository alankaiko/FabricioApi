package br.com.fabricio.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.fabricio.modelo.Carro;
import br.com.fabricio.repository.filtro.CarroFiltro;

public interface CarroRepositoryQuery {
	public Page<Carro> Filtrando(CarroFiltro filtro, Pageable pageable);
}
