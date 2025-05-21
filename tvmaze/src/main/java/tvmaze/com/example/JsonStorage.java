package tvmaze.com.example;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonStorage {

  private static final String FILE = "usuario.json";
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void salvar(Usuario usuario) throws Exception {
      mapper.writeValue(new File(FILE), usuario);
  }

  public static Usuario carregar() throws Exception {
      return mapper.readValue(new File(FILE), Usuario.class);
  }
}
