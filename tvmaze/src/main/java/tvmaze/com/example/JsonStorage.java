package tvmaze.com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class JsonStorage {
    private static final String FILE = "usuario.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void salvar(Usuario usuario) throws Exception {
        mapper.writeValue(new File(FILE), usuario);
    }

    public static Usuario carregar() throws Exception {
        File file = new File(FILE);
        if (!file.exists()) {
            return new Usuario();
        }
        return mapper.readValue(file, Usuario.class);
    }
}