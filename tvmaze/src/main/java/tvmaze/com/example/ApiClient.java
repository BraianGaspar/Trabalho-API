package tvmaze.com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    private static final String BASE_URL = "https://api.tvmaze.com";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public ApiClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<Serie> buscarSeries(String nome) throws Exception {
        String url = BASE_URL + "/search/shows?q=" + nome.replace(" ", "%20");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonNode root = mapper.readTree(response.body());
        List<Serie> series = new ArrayList<>();

        for (JsonNode node : root) {
            JsonNode show = node.get("show");
            String id = show.get("id").asText();
            String nomeSerie = show.get("name").asText();
            String idioma = show.get("language") != null ? show.get("language").asText() : "N/A";
            List<String> generos = new ArrayList<>();
            if (show.get("genres") != null) {
                for (JsonNode genre : show.get("genres")) {
                    generos.add(genre.asText());
                }
            }
            Double nota = show.get("rating").get("average") != null ? show.get("rating").get("average").asDouble() : null;
            String estado = show.get("status") != null ? show.get("status").asText() : "N/A";
            String dataEstreia = show.get("premiered") != null ? show.get("premiered").asText() : "N/A";
            String dataTermino = show.get("ended") != null ? show.get("ended").asText() : null;
            String emissora = show.get("network") != null ? show.get("network").get("name").asText() : "N/A";

            series.add(new Serie(id, nomeSerie, idioma, generos, nota, estado, dataEstreia, dataTermino, emissora));
        }
        return series;
    }
}