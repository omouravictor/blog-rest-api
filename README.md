# Blog API

Api rest desenvolvida com Java e Spring Boot.

Rotas implementadas ↓

* /register - [POST] - Cadastra um usuário;
* /posts - [POST] - Cadastra uma postagem mantendo a referência do autor.
* /posts/{id} - [PUT] - Edita a postagem do ID especificado mantendo a referência do autor.
* /posts - [GET] - Retorna a lista de todas as postagens ordenadas das mais recentes para as mais antigas com a possibilidade de inverter esta ordenação e de retornar apenas as postagens do usuário que fez a requisição.
* /posts/{id} - [GET] - Retorna a postagem do ID especificado com todos os seus dados.
* /posts/{id} - [DELETE] - Deleta a postagem do ID especificado.

**Obs**: Primeira experiência com Spring Boot.
