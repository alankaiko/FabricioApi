package br.com.fabricio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.modelo.Modelo;
import br.com.fabricio.repository.impl.ModeloRepositoryQuery;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long>, ModeloRepositoryQuery {

}
