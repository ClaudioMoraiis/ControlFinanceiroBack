package com.controleFinanceiro.ControleFinanceiro.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioToken;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioTokenRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import javax.swing.text.DateFormatter;

@Service
public class EmailService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioTokenRepository usuarioTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;
    public ResponseEntity<?> enviarEmailRecuperacaoSenha;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseEntity<?> enviarEmailRecuperacaoSenha(String para) throws MessagingException {
        Optional<UsuarioVO> usuarioVO = Optional.ofNullable(repository.findByEmail(para));

        if (usuarioVO.isEmpty()) {
            return ResponseEntity.ok(
                    "Se este e-mail estiver cadastrado, você receberá um e-mail com instruções para redefinir sua senha.");
        }

        try {
            UsuarioToken usuarioToken = new UsuarioToken(usuarioVO.get());
            String novoToken = UUID.randomUUID().toString();
            usuarioToken.setUto_token(novoToken);
            usuarioToken.setUto_dthr_expiracao(LocalDateTime.now().plusHours(1));
            usuarioTokenRepository.save(usuarioToken);

            String token = usuarioToken.getUto_token();

            String assunto = "Recuperação senha";
            String link = "http://192.168.18.22:5173/RedefinirSenha?token=" + token;
            String conteudo = "<p>Clique no link abaixo para redefinir sua senha:</p>" + "<p><a href=\"" + link
                    + "\">Redefinir Senha</a></p>";

            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(conteudo, true);

            mailSender.send(mensagem);

            return ResponseEntity.ok("E-mail enviado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar solicitação: " + e.getMessage());
        }
    }

    public ResponseEntity<?> redefinirSenha(Map<String, String> request) {
        String token = request.get("token");
        String novaSenha = request.get("senha");
        String confirmarSenha = request.get("confirmarSenha");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token ausente");
        }

        Optional<UsuarioToken> usuarioToken = usuarioTokenRepository.findByUtotoken(token);
        if (usuarioToken.isEmpty() || usuarioToken.get().getUto_dthr_expiracao().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token inválido ou expirado");
        }

        if (!novaSenha.equals(confirmarSenha)) {
            return ResponseEntity.badRequest().body("As senhas não coincidem");
        }

        UsuarioVO usuario = usuarioToken.get().getUsuario();
        String senhaCriptografada = passwordEncoder.encode(novaSenha);
        usuario.setUsu_password(senhaCriptografada);

        repository.save(usuario);
        usuarioToken.get().setUto_dthr_expiracao(LocalDateTime.now());

        return ResponseEntity.ok("Senha redefinida com sucesso!");
    }

    public void enviarEmailContasVencendo(String para, List<ContasDTO> listaContas) throws MessagingException {
        String assunto = "Contas próximo do vencimento";

        String templateEmail = """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        padding: 20px;
                    }
                    .container {
                        max-width: 600px;
                        background: white;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                        margin: auto;
                    }
                    h2 {
                        color: #333;
                    }
                    .details {
                        padding: 15px;
                        border: 1px solid #ddd;
                        border-radius: 5px;
                        background: #fafafa;
                        margin-bottom: 10px;
                    }
                    .footer {
                        text-align: center;
                        font-size: 12px;
                        color: #777;
                        margin-top: 20px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Atenção para as contas próximas do vencimento</h2>
                    <p>Prezado usuário,</p>
                    <p>Estas são as contas que estão próximas do vencimento:</p>
                    {DETALHES_CONTAS}
                    <div class="footer">
                        <p>Obrigado por utilizar nosso sistema!</p>
                        <p><strong>FinanceiroApp</strong> - Seu controle financeiro simplificado.</p>
                    </div>
                </div>
            </body>
            </html>
            """;

        StringBuilder detalhesContas = new StringBuilder();
        for (ContasDTO conta : listaContas) {
            LocalDate data = conta.getData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String formattedDate = data.format(formatter);
            detalhesContas.append("""
                <div class="details">
                    <p><strong>Conta:</strong> %s</p>
                    <p><strong>Valor:</strong> R$ %s</p>
                    <p><strong>Data de vencimento:</strong> %s</p>
                </div>
                """.formatted(conta.getNome(), conta.getValor(), formattedDate));
        }

        String mensagemEmail = templateEmail.replace("{DETALHES_CONTAS}", detalhesContas.toString());

        MimeMessage mensagem = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
        helper.setTo(para.toLowerCase());
        helper.setSubject(assunto);
        helper.setText(mensagemEmail, true); // Agora o conteúdo é HTML

        mailSender.send(mensagem);
    }
}
