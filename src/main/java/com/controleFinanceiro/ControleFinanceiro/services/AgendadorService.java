package com.controleFinanceiro.ControleFinanceiro.services;

import com.controleFinanceiro.ControleFinanceiro.repositories.ContasRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgendadorService {
    private final ContasRepository contasRepository;

    public AgendadorService(ContasRepository contasRepository){
        this.contasRepository = contasRepository;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void limparContasVencidas(){
        LocalDate data = LocalDate.now().minusMonths(3);
        contasRepository.excluirRegistrosVencidos(data);
    }
}
