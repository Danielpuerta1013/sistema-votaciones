package com.newInntech.votaciones.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API-REST - Sistema de votaciones",
                version = "1.0.0",
                description = "\"API RESTful para gestionar un sistema de votaciones.\\n\\n\" +\n" +
                        "                \"Permite registrar votantes, candidatos y emitir votos. Esta API ha sido desarrollada como parte de una prueba técnica para la empresa **New**, e incluye estadísticas de votación como el total de votos por candidato, porcentaje de votos y más.\",",
                contact = @Contact(
                        name = "Daniel Puerta",
                        email = "daniwar3@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
