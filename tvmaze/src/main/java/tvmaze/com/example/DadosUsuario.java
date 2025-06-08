package tvmaze.com.example;

import java.util.ArrayList;
import java.util.List;

public class DadosUsuario {
    String nomeUsuario;
    List<Serie> favoritos;
    List<Serie> assistidas;
    List<Serie> listaDesejos;

    public DadosUsuario() {
        this.nomeUsuario = "Usuário";
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.listaDesejos = new ArrayList<>();
    }
}
