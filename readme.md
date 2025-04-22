# API de Gerenciamento de Produtos - Prime Cell

Backend desenvolvido em Spring Boot para gerenciamento de produtos com autenticação JWT e controle de acesso administrativo.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.4.4
- PostgreSQL 13
- Docker
- JWT (JSON Web Token)
- Maven

## 📋 Funcionalidades

### Autenticação
- Registro de administradores (`/auth/register`) - Acesso restrito
- Login (`/auth/login`)
- Autenticação via JWT Token

### Produtos
- Listagem de produtos (público)
- Cadastro de produtos (autenticado)
- Atualização de produtos (autenticado)
- Remoção de produtos (autenticado)

## 🔒 Segurança

O sistema utiliza Spring Security com JWT:
- Rotas públicas:
    - `POST /auth/login`
    - `GET /products`
- Rotas protegidas:
    - `POST /auth/register` (somente com alteração no SecurityConfig)
    - `POST /products`
    - `PUT /products`
    - `DELETE /products`

Para liberar o registro de administradores, altere no `SecurityConfig.java`:
```java
.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
```

## 🛠 Configuração

1. Clone o repositório:
```bash
git clone https://github.com/mateusgomst/back-end-primecell.git
```

2. Crie um arquivo `.env`:
```dotenv
DATABASE_URL=jdbc:postgresql://db:5432/db_primecell
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=3132
API_SECRET=12345678
SERVER_PORT=8080
POSTGRES_DB=db_primecell
POSTGRES_USER=postgres
POSTGRES_PASSWORD=3132
POSTGRES_PORT=5433
```

## 🚀 Execução

1. Pare containers existentes:
```bash
docker-compose down --volumes
```

2. Inicie a aplicação:
```bash
docker-compose up --build
```

3. Endpoints:
- API: `http://localhost:8080`
- PostgreSQL: `localhost:5434`

## 🔑 Autenticação JWT

1. Libere o endpoint de registro no `SecurityConfig`
2. Registre um administrador via `/auth/register`
3. Faça login via `/auth/login`
4. Use o token retornado: `Authorization: Bearer {token}`

## 📝 Observações

- Dados persistem entre reinicializações (modo `update`)
- Volume Docker para persistência do PostgreSQL
- Registro de novos administradores requer alteração no código
- Porta do banco alterada para 5434 para evitar conflitos