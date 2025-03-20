package com.controleFinanceiro.ControleFinanceiro.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "CONTAS")
public class ContasVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
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

    @Column(name = "con_data")
    private LocalDate con_data;

    public LocalDate getCon_data() {
        return con_data;
    }

    public void setCon_data(LocalDate con_data) {
        this.con_data = con_data;
    }

    public ContasVO(String con_nome, BigDecimal con_valor, String con_tipo, LocalDate con_data) {
        this.con_nome = con_nome;
        this.con_valor = con_valor;
        this.con_tipo = con_tipo;
        this.con_data = con_data;
    }

    public ContasVO() {
    }
    ;

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
        return "ContasVO{" +
                "con_id=" + con_id +
                ", usuario=" + usuario +
                ", con_nome='" + con_nome + '\'' +
                ", con_valor=" + con_valor +
                ", con_tipo='" + con_tipo + '\'' +
                ", con_data=" + con_data +
                '}';
    }

    public Long getCon_id() {
        return con_id;
    }

    public void setCon_id(Long con_id) {
        this.con_id = con_id;
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
    public final boolean equals(Object o) {
        if (!(o instanceof ContasVO contasVO)) return false;

        return con_id.equals(contasVO.con_id) && usuario.equals(contasVO.usuario) && con_nome.equals(contasVO.con_nome) && con_valor.equals(contasVO.con_valor) && con_tipo.equals(contasVO.con_tipo) && con_data.equals(contasVO.con_data);
    }
}
