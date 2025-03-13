package com.controleFinanceiro.ControleFinanceiro.services;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioToken;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioTokenRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioTokenRepository usuarioTokenRepository;

	private final JavaMailSender mailSender;
	public ResponseEntity<?> enviarEmailRecuperacaoSenha;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public ResponseEntity<?> enviarEmailRecuperacaoSenha(String para) throws MessagingException {
		Optional<UsuarioVO> usuarioVO = Optional.ofNullable(repository.findByEmail(para));
		String getTokenAtivo = usuarioTokenRepository.getTokenAtivo(para, LocalDateTime.now());
		Optional<UsuarioToken> existeToken = Optional.ofNullable(usuarioTokenRepository.findByEmail(para));

		if (getTokenAtivo != null) {
			return ResponseEntity.ok("E-mail já enviado, verifique");
		}

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
		usuario.setUsu_password(novaSenha);
		repository.save(usuario);

		return ResponseEntity.ok("Senha redefinida com sucesso!");
	}	
	
	
	public ResponseEntity<?> findUserByEmail(String email){
		UsuarioVO usuarioVO = new UsuarioVO();		
		usuarioVO = repository.findByEmail(email);		
		
		return ResponseEntity.ok(usuarioVO.getUsu_name());
	}



}
