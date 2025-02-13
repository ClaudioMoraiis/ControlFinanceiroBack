package com.controleFinanceiro.ControleFinanceiro.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UsuarioLoginDTO {

	@JsonProperty("email")
	@NotNull(message = "Email é obrigatório")
    @Email(message = "O e-mail informado não é válido.")
    private String usu_email;

	@JsonProperty("senha")
	@NotNull(message = "O campo 'senha' é obrigatório.")
    private String usu_password;

    // Getters e Setters
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
}
