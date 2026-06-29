package com.example.crudrapido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudrapidoApplication {

	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack", "true"); //agregue esta linea por problemas del dns internos de java(Netty)
		SpringApplication.run(CrudrapidoApplication.class, args);
	}



}

/*
	
Cuando usas WebClient, no estás usando el cliente HTTP tradicional de Java, sino uno reactivo y asíncrono basado en Netty.

Netty tiene su propio sistema para traducir URLs a IPs, el cual es extremadamente rápido pero a veces demasiado estricto. Cuando le pediste a Netty que buscara jsonplaceholder.typicode.com, hizo lo siguiente:

Preguntó por el registro A (IPv4).

Preguntó por el registro AAAA (IPv6).

El problema ocurre cuando tu proveedor de internet (ISP), tu router o la configuración de red de tu sistema operativo tienen un soporte parcial, 
mal configurado o bloqueado para IPv6. 
Netty se queda esperando la respuesta del registro AAAA, se confunde, 
agota sus intentos (el after 3 queries que veías) y cancela toda la operación, 
incluso si ya tenía la dirección IPv4 lista para usar. */
