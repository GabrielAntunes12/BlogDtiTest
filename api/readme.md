# Blog Project

Este projeto é uma aplicação de blog simples em Go que permite criar e recuperar posts via API HTTP.

## Requisitos

- Go 1.22.2

## Instalação do Go 1.22.2 no Linux

1. Baixe o tarball do Go 1.22.2 para Linux em: [https://golang.org/dl/](https://golang.org/dl/)
2. Extraia o tarball em `/usr/local`:

   sudo tar -C /usr/local -xzf go1.22.2.linux-amd64.tar.gz

3. Adicione `/usr/local/go/bin` ao seu `PATH`. Você pode fazer isso adicionando a seguinte linha ao final do arquivo `~/.profile` (ou `~/.bashrc` ou `~/.zshrc`):

   export PATH=$PATH:/usr/local/go/bin

4. Carregue o perfil atualizado no shell atual:

   source ~/.profile

5. Verifique a instalação com:

   go version

   Deve retornar `go version go1.22.2 linux/amd64` (ou similar).

## Rodando a Aplicação

1. Clone este repositório:

   git clone https://github.com/seu-usuario/blog_project.git
   cd blog_project

2. Execute a aplicação:

   go run main.go

3. Acesse a aplicação no seu navegador em: [http://localhost:8080](http://localhost:8080)

## Endpoints

### GET /posts

Retorna todos os posts.

### GET /posts/{id}

Retorna um post específico pelo ID.

### POST /posts

Cria um novo post.

#### Exemplo de corpo da requisição:

{
"title": "Novo Post",
"description": "Descrição do novo post"
}

## Exemplo de Uso

1. Para criar um novo post, use o comando `curl`:

   curl -X POST http://localhost:8080/posts -d '{"title":"Novo Post","description":"Descrição do novo post"}' -H "Content-Type: application/json"

2. Para obter todos os posts, use:

   curl http://localhost:8080/posts

3. Para obter um post específico pelo ID (por exemplo, ID 1), use:

   curl http://localhost:8080/posts/1