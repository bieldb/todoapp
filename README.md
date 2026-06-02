# Todo App API

API REST para gerenciamento de tarefas desenvolvida com Spring Boot.

O projeto foi criado com foco em boas práticas de desenvolvimento backend, utilizando arquitetura em camadas, DTOs, validações, tratamento global de exceções e testes unitários.

---

## Funcionalidades

- Criar tarefa
- Buscar tarefa por ID
- Listar todas as tarefas
- Filtrar tarefas por status
- Listar tarefas vencidas
- Atualizar tarefa
- Excluir tarefa
- Validação de dados de entrada
- Tratamento global de erros
- Testes unitários

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- JUnit 5
- Mockito

---

## Arquitetura

O projeto segue uma arquitetura em camadas:

src/main/java

├── controller

├── service

├── repository

├── model

├── dto

├── mapper

└── exception

---

## Modelo de Dados

### Task

| Campo | Tipo |
|---------|---------|
| id | Long |
| titulo | String |
| descricao | String |
| dataLimite | LocalDate |
| status | TaskStatus |

### TaskStatus

```java
PENDENTE
EM_ANDAMENTO
CONCLUIDA
```

---

## Endpoints

### Criar tarefa

```http
POST /tasks
```

Body:

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Finalizar CRUD",
  "status": "PENDENTE",
  "dataLimite": "2026-06-15"
}
```

---

### Buscar por ID

```http
GET /tasks/{id}
```

---

### Listar todas

```http
GET /tasks
```

---

### Buscar por status

```http
GET /tasks/status/{status}
```

Exemplo:

```http
GET /tasks/status/PENDENTE
```

---

### Buscar tarefas vencidas

```http
GET /tasks/vencidas
```

---

### Atualizar tarefa

```http
PUT /tasks/{id}
```

---

### Excluir tarefa

```http
DELETE /tasks/{id}
```

---

## Validações

As seguintes validações foram implementadas:

### Título obrigatório

```java
@NotBlank
private String titulo;
```

### Status obrigatório

```java
@NotNull
private TaskStatus status;
```

### Data limite obrigatória

```java
@NotNull
private LocalDate dataLimite;
```

---

## Tratamento de Exceções

A aplicação possui tratamento global de exceções utilizando:

```java
@RestControllerAdvice
```

Exemplo:

```json
{
  "message": "Task com ID 10 não foi encontrada."
}
```

---

## Testes

Foram implementados testes unitários utilizando:

- JUnit 5
- Mockito

Cobertura dos principais fluxos:

- Criação de tarefas
- Busca por ID
- Busca por status
- Busca de tarefas vencidas
- Atualização
- Exclusão
- Tratamento de exceções

---

## Como Executar

### Clonar o projeto

```bash
git clone https://github.com/bieldb/todo-app.git
```

### Configurar banco MySQL

Criar banco:

```sql
CREATE DATABASE todoapp;
```

### Configurar application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/todoapp
spring.datasource.username=seu_user
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```

### Executar

```bash
mvn spring-boot:run
```

---

## Próximas Melhorias

- Swagger/OpenAPI
- Paginação
- Spring Security + JWT
- Docker
- CI/CD com GitHub Actions

---

## Autor

Gabriel Alves

Projeto desenvolvido para estudo de Java, Spring Boot e desenvolvimento de APIs REST.