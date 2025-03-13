package com.controleFinanceiro.ControleFinanceiro.VO;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO")
public class UsuarioVO {
	
	public UsuarioVO(Long usu_id, String usu_name, String usu_email, String usu_password) {
		this.usu_id = usu_id;
		this.usu_name = usu_name;
		this.usu_email = usu_email;
		this.usu_password = usu_password;
	}
	
	public UsuarioVO() {}	
	
	@PrePersist
	@PreUpdate
	public void preProcess() {
		if (usu_name != null) {
			usu_name.toUpperCase();			
		}
		
		if (usu_email != null) {
			usu_email.toUpperCase();			
		}	
	}

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)	
	private Long usu_id;
	
	@NotNull(message = "Campo ''nome'' no body deve ser informado")
	@JsonProperty("nome")
	@Column(name = "usu_name")
	private String usu_name;
	
	@NotNull(message = "Campo ''email'' no body deve ser informado")
	@Email(message = "Email deve ser válido")
	@JsonProperty("email")
	@Column(name = "usu_email")
	private String usu_email;
	
	@NotNull(message = "Campo ''senha'' no body deve ser informado")
	@JsonProperty("senha")
	@Column(name = "usu_password")
	private String usu_password;

	@Override
	public int hashCode() {
		return Objects.hash(usu_password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(usu_password, other.usu_password);
	}	
	
	
	public Long getUsu_id() {
		return usu_id;
	}
	public void setUsu_id(Long usu_id) {
		this.usu_id = usu_id;
	}
	public String getUsu_name() {
		return usu_name;
	}
	public void setUsu_name(String usu_name) {
		this.usu_name = usu_name;
	}
	public String getUsu_email() {
		return usu_email;
	}
	public void setUsu_email(String usu_email) {
		this.usu_email = usu_email;
	}
	public String getUsu_password() {
		return usu_password;
	}
	public void setUsu_password(String usu_password) {
		this.usu_password = usu_password;
	}
	
	@Override
	public String toString() {
		return "UsuarioVO [usu_id=" + usu_id + ", usu_name=" + usu_name + ", usu_email=" + usu_email + ", usu_password="
				+ usu_password + "]";
	}
	
	
	
	
	
	

}
