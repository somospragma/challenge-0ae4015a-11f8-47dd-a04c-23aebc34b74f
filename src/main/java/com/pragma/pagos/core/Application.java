package com.pragma.pagos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 * Configura el escaneo de componentes para todas las capas del dominio.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.pragma.pagos.core"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

//