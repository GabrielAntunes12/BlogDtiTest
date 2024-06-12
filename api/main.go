
package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"strconv"
	"strings"
	"sync"
	"time"
)

// Post estrutura para armazenar informações do post
type Post struct {
	ID          int       `json:"id"`
	Title       string    `json:"title"`
	Description string    `json:"description"`
	CreatedAt   string `json:"created_at"`
}

var (
	posts  []Post
	nextID int
	mu     sync.Mutex
)

func main() {
	now := time.Now().UTC()
	formattedDate := now.Format("02-01-2006")
	// Inicializar com alguns posts mockados
	posts = []Post{
		{ID: 1, Title: "Primeiro Post", Description: "Descrição do primeiro post", CreatedAt: formattedDate},
		{ID: 2, Title: "Segundo Post", Description: "Descrição do segundo post", CreatedAt: formattedDate},
	}
	nextID = 3

	http.HandleFunc("/posts", postsHandler)
	http.HandleFunc("/posts/", postHandler)

	fmt.Println("Servidor rodando em http://localhost:8080")
	http.ListenAndServe(":8080", nil)
}

// postsHandler lida com requisições para /posts
func postsHandler(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case "GET":
		getPosts(w, r)
	case "POST":
		createPost(w, r)
	default:
		http.Error(w, "Método não permitido", http.StatusMethodNotAllowed)
	}
}

// postHandler lida com requisições para /posts/{id}
func postHandler(w http.ResponseWriter, r *http.Request) {
	switch r.Method {
	case "GET":
		getPost(w, r)
	default:
		http.Error(w, "Método não permitido", http.StatusMethodNotAllowed)
	}
}

// getPosts retorna todos os posts
func getPosts(w http.ResponseWriter, r *http.Request) {
	mu.Lock()
	defer mu.Unlock()

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(posts)
}

// createPost cria um novo post
func createPost(w http.ResponseWriter, r *http.Request) {
	var post Post
	if err := json.NewDecoder(r.Body).Decode(&post); err != nil {
		http.Error(w, "Erro ao decodificar JSON", http.StatusBadRequest)
		return
	}

	mu.Lock()
	defer mu.Unlock()
	now := time.Now().UTC()
	formattedDate := now.Format("02-01-2006")
	post.ID = nextID
	post.CreatedAt = formattedDate
	nextID++
	posts = append(posts, post)

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusCreated)
	json.NewEncoder(w).Encode(post)
}

// getPost retorna um post específico pelo ID
func getPost(w http.ResponseWriter, r *http.Request) {
	idStr := strings.TrimPrefix(r.URL.Path, "/posts/")
	id, err := strconv.Atoi(idStr)
	if err != nil {
		http.Error(w, "ID inválido", http.StatusBadRequest)
		return
	}

	mu.Lock()
	defer mu.Unlock()

	for _, post := range posts {
		if post.ID == id {
			w.Header().Set("Content-Type", "application/json")
			json.NewEncoder(w).Encode(post)
			return
		}
	}

	http.Error(w, "Post não encontrado", http.StatusNotFound)
}
