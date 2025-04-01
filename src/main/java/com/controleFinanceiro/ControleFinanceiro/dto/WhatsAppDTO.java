package com.controleFinanceiro.ControleFinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class WhatsAppDTO {
    @NotNull(message = "Campo numero não informado")
    @JsonProperty("numero")
    private String numero;

    @NotNull(message = "Campo mensagem não informado")
    @JsonProperty("mensagem")
    private String mensagem;

    public WhatsAppDTO(String numero, String mensagem) {
        this.numero = numero;
        this.mensagem = mensagem;
    }
    public WhatsAppDTO(){}


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof WhatsAppDTO that)) return false;

        return numero.equals(that.numero) && mensagem.equals(that.mensagem);
    }

    @Override
    public int hashCode() {
        int result = numero.hashCode();
        result = 31 * result + mensagem.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WhatsAppDTO{" +
                "numero='" + numero + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
