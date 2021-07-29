package br.com.fabricio.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fabricio.eventos.RecursoCriadoEventos;
import br.com.fabricio.modelo.Modelo;
import br.com.fabricio.repository.filtro.ModeloFiltro;
import br.com.fabricio.service.ModeloService;

@RestController
@RequestMapping("/modelos")
@CrossOrigin("*")
public class ModeloResources {
	@Autowired
	private ModeloService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Modelo> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Modelo> Resumir(ModeloFiltro filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<Modelo> Salvar(@Valid @RequestBody Modelo modelo, HttpServletResponse resposta){
		Modelo salvo = this.service.Criar(modelo);
		this.publisher.publishEvent(new RecursoCriadoEventos(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Modelo> PorId(@PathVariable Long codigo){
		Modelo salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Modelo> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Modelo modelo){
		Modelo salvo = this.service.Atualizar(codigo, modelo);
		return ResponseEntity.ok(salvo);
	}
}
