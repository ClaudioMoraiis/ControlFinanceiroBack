package com.controleFinanceiro.ControleFinanceiro.vo;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO_TOKEN")
public class UsuarioToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long uto_id;
    
    @ManyToOne
    @JoinColumn(name = "uto_user_id", referencedColumnName = "usu_id", nullable = false)
    private UsuarioVO usuario;
    
    @Column(name = "uto_token", nullable = false, unique = true)
    private String uto_token;
    
    @Column(name = "uto_dthr_expiracao", nullable = false)
    private LocalDateTime uto_dthr_expiracao;
    
    public UsuarioToken(UsuarioVO usuarioVO) {
        if (usuarioVO == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        this.usuario = usuarioVO;
        this.uto_token = UUID.randomUUID().toString();
        this.uto_dthr_expiracao = LocalDateTime.now().plusHours(1);
    }
    
    public UsuarioToken() {}
    
    public boolean isExpirado() {
        return LocalDateTime.now().isAfter(uto_dthr_expiracao);
    }

    public Long getUto_id() {
        return uto_id;
    }

    public void setUto_id(Long uto_id) {
        this.uto_id = uto_id;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public String getUto_token() {
        return uto_token;
    }

    public void setUto_token(String uto_token) {
        this.uto_token = uto_token;
    }

    public LocalDateTime getUto_dthr_expiracao() {
        return uto_dthr_expiracao;
    }

    public void setUto_dthr_expiracao(LocalDateTime uto_dthr_expiracao) {
        this.uto_dthr_expiracao = uto_dthr_expiracao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, uto_dthr_expiracao, uto_id, uto_token);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        UsuarioToken other = (UsuarioToken) obj;
        return Objects.equals(usuario, other.usuario) && Objects.equals(uto_dthr_expiracao, other.uto_dthr_expiracao)
                && Objects.equals(uto_id, other.uto_id) && Objects.equals(uto_token, other.uto_token);
    }

    @Override
    public String toString() {
        return "UsuarioToken [uto_id=" + uto_id + ", usuario=" + usuario + ", uto_token=" + uto_token
                + ", uto_dthr_expiracao=" + uto_dthr_expiracao + "]";
    }    
}
