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

import br.com.fabricio.modelo.Carro;
import br.com.fabricio.repository.CarroRepository;
import br.com.fabricio.repository.filtro.CarroFiltro;

@Service
public class CarroService {
	@Autowired
	private CarroRepository dao;
	
	private final Logger LOG = LoggerFactory.getLogger(CarroService.class);
	
	public List<Carro> Listar() {
		return this.dao.findAll();
	}

	public Carro Criar(Carro carro) {
		try {
			return this.dao.save(carro);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Criar------------------ de CarroService");
			e.printStackTrace();
			return null;
		}		
	}

	public Carro BuscarPorId(Long id) {
		Optional<Carro> carro = this.dao.findById(id);

		if (carro.get() == null)
			throw new EmptyResultDataAccessException(1);

		return carro.get();
	}

	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CarroService");
			e.printStackTrace();
		}
	}

	public void Deletar(Carro carro) {
		try {
			this.dao.delete(carro);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Deletar------------------ de CarroService");
			e.printStackTrace();
		}
	}

	public Carro Atualizar(Long id, Carro carro) {
		try {
			Carro salvo = this.BuscarPorId(id);
			BeanUtils.copyProperties(carro, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Atualizar------------------ de CarroService");
			e.printStackTrace();
			return null;
		}		
	}

	public Long QuantidadeTotal() {
		try {
			return this.dao.count();
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo QuantidadeTotal------------------ de CarroService");
			e.printStackTrace();
			return null;
		}		
	}

	public Page<Carro> Filtrando(CarroFiltro filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			LOG.error("Erro ao executar o metodo Filtrando------------------ de CarroService");
			e.printStackTrace();
			return null;
		}	
	}

}
