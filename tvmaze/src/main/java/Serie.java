import java.util.List;

public class Serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String status;
    private String dataEstreia;
    private String dataFim;
    private String emissora;

    public Serie(String nome, String idioma, List<String> generos, double nota, String status, String dataEstreia,
        String dataFim, String emissora) {
      this.nome = nome;
      this.idioma = idioma;
      this.generos = generos;
      this.nota = nota;
      this.status = status;
      this.dataEstreia = dataEstreia;
      this.dataFim = dataFim;
      this.emissora = emissora;
    }

    public String getNome() {
      return nome;
    }
    public void setNome(String nome) {
      this.nome = nome;
    }
    public String getIdioma() {
      return idioma;
    }
    public void setIdioma(String idioma) {
      this.idioma = idioma;
    }
    public List<String> getGeneros() {
      return generos;
    }
    public void setGeneros(List<String> generos) {
      this.generos = generos;
    }
    public double getNota() {
      return nota;
    }
    public void setNota(double nota) {
      this.nota = nota;
    }
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    public String getDataEstreia() {
      return dataEstreia;
    }
    public void setDataEstreia(String dataEstreia) {
      this.dataEstreia = dataEstreia;
    }
    public String getDataFim() {
      return dataFim;
    }
    public void setDataFim(String dataFim) {
      this.dataFim = dataFim;
    }
    public String getEmissora() {
      return emissora;
    }
    public void setEmissora(String emissora) {
      this.emissora = emissora;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Serie{");
        sb.append("nome=").append(nome);
        sb.append(", idioma=").append(idioma);
        sb.append(", generos=").append(generos);
        sb.append(", nota=").append(nota);
        sb.append(", status=").append(status);
        sb.append(", dataEstreia=").append(dataEstreia);
        sb.append(", dataFim=").append(dataFim);
        sb.append(", emissora=").append(emissora);
        sb.append('}');
        return sb.toString();
    }



}
