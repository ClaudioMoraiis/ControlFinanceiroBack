package com.controleFinanceiro.ControleFinanceiro.vo;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTAS")
public class ContasVO {	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)	
	private Long con_id;

	@ManyToOne
	@JoinColumn(name = "con_user_id", referencedColumnName = "usu_id", nullable = false)
    private UsuarioVO usuario;
    
	@Column(name = "con_nome")
	private String con_nome;
	
	@Column(name = "con_valor", precision = 10, scale = 2)
	private BigDecimal con_valor;
	
	@Column(name = "con_tipo")
	private String con_tipo;
	
	public ContasVO(String con_nome, BigDecimal con_valor, String con_tipo) {
		this.con_nome = con_nome;
		this.con_valor = con_valor;
		this.con_tipo = con_tipo;
	}
		
	public ContasVO() {};
	
	@PrePersist
	@PreUpdate
	public void preProcess() {
		if (con_nome != null) {
			con_nome = con_nome.toUpperCase();			
		}
		
		if (con_tipo != null) {
			con_tipo = con_tipo.toUpperCase();			
		}	
	}	
	
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
	public BigDecimal getCon_valor() {
		return con_valor;
	}
	public void setCon_valor(BigDecimal con_valor) {
		this.con_valor = con_valor;
	}
	public String getCon_tipo() {
		return con_tipo;
	}
	public void setCon_tipo(String con_tipo) {
		this.con_tipo = con_tipo;
	}
	

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
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
