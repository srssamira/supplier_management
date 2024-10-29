# Desafio: API para Gerenciamento de Contratos e Fornecedores
A empresa XYZ precisa de uma aplicação para gerenciar contratos com seus fornecedores. Essa aplicação permitirá à equipe de gerenciamento de contratos registrar, atualizar e consultar informações sobre os fornecedores e contratos vigentes. A aplicação deve utilizar Spring Boot e Spring Data JPA e armazenar as informações em tabelas relacionais.


## Objetivos:
-   Criar uma API RESTful com Spring Boot para gerenciar Fornecedores e Contratos.
-   Utilizar PostgreSQL como banco de dados para armazenar os dados. 
- Implementar relacionamentos entre as tabelas: um Fornecedor pode ter múltiplos Contratos, mas um Contrato está vinculado a apenas um Fornecedor. 
- Validar dados de entrada usando Bean Validation. 
- Implementar tratamento de erros, retornando mensagens amigáveis ao cliente em caso de erros de validação e erros de negócio.


## Requisitos Funcionais:
**Fornecedor:**
Um fornecedor deve ter um identificador, nome, CNPJ (Cadastro Nacional de Pessoa Jurídica), telefone e endereço.
Deverá ser possível listar todos os fornecedores, buscar um fornecedor específico, criar, atualizar e remover fornecedores.

**Contrato:**
Um contrato deve conter um identificador, número do contrato, data de início, data de término, valor total e uma descrição.
Cada contrato é vinculado a um fornecedor e deve ser possível listar todos os contratos de um fornecedor, além de criar, atualizar e remover contratos.


## Estrutura da API:
Abaixo está o contrato para cada endpoint da API.


### Endpoints de Fornecedor:

GET /fornecedores - Retorna uma lista de todos os fornecedores cadastrados. 

GET /fornecedores/{id} - Retorna os detalhes de um fornecedor específico. 

POST /fornecedores - Cria um novo fornecedor.

- *Corpo da requisição:*
*json*
```
{
    "nome": "string",
    "cnpj": "string",
    "telefone": "string",
    "endereco": "string"
}
```
PUT /fornecedores/{id} - Atualiza os dados de um fornecedor existente.

- Corpo da requisição: Mesmo formato que o POST.

DELETE /fornecedores/{id} - Exclui um fornecedor.

### Endpoints de Contrato
GET /fornecedores/{fornecedorId}/contratos - Retorna uma lista de contratos de um fornecedor específico.
GET /contratos/{id} - Retorna os detalhes de um contrato específico.
POST /fornecedores/{fornecedorId}/contratos - Cria um novo contrato para um fornecedor específico.
*- Corpo da requisição:*
*json*

```
{
    "numeroContrato": "string",
    "dataInicio": "yyyy-MM-dd",
    "dataTermino": "yyyy-MM-dd",
    "valorTotal": "number",
    "descricao": "string",
    "ativo": boolean
}
```
PUT /contratos/{id} - Atualiza os dados de um contrato existente.
- Corpo da requisição: Mesmo formato que o POST.

DELETE /contratos/{id} - Exclui um contrato.


## Regras de Negócio
Validação de datas: A data de término do contrato deve ser maior que a data de início.

Formato do CNPJ: O CNPJ do fornecedor deve ser válido (utilizar validação específica para o Brasil).

Valor do contrato: O valor do contrato deve ser maior que zero.


## Tratamento de Erros

Retornar status 400 para erros de validação de dados.

Retornar status 404 para recursos não encontrados.

Retornar status 500 para erros internos do servidor com uma mensagem genérica.


### Entrega mínima
A API deve permitir que o usuário cadastre tanto fornecedor quanto contrato. Um contrato não pode existir sem um fornecedor.

Os contratos devem ser organizados por ativos e inativos. Sempre que um novo contrato for cadastrado é necessário fazer a verificação se está seguindo a definição de um contrato ativo. Caso contrário deve ser cadastrado como inativo.
Definição de contrato ativo: Último contrato cadastrado com data de término no futuro.   

### Entrega Média
A API deve permitir uma busca por contratos de um fornecedor por data inicial, ativo, data término ou palavra que CONTENHA na descrição.

exemplo de como aplicar o filtro
GET /fornecedor/{fornecedorId}/contratos?dataInicial=23/08/2024 -  Retorna todos os contratos do fornecedor especificado pelo ID com a data Inicial mencionada no parametro

### Entrega Maxima
A API também contará com um serviço de rotina que será executado automaticamente todo dia a cada 1 hora. Esse serviço busca no banco de dados TODOS os contratos com data de término menor do que a data atual para mudar o status de ativo=true para ativo=false.

Temas para pesquisa para realizar essa tarefa:
- Cronjob
- Scheduler com Spring
- Expressão de Cronjob (https://pt.rakko.tools/tools/88/) 
