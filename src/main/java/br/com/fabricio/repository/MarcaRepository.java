package br.com.fabricio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.modelo.Marca;
import br.com.fabricio.repository.impl.MarcaRepositoryQuery;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>, MarcaRepositoryQuery{

}
