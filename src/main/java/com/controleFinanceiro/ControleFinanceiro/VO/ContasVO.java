package com.controleFinanceiro.ControleFinanceiro.VO;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CONTAS")
public class ContasVO {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)	
	private Long con_id;
	
	@JsonProperty("idUsuario")
	@NotNull(message = "Campo 'idUsuario' no body deve ser informado")
    @ManyToOne
    @JoinColumn(name = "con_usu_id", referencedColumnName = "usu_id", nullable = false)
    private UsuarioVO usuario;
	
	@NotNull(message = "Campo 'nome' no body deve ser informado")
	@JsonProperty("nome")
	private String con_nome;
	
	@NotNull(message = "Campo 'valor' no body deve ser informado")
	@JsonProperty("valor")
	private Float con_valor;
	
	@NotNull(message = "Campo 'tipo' no body deve ser informado")
	@JsonProperty("tipo")
	private String con_tipo;
	
	public ContasVO(String con_nome, Float con_valor, String con_tipo) {
		this.con_nome = con_nome;
		this.con_valor = con_valor;
		this.con_tipo = con_tipo;
	}	
	
	public ContasVO() {};
	
	@Override
	public String toString() {
		return "ContasVO [con_nome=" + con_nome + ", con_valor=" + con_valor + ", con_tipo=" + con_tipo + "]";
	}	
	
	public String getCon_nome() {
		return con_nome;
	}
	public void setCon_nome(String con_nome) {
		this.con_nome = con_nome;
	}
	public Float getCon_valor() {
		return con_valor;
	}
	public void setCon_valor(Float con_valor) {
		this.con_valor = con_valor;
	}
	public String getCon_tipo() {
		return con_tipo;
	}
	public void setCon_tipo(String con_tipo) {
		this.con_tipo = con_tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(con_nome, con_tipo, con_valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContasVO other = (ContasVO) obj;
		return Objects.equals(con_nome, other.con_nome) && Objects.equals(con_tipo, other.con_tipo)
				&& Objects.equals(con_valor, other.con_valor);
	}	

}
