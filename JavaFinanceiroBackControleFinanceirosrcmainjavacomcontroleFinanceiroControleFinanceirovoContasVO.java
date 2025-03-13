[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/ContasRota.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/ContasRota.java[m
[1mindex 246566b..fde6a6b 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/ContasRota.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/ContasRota.java[m
[36m@@ -8,9 +8,8 @@[m [mimport org.springframework.web.bind.annotation.RequestBody;[m
 import org.springframework.web.bind.annotation.RequestMapping;[m
 import org.springframework.web.bind.annotation.RestController;[m
 [m
[31m-import com.controleFinanceiro.ControleFinanceiro.DTO.ContasDTO;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.Services.ContasService;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.VO.ContasVO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.dto.ContasDTO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.services.ContasService;[m
 [m
 import jakarta.validation.Valid;[m
 [m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/UsuarioRota.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/UsuarioRota.java[m
[1mindex 920fc2b..5d34436 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/UsuarioRota.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/controller/UsuarioRota.java[m
[36m@@ -2,14 +2,11 @@[m [mpackage com.controleFinanceiro.ControleFinanceiro.controller;[m
 [m
 import java.security.NoSuchAlgorithmException;[m
 import java.util.Map;[m
[31m-import java.util.Optional;[m
[31m-import java.util.UUID;[m
 [m
 import org.springframework.beans.factory.annotation.Autowired;[m
 import org.springframework.http.ResponseEntity;[m
 import org.springframework.validation.annotation.Validated;[m
 import org.springframework.web.bind.annotation.GetMapping;[m
[31m-import org.springframework.web.bind.annotation.PathVariable;[m
 import org.springframework.web.bind.annotation.PostMapping;[m
 import org.springframework.web.bind.annotation.PutMapping;[m
 import org.springframework.web.bind.annotation.RequestBody;[m
[36m@@ -17,14 +14,13 @@[m [mimport org.springframework.web.bind.annotation.RequestMapping;[m
 import org.springframework.web.bind.annotation.RequestParam;[m
 import org.springframework.web.bind.annotation.RestController;[m
 [m
[31m-import com.controleFinanceiro.ControleFinanceiro.DTO.UsuarioLoginDTO;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.Services.EmailService;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.Services.UsuarioService;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.dto.UsuarioLoginDTO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.services.EmailService;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.services.UsuarioService;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;[m
 [m
 import jakarta.mail.MessagingException;[m
 import jakarta.validation.Valid;[m
[31m-import jakarta.websocket.server.PathParam;[m
 [m
 @Validated[m
 @RestController[m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/ContasDTO.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/ContasDTO.java[m
[1mindex 7640c1b..1f43e8d 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/ContasDTO.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/ContasDTO.java[m
[36m@@ -1,21 +1,27 @@[m
[31m-package com.controleFinanceiro.ControleFinanceiro.DTO;[m
[32m+[m[32mpackage com.controleFinanceiro.ControleFinanceiro.dto;[m
 [m
 import java.math.BigDecimal;[m
 import java.util.Objects;[m
 [m
[32m+[m[32mimport com.fasterxml.jackson.annotation.JsonProperty;[m
[32m+[m
 import jakarta.validation.constraints.NotNull;[m
 [m
 public class ContasDTO {[m
 	[m
[32m+[m	[32m@JsonProperty("nome")[m
 	@NotNull(message = "Campo ''nome'' no body deve ser informado")[m
 	private String nome;[m
 	[m
[32m+[m	[32m@JsonProperty("valor")[m
 	@NotNull(message = "Campo ''valor'' no body deve ser informado")[m
 	private BigDecimal valor;[m
 	[m
[32m+[m	[32m@JsonProperty("tipo")[m
 	@NotNull(message = "Campo ''tipo'' no body deve ser informado")[m
 	private String tipo;[m
 	[m
[32m+[m	[32m@JsonProperty("usuarioID")[m
 	@NotNull(message = "Campo ''usuarioId'' no body deve ser informado")[m
 	private Long usuarioID;[m
 	[m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/UsuarioLoginDTO.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/UsuarioLoginDTO.java[m
[1mindex e6aa5ca..07455cd 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/UsuarioLoginDTO.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/dto/UsuarioLoginDTO.java[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.controleFinanceiro.ControleFinanceiro.DTO;[m
[32m+[m[32mpackage com.controleFinanceiro.ControleFinanceiro.dto;[m
 [m
 import com.fasterxml.jackson.annotation.JsonProperty;[m
 [m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/globalExeptionHandler/GlobalExceptionHandler.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/globalExeptionHandler/GlobalExceptionHandler.java[m
[1mindex 9fb0d7d..43edb5f 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/globalExeptionHandler/GlobalExceptionHandler.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/globalExeptionHandler/GlobalExceptionHandler.java[m
[36m@@ -1,4 +1,4 @@[m
[31m-package com.controleFinanceiro.ControleFinanceiro.GlobalExeptionHandler;[m
[32m+[m[32mpackage com.controleFinanceiro.ControleFinanceiro.globalExeptionHandler;[m
 [m
 import org.springframework.http.HttpStatus;[m
 import org.springframework.http.ResponseEntity;[m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/ContasRepository.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/ContasRepository.java[m
[1mindex 3d68d51..05912d1 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/ContasRepository.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/ContasRepository.java[m
[36m@@ -2,8 +2,7 @@[m [mpackage com.controleFinanceiro.ControleFinanceiro.repositories;[m
 [m
 import org.springframework.data.jpa.repository.JpaRepository;[m
 [m
[31m-import com.controleFinanceiro.ControleFinanceiro.VO.ContasVO;[m
[31m-import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.vo.ContasVO;[m
 [m
 public interface ContasRepository extends JpaRepository<ContasVO, Long> {[m
 	[m
[1mdiff --git a/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/UsuarioRepository.java b/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/UsuarioRepository.java[m
[1mindex e26efbc..e447c73 100644[m
[1m--- a/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/UsuarioRepository.java[m
[1m+++ b/src/main/java/com/controleFinanceiro/ControleFinanceiro/repositories/UsuarioRepository.java[m
[36m@@ -1,12 +1,10 @@[m
 package com.controleFinanceiro.ControleFinanceiro.repositories;[m
 [m
[31m-import java.util.Optional;[m
[31m-[m
 import org.springframework.data.jpa.repository.JpaRepository;[m
 import org.springframework.data.jpa.repository.Query;[m
 import org.springframework.data.repository.query.Param;[m
 [m
[31m-import com.controleFinanceiro.ControleFinanceiro.VO.UsuarioVO;[m
[32m+[m[32mimport com.controleFinanceiro.ControleFinanceiro.vo.UsuarioVO;[m
 [m
 public interface UsuarioRepository extends JpaRepository<UsuarioVO, Long> {[m
 	@Query("SELECT u FROM UsuarioVO u WHERE u.usu_name = :name ORDER BY u.id