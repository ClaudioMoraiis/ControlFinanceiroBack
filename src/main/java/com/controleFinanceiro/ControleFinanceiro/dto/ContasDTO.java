package com.controleFinanceiro.ControleFinanceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ContasDTO {
	
	@JsonProperty("nome")
	@NotNull(message = "Campo ''nome'' no body deve ser informado")
	private String nome;
	
	@JsonProperty("valor")
	@NotNull(message = "Campo ''valor'' no body deve ser informado")
	private BigDecimal valor;
	
	@JsonProperty("tipo")
	@NotNull(message = "Campo ''tipo'' no body deve ser informado")
	private String tipo;
	
	@JsonProperty("usuarioID")
	@NotNull(message = "Campo ''usuarioId'' no body deve ser informado")
	private Long usuarioID;

	@JsonProperty("data")
	@NotNull(message = "Campo ''data'' no body deve ser informado")
	private LocalDate data;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public ContasDTO(String nomeConta, BigDecimal valor, String tipo, Long usuarioID, LocalDate data) {
		this.nome = nomeConta;
		this.valor = valor;
		this.tipo = tipo;
		this.usuarioID = usuarioID;
		this.data = data;
	}
	
	public ContasDTO() {};
	
	public String getNomeConta() {
		return nome;
	}
	
	public void setNomeConta(String nomeConta) {
		this.nome = nomeConta;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Long getUsuarioID() {
		return usuarioID;
	}
	
	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	@Override
	public String toString() {
		return "ContasDTO [nomeConta=" + nome + ", valor=" + valor + ", tipo=" + tipo + ", usuarioID=" + usuarioID
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, tipo, usuarioID, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContasDTO other = (ContasDTO) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(usuarioID, other.usuarioID) && Objects.equals(valor, other.valor);
	}


	
	
	
	

}
