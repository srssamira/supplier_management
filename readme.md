## API de Gerenciamento de Contratos e Fornecedores

Este documento descreve a especificação da API RESTful para gerenciar contratos e fornecedores da empresa XYZ, utilizando Spring Boot e Spring Data JPA com PostgreSQL como banco de dados.

### Visão Geral

A API visa atender às necessidades da equipe de gerenciamento de contratos, permitindo registrar, atualizar e consultar informações sobre fornecedores e contratos vigentes. A API deve ser robusta, segura e eficiente, com foco na usabilidade e na experiência do usuário.

### Requisitos Funcionais

**1. Fornecedor:**

* **Identificador:** ID único para cada fornecedor.
* **Nome:** Nome completo do fornecedor.
* **CNPJ:** Cadastro Nacional de Pessoa Jurídica do fornecedor.
* **Telefone:** Número de telefone de contato.
* **Endereço:** Endereço completo do fornecedor.

**Funcionalidades:**

* **Listar todos os fornecedores:** Retorna a lista de todos os fornecedores cadastrados.
* **Buscar um fornecedor específico:** Retorna os detalhes de um fornecedor específico, dado o seu ID.
* **Criar um novo fornecedor:** Permite cadastrar um novo fornecedor com os dados obrigatórios.
* **Atualizar um fornecedor existente:** Permite modificar os dados de um fornecedor já cadastrado.
* **Excluir um fornecedor:** Permite remover um fornecedor do sistema.

**2. Contrato:**

* **Identificador:** ID único para cada contrato.
* **Número do Contrato:** Número do contrato, para referência interna.
* **Data de Início:** Data de início da vigência do contrato.
* **Data de Término:** Data de término da vigência do contrato.
* **Valor Total:** Valor total do contrato em moeda local.
* **Descrição:** Descrição detalhada do contrato.
* **Ativo:** Flag booleano indicando se o contrato está ativo (true) ou inativo (false).

**Funcionalidades:**

* **Listar todos os contratos de um fornecedor:** Retorna a lista de contratos vinculados a um fornecedor específico.
* **Buscar um contrato específico:** Retorna os detalhes de um contrato específico, dado o seu ID.
* **Criar um novo contrato para um fornecedor:** Permite cadastrar um novo contrato para um fornecedor existente.
* **Atualizar um contrato existente:** Permite modificar os dados de um contrato já cadastrado.
* **Excluir um contrato:** Permite remover um contrato do sistema.

**3. Relacionamento:**

* Um fornecedor pode ter múltiplos contratos.
* Um contrato é vinculado a apenas um fornecedor.

### Estrutura da API

**Endpoints de Fornecedor:**

* **GET /fornecedores:** Retorna a lista de todos os fornecedores cadastrados.
* **GET /fornecedores/{id}:** Retorna os detalhes de um fornecedor específico.
* **POST /fornecedores:** Cria um novo fornecedor.
* **PUT /fornecedores/{id}:** Atualiza os dados de um fornecedor existente.
* **DELETE /fornecedores/{id}:** Exclui um fornecedor.

**Endpoints de Contrato:**

* **GET /fornecedores/{fornecedorId}/contratos:** Retorna a lista de contratos de um fornecedor específico.
* **GET /contratos/{id}:** Retorna os detalhes de um contrato específico.
* **POST /fornecedores/{fornecedorId}/contratos:** Cria um novo contrato para um fornecedor específico.
* **PUT /contratos/{id}:** Atualiza os dados de um contrato existente.
* **DELETE /contratos/{id}:** Exclui um contrato.

### Regras de Negócio

* **Validação de datas:** A data de término do contrato deve ser maior que a data de início.
* **Formato do CNPJ:** O CNPJ do fornecedor deve ser válido (utilizar validação específica para o Brasil).
* **Valor do contrato:** O valor do contrato deve ser maior que zero.
* **Gerenciamento de Status de Contratos:** Um novo contrato é cadastrado como "ativo" se a data de término for posterior à data atual. Caso contrário, o contrato é cadastrado como "inativo".

### Tratamento de Erros

* **Status 400:** Erros de validação de dados.
* **Status 404:** Recurso não encontrado.
* **Status 500:** Erros internos do servidor com uma mensagem genérica.

### Entrega Mínima

* A API deve permitir o cadastro de fornecedores e contratos.
* Um contrato não pode existir sem um fornecedor.
* O sistema deve gerenciar o status de contratos (ativo/inativo) de acordo com a data de término.

### Entrega Média

* A API deve permitir a busca de contratos de um fornecedor por data inicial, ativo, data término ou palavra que CONTENHA na descrição.
* Exemplo de filtro: `GET /fornecedor/{fornecedorId}/contratos?dataInicial=23/08/2024`

### Entrega Máxima

* A API deve incluir um serviço de rotina que será executado automaticamente a cada 1 hora. Este serviço busca no banco de dados todos os contratos com data de término menor do que a data atual e altera o status de "ativo" para "inativo".

### Temas para Pesquisa

* **Cronjob:** Configurar tarefas agendadas com Spring para executar a rotina de atualização de status de contratos.
* **Scheduler com Spring:** Implementar um mecanismo de agendamento de tarefas.
* **Expressão de Cronjob:** Utilizar expressões cron para definir a frequência de execução da tarefa de atualização de status de contratos.
