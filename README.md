# Spring Boot com Spring Cloud

Projeto tem por objetivo mostrar integração entre microserviços utilizando o spring cloud.
Utiliza-se o conceito de config-server para armazenar de forma centralizada as configurações dos microserviços.
Usa-se também o conceito de service-discovery para registrar as aplicações e fornece um load-balance para as mesmas.
Na presente arquitetura também temos a utilização de gateway para externalizar os recursos para o mundo externo.
E por final dois pequenos microserviços para cadastros de produtos e carrinho de compras, o primeiro tendo como repositório de dados um elasticsearch e o último um redis.

# Guia de Inicialização Rápido

## 1 - Config Server
Para inicializar o config server deveremos utilizar os seguintes comandos:

```
$ cd config-server
$ ./mvnw spring-boot:run
``` 

O serviço está configurado para subir na porta 8888.
Para visualizar as configurações recuperadas do repositório git e que serão disponibilizadas para os clientes do servidor de configurações.
Abaixo temos a listagem dos recursos disponibilizados para cada aplicação do sistema.

 * http://localhost:8888/config-server/default
 * http://localhost:8888/gateway/default
 * http://localhost:8888/product-catalog/default  
 * http://localhost:8888/service-discovery/default  
 * http://localhost:8888/shopping-cart/default

## 2 - Service Discovery

Para inicializar o config server deveremos utilizar os seguintes comandos:

```
$ cd gateway
$ ./mvnw spring-boot:run
```

 * http://localhost:9000

## 3 - Gateway

Para inicializar o gateway deveremos utilizar os seguintes comandos:

```
$ cd gateway
$ ./mvnw spring-boot:run
```

## 4 - Product Catalog

Para inicializar o product-catalog deveremos utilizar os seguintes comandos:

```
$ cd product-catalog
$ ./mvnw spring-boot:run
```

 * http://localhost:8081/products

## 5 - Shopping Cart

Para inicializar o shopping-cart deveremos utilizar os seguintes comandos:

```
$ cd shopping-cart
$ ./mvnw spring-boot:run
```
 * http://localhost:8082/carts

