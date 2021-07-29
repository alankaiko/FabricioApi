package br.com.fabricio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.modelo.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long>{

}
