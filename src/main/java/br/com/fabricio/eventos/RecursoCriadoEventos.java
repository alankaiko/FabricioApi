package br.com.fabricio.eventos;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEventos extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private HttpServletResponse resposta;
	private Long id;
	private String codigo;

	public RecursoCriadoEventos(Object obj, HttpServletResponse resposta, Long id) {
		super(obj);
		this.resposta = resposta;
		this.id = id;
	}
	
	public RecursoCriadoEventos(Object obj, HttpServletResponse resposta, String codigo) {
		super(obj);
		this.resposta = resposta;
		this.codigo = codigo;
	}

	public HttpServletResponse getResposta() {
		return resposta;
	}

	public Long getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
