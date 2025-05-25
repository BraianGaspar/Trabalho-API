package tvmaze.com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("favoritos")
    private SerieList favoritos;
    @JsonProperty("assistidas")
    private SerieList assistidas;
    @JsonProperty("desejaAssistir")
    private SerieList desejaAssistir;

    public Usuario() {
        this.nome = "Usuário Padrão";
        this.favoritos = new SerieList();
        this.assistidas = new SerieList();
        this.desejaAssistir = new SerieList();
    }

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new SerieList();
        this.assistidas = new SerieList();
        this.desejaAssistir = new SerieList();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public SerieList getFavoritos() { return favoritos; }
    public SerieList getAssistidas() { return assistidas; }
    public SerieList getDesejaAssistir() { return desejaAssistir; }
}