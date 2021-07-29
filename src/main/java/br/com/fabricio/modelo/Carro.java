package br.com.fabricio.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Carro {
	private Long codigo;
	private Modelo modelo;
	private String anomodelo;
	private String cor;
	private String valorveiculo;
	private String valorfipe;
	private String placaveiculo;
	private String quilometragem;
	private String observacao;
	private boolean blindado;
	private String versao;
	private List<Imagem> imagens;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	@JoinColumn(name = "tbl_modelo_id", referencedColumnName = "codigo")
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getAnomodelo() {
		return anomodelo;
	}

	public void setAnomodelo(String anomodelo) {
		this.anomodelo = anomodelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getValorveiculo() {
		return valorveiculo;
	}

	public void setValorveiculo(String valorveiculo) {
		this.valorveiculo = valorveiculo;
	}

	public String getValorfipe() {
		return valorfipe;
	}

	public void setValorfipe(String valorfipe) {
		this.valorfipe = valorfipe;
	}

	public String getPlacaveiculo() {
		return placaveiculo;
	}

	public void setPlacaveiculo(String placaveiculo) {
		this.placaveiculo = placaveiculo;
	}

	public String getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isBlindado() {
		return blindado;
	}

	public void setBlindado(boolean blindado) {
		this.blindado = blindado;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "carro")
	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	@Override
	public String toString() {
		return "Carro [codigo=" + codigo + ", modelo=" + modelo + ", anomodelo=" + anomodelo + ", cor=" + cor
				+ ", valorveiculo=" + valorveiculo + ", valorfipe=" + valorfipe + ", placaveiculo=" + placaveiculo
				+ ", quilometragem=" + quilometragem + ", observacao=" + observacao + ", blindado=" + blindado
				+ ", versao=" + versao + ", imagens=" + imagens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
