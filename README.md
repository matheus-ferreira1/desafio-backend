# Picpay simplificado (desafio backend Picpay)


API RESTful que consiste em uma versão simplificada do Picpay. Podem ser cadastrados 2 tipos de novos usuários (lojistas ou comuns), ambos possuem carteira com dinheiro e podem realizar transferência entre eles. Para o cadastro, é necessário inserir nome completo, CPF, e-mail e senha. E-mail e CPF são únicos e não pode haver registros duplicados. Usuários comuns podem realizar transferências para lojistas e entre si. Lojistas podem apenas receber transferências. Este projeto faz parte do [desafio backend do Picpay](https://github.com/PicPay/picpay-desafio-backend).

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)
- [H2 database](https://www.h2database.com/html/main.html)

## Práticas adotadas

- DRY
- API RESTful
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro

## Como Executar

- Clonar repositório git
- Construir e rodar o projeto em sua IDE de preferência.


## API Endpoints

### Registrar novo usuário - POST /users
#### Request body:
```
{
    "firstName": "Matheus",
    "lastName": "Sobrenome",
    "document": "123456789",
    "email": "matheus@gmail.com",
    "password": "senha",
    "userType": "COMMON",
    "balance": 100
}
```
### Buscar todos usuários - GET /users
#### Response:
```
[
    {
        "id": 1,
        "firstName": "Loja",
        "lastName": "Sobrenome",
        "document": "123456780",
        "email": "loja@gmail.com",
        "password": "senha",
        "balance": 100.00,
        "userType": "MERCHANT"
    },
    {
        "id": 2,
        "firstName": "Matheus",
        "lastName": "Sobrenome",
        "document": "123456789",
        "email": "matheus@gmail.com",
        "password": "senha",
        "balance": 100.00,
        "userType": "COMMON"
    }
]
```

### Nova transação - POST /transaction
#### Request body:
```
{
    "senderId": 1,
    "receiverId": 2,
    "value": 100
}
```{
    "id": 1,
    "amount": 100,
    "sender": {
        "id": 2,
        "firstName": "Matheus",
        "lastName": "Sobrenome",
        "document": "123456789",
        "email": "matheus@gmail.com",
        "password": "senha",
        "balance": 0.00,
        "userType": "COMMON"
    },
    "receiver": {
        "id": 1,
        "firstName": "Loja",
        "lastName": "Sobrenome",
        "document": "123456780",
        "email": "loja@gmail.com",
        "password": "senha",
        "balance": 200.00,
        "userType": "MERCHANT"
    },
    "timeStamp": "2023-09-03T00:30:25.057927707"
}
#### Response:
```
{
    "id": 1,
    "amount": 100,
    "sender": {
        "id": 2,
        "firstName": "Matheus",
        "lastName": "Sobrenome",
        "document": "123456789",
        "email": "matheus@gmail.com",
        "password": "senha",
        "balance": 0.00,
        "userType": "COMMON"
    },
    "receiver": {
        "id": 1,
        "firstName": "Loja",
        "lastName": "Sobrenome",
        "document": "123456780",
        "email": "loja@gmail.com",
        "password": "senha",
        "balance": 200.00,
        "userType": "MERCHANT"
    },
    "timeStamp": "2023-09-03T00:30:25.057927707"
}
```
