package br.com.fabricio.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fabricio.modelo.Marca;
import br.com.fabricio.repository.MarcaRepository;
import br.com.fabricio.repository.filtro.MarcaFiltro;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(MarcaService.class);
	
	public List<Marca> Listar() {
		return this.dao.findAll();
	}

	public Marca Criar(Marca marca) {
		try {
			return this.dao.save(marca);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de MarcaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Marca BuscarPorId(Long id) {
		Optional<Marca> marca = this.dao.findById(id);

		if (marca.get() == null)
			throw new EmptyResultDataAccessException(1);

		return marca.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de MarcaService");
			e.printStackTrace();
		}
	}

	public void Deletar(Marca marca) {
		try {
			this.dao.delete(marca);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de MarcaService");
			e.printStackTrace();
		}
	}

	public Marca Atualizar(Long id, Marca marca) {
		try {
			Marca salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(marca, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de MarcaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de MarcaService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<Marca> Filtrando(MarcaFiltro filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de MarcaService");
			e.printStackTrace();
			return null;
		}	
	}

}
