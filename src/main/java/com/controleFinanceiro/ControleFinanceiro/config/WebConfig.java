package com.controleFinanceiro.ControleFinanceiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "http://192.168.18.22:5173",
                        "https://controle-financeiro-gilt.vercel.app")
                .allowedMethods("*")
                .allowedHeaders("*") 
                .allowCredentials(true); 
    }
	
}
