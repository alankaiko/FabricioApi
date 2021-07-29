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

import br.com.fabricio.modelo.Modelo;
import br.com.fabricio.repository.ModeloRepository;
import br.com.fabricio.repository.filtro.ModeloFiltro;

@Service
public class ModeloService {
	@Autowired
	private ModeloRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(ModeloService.class);
	
	public List<Modelo> Listar() {
		return this.dao.findAll();
	}

	public Modelo Criar(Modelo modelo) {
		try {
			return this.dao.save(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de ModeloService");
			e.printStackTrace();
			return null;
		}		
	}

	public Modelo BuscarPorId(Long id) {
		Optional<Modelo> modelo = this.dao.findById(id);

		if (modelo.get() == null)
			throw new EmptyResultDataAccessException(1);

		return modelo.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloService");
			e.printStackTrace();
		}
	}

	public void Deletar(Modelo modelo) {
		try {
			this.dao.delete(modelo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de ModeloService");
			e.printStackTrace();
		}
	}

	public Modelo Atualizar(Long id, Modelo modelo) {
		try {
			Modelo salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(modelo, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de ModeloService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de ModeloService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<Modelo> Filtrando(ModeloFiltro filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de ModeloService");
			e.printStackTrace();
			return null;
		}	
	}

}
