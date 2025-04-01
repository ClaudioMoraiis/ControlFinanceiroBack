package com.controleFinanceiro.ControleFinanceiro.services;

import com.controleFinanceiro.ControleFinanceiro.dto.WhatsAppDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppService {
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppService.class);

    @Value("${whatsapp.api.url}")
    private String whatsappApiUrl;

    @Value("${whatsapp.api.token}")
    private String accessToken;

    public void enviarMensagem(WhatsAppDTO whatsAppDTO) {
        if (whatsAppDTO.getNumero() == null || whatsAppDTO.getMensagem() == null) {
            throw new IllegalArgumentException("Número e mensagem são obrigatórios");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messaging_product", "whatsapp");
        requestBody.put("to", whatsAppDTO.getNumero());
        requestBody.put("type", "text");

        Map<String, String> text = new HashMap<>();
        text.put("body", whatsAppDTO.getMensagem());
        requestBody.put("text", text);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    whatsappApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Mensagem enviada com sucesso para {}", whatsAppDTO.getNumero());
            } else {
                logger.error("Erro ao enviar mensagem: {}", response.getBody());
                throw new RuntimeException("Falha ao enviar mensagem: " + response.getBody());
            }

            logger.info("Resposta completa: {}", response.getBody());
        } catch (Exception e) {
            logger.error("Erro na comunicação com a API do WhatsApp", e);
            throw new RuntimeException("Erro ao comunicar com a API do WhatsApp", e);
        }

    }
}
