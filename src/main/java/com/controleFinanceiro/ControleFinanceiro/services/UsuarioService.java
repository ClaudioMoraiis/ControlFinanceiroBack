package com.controleFinanceiro.ControleFinanceiro.services;

import java.security.NoSuchAlgorithmException;

import com.controleFinanceiro.ControleFinanceiro.user.UserROle;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.controleFinanceiro.ControleFinanceiro.dto.UsuarioLoginDTO;
import com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;
import com.controleFinanceiro.ControleFinanceiro.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;


    public ResponseEntity<?> insertUser(@RequestBody @Valid UsuarioVO mUsuarioVO) throws NoSuchAlgorithmException {
        if (repository.findByEmail(mUsuarioVO.getUsu_email()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado.");
        }

        String senhaCriptografada = passwordEncoder.encode(mUsuarioVO.getUsu_password());

        mUsuarioVO.setUsu_password(senhaCriptografada);
        mUsuarioVO.setUsu_role(UserROle.ADMIN);

        repository.save(mUsuarioVO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
    }

    public UsuarioVO findByName(String name) {
        return repository.findByUsuName(name);
    }

    public UsuarioVO findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO loginDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsu_email(), loginDTO.getUsu_password());
            var auth = authenticationManager.authenticate(authToken);

            var token = tokenService.generateToken((UsuarioVO) auth.getPrincipal());
            return ResponseEntity.ok("Login realizado com sucesso\n" + token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public ResponseEntity<?> getIdByEmail(String email) {
        Integer usuarioId = repository.getIdByEmail(email);
        if ((usuarioId == null) || (usuarioId == 0)) {
            ResponseEntity.ok("Nenhum usuário localizado com esse email");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioId);
    }

    public ResponseEntity<?> findUserByEmail(String email) {
        UsuarioVO usuarioVO = new UsuarioVO();
        usuarioVO = repository.findByEmail(email);

        return ResponseEntity.ok(usuarioVO.getUsu_name());
    }

}
