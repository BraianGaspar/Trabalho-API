package tvmaze.com.example;

import java.util.List;

public class Serie {
    private String id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    // Construtores
    public Serie(String id, String nome, String idioma, List<String> generos, Double notaGeral,
                 String estado, String dataEstreia, String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public Double getNotaGeral() { return notaGeral != null ? notaGeral : 0.0; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataTermino() { return dataTermino; }
    public String getEmissora() { return emissora; }

    @Override
    public String toString() {
        return "Série: " + nome + "\n" +
                "Idioma: " + idioma + "\n" +
                "Gêneros: " + generos + "\n" +
                "Nota Geral: " + notaGeral + "\n" +
                "Estado: " + estado + "\n" +
                "Data de Estreia: " + dataEstreia + "\n" +
                "Data de Término: " + (dataTermino != null ? dataTermino : "N/A") + "\n" +
                "Emissora: " + emissora;
    }
}