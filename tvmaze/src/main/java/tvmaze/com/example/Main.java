package tvmaze.com.example;

public class Main {
    public static void main(String[] args) {
        try {
            Usuario usuario = JsonStorage.carregar();
            Menu menu = new Menu(usuario);
            menu.exibir();
            JsonStorage.salvar(usuario);
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o sistema: " + e.getMessage());
        }
    }
}