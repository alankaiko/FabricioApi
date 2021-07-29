package br.com.fabricio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.modelo.Carro;
import br.com.fabricio.repository.impl.CarroRepositoryQuery;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>, CarroRepositoryQuery{

}
