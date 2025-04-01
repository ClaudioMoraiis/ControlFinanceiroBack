package com.controleFinanceiro.ControleFinanceiro.dto;

import java.math.BigDecimal;
import java.util.Map;

public class ContaTotalDTO extends ContasDTO {
    private Map<String, BigDecimal> totais;

    public ContaTotalDTO(Map<String, BigDecimal> totais){
        this.totais = totais;
    }

    public Map<String, BigDecimal> getTotais() {
        return totais;
    }

    public void setTotais(Map<String, BigDecimal> totais) {
        this.totais = totais;
    }
}
