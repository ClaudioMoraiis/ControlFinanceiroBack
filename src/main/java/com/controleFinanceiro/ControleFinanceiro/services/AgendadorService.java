package com.controleFinanceiro.ControleFinanceiro.services;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import com.controleFinanceiro.ControleFinanceiro.repositories.ContasRepository;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;
import com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendadorService {
    private final ContasRepository contasRepository;
    private final EmailService emailService;
    private final ContasService contasService;
    private final UsuarioRepository usuarioRepository;


    public AgendadorService(ContasRepository contasRepository, EmailService emailService, ContasService contasService,
                            UsuarioRepository usuarioRepository) {
        this.contasRepository = contasRepository;
        this.emailService = emailService;
        this.contasService = contasService;
        this.usuarioRepository = usuarioRepository;
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void limparContasVencidas() {
        LocalDate data = LocalDate.now().minusMonths(6);
        contasRepository.excluirRegistrosVencidos(data);
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void enviarEmailUsuario() {
        List<ContasDTO> listContas = contasService.buscarContasVencendo();
        for (ContasDTO contasDTO : listContas) {
            Optional<UsuarioVO> usuarioVO = usuarioRepository.findById(contasDTO.getUsuarioID());
            if (usuarioVO.isPresent()) {
                try {
                    emailService.enviarEmailContasVencendo(
                            usuarioVO.get().getUsu_email(),
                            listContas.stream()
                                    .filter(conta -> conta.getUsuarioID().equals(usuarioVO.get().getUsu_id()))
                                    .collect(Collectors.toList())
                    );
                } catch (MessagingException e) {
                    throw new RuntimeException("Erro ao enviar e-mail", e);
                }
            }
        }
    }
}
