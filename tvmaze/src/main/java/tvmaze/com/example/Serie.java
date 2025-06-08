package model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double classificacao;
    private String estado;
    private String estreia;
    private String termino;
    private String emissora;

    public Serie(String id, String nome, String idioma, List<String> generos, Double classificacao,
                 String estado, String estreia, String termino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma != null ? idioma : "Desconhecido";
        this.generos = generos != null ? generos : new ArrayList<>();
        this.classificacao = classificacao != null ? classificacao : 0.0;
        this.estado = estado != null ? estado : "Desconhecido";
        this.estreia = estreia != null ? estreia : "Desconhecido";
        this.termino = termino != null ? termino : "Desconhecido";
        this.emissora = emissora != null ? emissora : "Desconhecido";
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public Double getClassificacao() { return classificacao; }
    public String getEstado() { return estado; }
    public String getEstreia() { return estreia; }
    public String getTermino() { return termino; }
    public String getEmissora() { return emissora; }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s\nIdioma: %s\nGêneros: %s\nClassificação: %.1f\nEstado: %s\nEstreia: %s\nTérmino: %s\nEmissora: %s\n",
            nome, idioma, generos.isEmpty() ? "Nenhum" : String.join(", ", generos), classificacao, estado, estreia, termino, emissora
        );
    }
}
