package com.controleFinanceiro.ControleFinanceiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContasDTO {
	@JsonProperty("con_id")
	private Long id;

	@JsonProperty("nomeConta")
	@NotNull(message = "Campo ''nomeConta'' no body deve ser informado")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public ContasDTO(Long id, String nome, BigDecimal valor, String tipo, Long usuarioID, LocalDate data) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.tipo = tipo;
		this.usuarioID = usuarioID;
		this.data = data;
	}

	public ContasDTO(){
	};

	public ContasDTO(String tipo, BigDecimal valor){
		this.tipo = tipo;
		this.valor = valor;
	};

	public void setNome(String nome) {
		this.nome = nome;
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public final boolean equals(Object o) {
		if (!(o instanceof ContasDTO contasDTO)) return false;

        return id.equals(contasDTO.id) && nome.equals(contasDTO.nome) && valor.equals(contasDTO.valor) && tipo.equals(contasDTO.tipo) && usuarioID.equals(contasDTO.usuarioID) && data.equals(contasDTO.data);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + nome.hashCode();
		result = 31 * result + valor.hashCode();
		result = 31 * result + tipo.hashCode();
		result = 31 * result + usuarioID.hashCode();
		result = 31 * result + data.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "ContasDTO{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", valor=" + valor +
				", tipo='" + tipo + '\'' +
				", usuarioID=" + usuarioID +
				", data=" + data +
				'}';
	}
}
