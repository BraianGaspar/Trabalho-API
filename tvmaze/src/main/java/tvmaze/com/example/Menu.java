package tvmaze.com.example;

import java.util.Scanner;

public class Menu {
  private final Usuario usuario;
  private final ApiClient api;

  public Menu(Usuario usuario) {
      this.usuario = usuario;
      this.api = new ApiClient();
  }

  public void exibir() {
      Scanner sc = new Scanner(System.in);
  }
}
