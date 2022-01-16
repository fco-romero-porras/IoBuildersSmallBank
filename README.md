# IoBuildersSmallBank
## Requerimientos
API Rest para simular un pequeño banco:  
- Registro usuario  
- Creación de cuenta (wallet)  
- Realización de depósito de dinero  
- Visualización de cuenta (wallet) --> Balance y movimientos  
- Transferencia de una cuenta A a una cuenta B  
  
Puntos a destacar:  
- **_Arquitectura hexagonal y testing (Obligatorio)_**  
- Libertad en el stack usado en la prueba, aunque preferiblemente algún lenguaje de la JVM, Java, Groovy o haciendo uso de Spring, Micronaut o Quarkus.  
- Conceptos DDD  
- Base de datos (puedes usar una base de datos simulada en memoria, H2 en memoria, etc)  
- Parte Blockchain (no requerida, opcional).  
Si el candidato quiere introducir parte de Blockchain, podria utilizar Truffle para generar los contratos y usarlos desde Java y Ganache para su despliegue.  
[https://www.trufflesuite.com/docs/truffle/quickstart](https://www.trufflesuite.com/docs/truffle/quickstart)  
[https://www.trufflesuite.com/ganache](https://www.trufflesuite.com/ganache)  
  
Para entregarla, puedes subir el código a un repositorio de Github público y mandarnos dicho enlace cuando lo tengas realizado.

## Solución Propuesta
La solución propuesta para la prueba se dividi en el Backend offChain y los SmartContracts onChain.

#### Backend offChain
Esta capa es la encargada de recibir las peticiones REST, aplicarles logica y trasladarlas a los SmartContracts.
Esta desarrollada en Java con SpringBoot y la librería Web3j.

Los datos de aplicación estaran almacenados en una base de datos PostgreSql levantada sobre un Docker.

Las funcionalidades de la API son las siguientes:
- Alta de usuario: Que se encarga de crear un usuairo nuevo.
`bash curl -X POST -d" username = user4 & password = pwd "http: // localhost: 8081 / user`
- Listado de usuarios: `bash curl -i -H" Aceptar: aplicación / json "-H" Tipo de contenido: aplicación / json "-X OBTENER http: // localhost: 8081 / user / all`
- Busqueda de usuario por nombre: `bash curl -i -H" Aceptar: aplicación / json "-H" Tipo de contenido: aplicación / json "-X OBTENER http: // localhost: 8081 / user / user1`
- Intercambio: Que se encarga de proporcionar Ether al usuario.
`bash curl -X POST http: // localhost: 8081 / user / user1 / exchange / 10`
- Compra `bash curl -X POST http: // localhost: 8081 / user / user1 / buyIobTokens / 10`
- Retirada `bash curl -X POST http: // localhost: 8081 / user / user1 / retirew / 5`
- Transferencia `bash curl -X POST http: // localhost: 8081 / user / user1 / transfer / 5 / user2`


#### SmartContracts onChain
Los contratos tienen la logica financiera del banco, y estan desplegados en una testNet de Ganache.

La solución tiene los siguientes SmartContracts: 
- **IoCoin** un contratado basado en ERC20 de Openzeppelin y agrega algunas funcionalidades para la creación y asignación de tokens que se pueden ejecutar por el owner del contrato.

- **IoWallet** un contrato que gestiona la operativa financiera de compra venta de los IoCoin.