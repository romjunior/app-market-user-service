# AppMarketUserService - Serviço de gestão de usuários do App Market
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=bugs)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=coverage)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=romjunior_app-market-user-service&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=romjunior_app-market-user-service)

## Introdução 

Aplicação feita para estudo onde simula um sistema para um aplicativo semelhante de compras de alimentos de supermercados, esse serviço cuida do gerenciamento de usuários:
* Cadastro
* Edição
* Alteração
* Desativação(Não é remoção)

## Arquitetura

A aplicação utiliza arquitetura _Hexagonal_ ou também conhecida como _Ports And Adapters_. As suas dependências apontam para o centro, objetos de domínio e casos de uso, obviamente utilizando abstrações para as camandas externas se comunicarem com as camadas internas.

Diferente da arquitetura por camadas _"Layered architecture"_ ela trabalha na ideia onde o domínio, e suas regras de negócio são abstraídas dos detalhes, integrações como Banco de dados, Web, Fila, Tópicos e etc.

Referências:
[Hexagonal](https://reflectoring.io/spring-hexagonal/)

![arquitetura hexagonal](https://reflectoring.io/assets/img/posts/spring-hexagonal/hexagonal-architecture.png)


## Links de referência:
* https://arctype.com/blog/postgres-uuid/
* https://www.postgresqltutorial.com/postgresql-uuid/
* https://www.cybertec-postgresql.com/en/uuid-serial-or-identity-columns-for-postgresql-auto-generated-primary-keys/
* https://medium.com/@FernandoUnix/gerando-chave-prim%C3%A1ria-uuid-com-jpa-e-hibernate-719285a13eb2
* https://springbootdev.com/2018/03/13/spring-data-jpa-auditing-with-createdby-createddate-lastmodifiedby-and-lastmodifieddate/
* https://www.baeldung.com/java-performance-mapping-frameworks
* https://reflectoring.io/spring-boot-test/
