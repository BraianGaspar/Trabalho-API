package tvmaze.com.example;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Usuario usuario;
    private final ApiClient api;
    private final Scanner sc;

    public Menu(Usuario usuario) {
        this.usuario = usuario;
        this.api = new ApiClient();
        this.sc = new Scanner(System.in);
    }

    public void exibir() {
        while (true) {
            System.out.println("\nBem-vindo, " + usuario.getNome() + "!");
            System.out.println("1. Buscar série");
            System.out.println("2. Gerenciar favoritos");
            System.out.println("3. Gerenciar séries assistidas");
            System.out.println("4. Gerenciar séries para assistir");
            System.out.println("5. Alterar nome do usuário");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        buscarSerie();
                        break;
                    case 2:
                        gerenciarLista(usuario.getFavoritos(), "Favoritos");
                        break;
                    case 3:
                        gerenciarLista(usuario.getAssistidas(), "Assistidas");
                        break;
                    case 4:
                        gerenciarLista(usuario.getDesejaAssistir(), "Deseja Assistir");
                        break;
                    case 5:
                        alterarNomeUsuario();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void buscarSerie() throws Exception {
        System.out.print("Digite o nome da série: ");
        String nome = sc.nextLine();
        List<Serie> series = api.buscarSeries(nome);
        if (series.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
            return;
        }
        for (int i = 0; i < series.size(); i++) {
            System.out.println((i + 1) + ". " + series.get(i));
            System.out.println("---------------");
        }
        System.out.print("Deseja adicionar a uma lista? (1-Favoritos, 2-Assistidas, 3-Deseja Assistir, 0-Não): ");
        int escolha = Integer.parseInt(sc.nextLine());
        if (escolha >= 1 && escolha <= 3) {
            System.out.print("Digite o número da série: ");
            int indice = Integer.parseInt(sc.nextLine()) - 1;
            if (indice >= 0 && indice < series.size()) {
                Serie serie = series.get(indice);
                if (escolha == 1) usuario.getFavoritos().adicionar(serie);
                else if (escolha == 2) usuario.getAssistidas().adicionar(serie);
                else usuario.getDesejaAssistir().adicionar(serie);
                System.out.println("Série adicionada com sucesso!");
            } else {
                System.out.println("Índice inválido.");
            }
        }
    }

    private void gerenciarLista(SerieList lista, String nomeLista) {
        while (true) {
            System.out.println("\nLista: " + nomeLista);
            System.out.println("1. Exibir lista");
            System.out.println("2. Remover série");
            System.out.println("3. Ordenar por nome");
            System.out.println("4. Ordenar por nota");
            System.out.println("5. Ordenar por estado");
            System.out.println("6. Ordenar por data de estreia");
            System.out.println("7. Voltar");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        lista.exibir();
                        break;
                    case 2:
                        System.out.print("Digite o ID da série para remover: ");
                        String id = sc.nextLine();
                        lista.remover(id);
                        System.out.println("Série removida com sucesso!");
                        break;
                    case 3:
                        lista.ordenarPorNome();
                        System.out.println("Lista ordenada por nome.");
                        break;
                    case 4:
                        lista.ordenarPorNota();
                        System.out.println("Lista ordenada por nota.");
                        break;
                    case 5:
                        lista.ordenarPorEstado();
                        System.out.println("Lista ordenada por estado.");
                        break;
                    case 6:
                        lista.ordenarPorDataEstreia();
                        System.out.println("Lista ordenada por data de estreia.");
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void alterarNomeUsuario() {
        System.out.print("Digite o novo nome: ");
        String novoNome = sc.nextLine();
        usuario.setNome(novoNome);
        System.out.println("Nome alterado com sucesso!");
    }
}