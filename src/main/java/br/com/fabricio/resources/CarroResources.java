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
import br.com.fabricio.modelo.Carro;
import br.com.fabricio.repository.filtro.CarroFiltro;
import br.com.fabricio.service.CarroService;

@RestController
@RequestMapping("/carros")
@CrossOrigin("*")
public class CarroResources {
	@Autowired
	private CarroService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Carro> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Carro> Resumir(CarroFiltro filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	
	@PostMapping
	public ResponseEntity<Carro> Salvar(@Valid @RequestBody Carro carro, HttpServletResponse resposta){
		Carro salvo = this.service.Criar(carro);
		this.publisher.publishEvent(new RecursoCriadoEventos(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Carro> PorId(@PathVariable Long codigo){
		Carro salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Carro> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Carro carro){
		Carro salvo = this.service.Atualizar(codigo, carro);
		return ResponseEntity.ok(salvo);
	}
}
