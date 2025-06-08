package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorDados {
    private static final String ARQUIVO_DADOS = "rastreador_series.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void salvarDados(DadosUsuario dadosUsuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO_DADOS)) {
            gson.toJson(dadosUsuario, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public DadosUsuario carregarDados() {
        File file = new File(ARQUIVO_DADOS);
        if (!file.exists()) {
            return new DadosUsuario();
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, DadosUsuario.class);
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return new DadosUsuario();
        }
    }
}
