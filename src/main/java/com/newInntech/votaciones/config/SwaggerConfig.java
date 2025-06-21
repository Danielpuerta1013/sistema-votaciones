package com.newInntech.votaciones.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API-REST - Sistema de votaciones",
                version = "1.0.0",
                description = """
                        API RESTful para gestionar un sistema de votaciones. 
                        Permite registrar votantes, candidatos y emitir votos. 
                        Esta API ha sido desarrollada como parte de una prueba técnica para la empresa **New Inntech**.

                        ### Autenticación:
                        - Tipo: Autenticación básica (HTTP Basic Auth)
                        - **Usuario:** `admin`
                        - **Contraseña:** `1234`
                        - Los endpoints de creación y eliminación requieren autenticación.

                        También puedes consultar estadísticas generales de votaciones sin necesidad de autenticarte.
                        """,
                contact = @Contact(
                        name = "Daniel Puerta",
                        email = "daniwar3@gmail.com",
                        url = "https://github.com/Danielpuerta1013/sistema-votaciones"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080/api",description = "servidor local"),

        }, security = {@SecurityRequirement(name = "basicAuth")}
)
@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
public class SwaggerConfig {
}
