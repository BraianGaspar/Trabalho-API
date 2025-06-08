package model;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RastreadorSeriesTV {
    private DadosUsuario dadosUsuario;
    private final GerenciadorDados gerenciadorDados;
    private final Scanner scanner;

    public RastreadorSeriesTV() {
        this.gerenciadorDados = new GerenciadorDados();
        this.scanner = new Scanner(System.in);
        this.dadosUsuario = gerenciadorDados.carregarDados();
        inicializarUsuario();
    }

    private void inicializarUsuario() {
        if (dadosUsuario.nomeUsuario.equals("Usuário")) {
            System.out.print("Digite seu nome ou apelido: ");
            String nome = scanner.nextLine().trim();
            if (!nome.isEmpty()) {
                dadosUsuario.nomeUsuario = nome;
                gerenciadorDados.salvarDados(dadosUsuario);
            }
        }
        System.out.println("Bem-vindo, " + dadosUsuario.nomeUsuario + "!");
    }

    public void executar() {
        while (true) {
            exibirMenu();
            String escolha = scanner.nextLine().trim();
            try {
                switch (escolha) {
                    case "1": buscarSerie(); break;
                    case "2": gerenciarLista(dadosUsuario.favoritos, "Favoritos"); break;
                    case "3": gerenciarLista(dadosUsuario.assistidas, "Assistidas"); break;
                    case "4": gerenciarLista(dadosUsuario.listaDesejos, "Lista de Desejos"); break;
                    case "5": exibirListas(); break;
                    case "6": System.out.println("Tchau, " + dadosUsuario.nomeUsuario + "!"); return;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.err.println("Ocorreu um erro: " + e.getMessage());
            }
            gerenciadorDados.salvarDados(dadosUsuario);
        }
    }

    private void exibirMenu() {
        System.out.println("\n=== Rastreador de Séries de TV ===");
        System.out.println("1. Buscar uma série");
        System.out.println("2. Gerenciar Favoritos");
        System.out.println("3. Gerenciar Assistidas");
        System.out.println("4. Gerenciar Lista de Desejos");
        System.out.println("5. Exibir Listas");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void buscarSerie() {
        System.out.print("Digite o nome da série para buscar: ");
        String consulta = scanner.nextLine().trim();
        try {
            List<Serie> resultados = TVMazeAPI.buscarSeries(consulta);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma série encontrada.");
                return;
            }
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println("\n[" + (i + 1) + "]");
                System.out.println(resultados.get(i));
            }
            System.out.print("Digite o número da série para adicionar a uma lista (0 para cancelar): ");
            int indice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (indice >= 0 && indice < resultados.size()) {
                System.out.print("Adicionar a (1) Favoritos, (2) Assistidas, (3) Lista de Desejos, (0) Cancelar: ");
                String escolhaLista = scanner.nextLine().trim();
                Serie serie = resultados.get(indice);
                switch (escolhaLista) {
                    case "1": adicionarALista(dadosUsuario.favoritos, serie, "Favoritos"); break;
                    case "2": adicionarALista(dadosUsuario.assistidas, serie, "Assistidas"); break;
                    case "3": adicionarALista(dadosUsuario.listaDesejos, serie, "Lista de Desejos"); break;
                    case "0": break;
                    default: System.out.println("Opção inválida.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao buscar série: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }

    private void adicionarALista(List<Serie> lista, Serie serie, String nomeLista) {
        if (lista.stream().anyMatch(s -> s.getId().equals(serie.getId()))) {
            System.out.println("Série já está em " + nomeLista + ".");
        } else {
            lista.add(serie);
            System.out.println("Adicionada a " + nomeLista + ".");
        }
    }

    private void gerenciarLista(List<Serie> lista, String nomeLista) {
        while (true) {
            System.out.println("\n=== Gerenciar " + nomeLista + " ===");
            exibirLista(lista, nomeLista);
            System.out.println("1. Remover série");
            System.out.println("2. Ordenar lista");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");
            String escolha = scanner.nextLine().trim();
            try {
                switch (escolha) {
                    case "1": removerDaLista(lista, nomeLista); break;
                    case "2": ordenarLista(lista, nomeLista); break;
                    case "3": return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao gerenciar lista: " + e.getMessage());
            }
            gerenciadorDados.salvarDados(dadosUsuario);
        }
    }

    private void exibirLista(List<Serie> lista, String nomeLista) {
        if (lista.isEmpty()) {
            System.out.println(nomeLista + " está vazia.");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            System.out.println(lista.get(i));
        }
    }

    private void removerDaLista(List<Serie> lista, String nomeLista) {
        exibirLista(lista, nomeLista);
        if (lista.isEmpty()) return;
        System.out.print("Digite o número da série para remover (0 para cancelar): ");
        try {
            int indice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (indice >= 0 && indice < lista.size()) {
                lista.remove(indice);
                System.out.println("Removida de " + nomeLista + ".");
            } else if (indice != -1) {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
        }
    }

    private void ordenarLista(List<Serie> lista, String nomeLista) {
        System.out.println("Ordenar por: 1. Nome, 2. Classificação, 3. Estado, 4. Data de Estreia");
        System.out.print("Escolha uma opção: ");
        String escolhaOrdenacao = scanner.nextLine().trim();
        Comparator<Serie> comparador;
        switch (escolhaOrdenacao) {
            case "1":
                comparador = Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
                break;
            case "2":
                comparador = Comparator.comparing(Serie::getClassificacao, Comparator.reverseOrder());
                break;
            case "3":
                comparador = Comparator.comparing(Serie::getEstado, String.CASE_INSENSITIVE_ORDER);
                break;
            case "4":
                comparador = Comparator.comparing(Serie::getEstreia, Comparator.nullsLast(String::compareTo));
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        lista.sort(comparador);
        System.out.println(nomeLista + " ordenada.");
        exibirLista(lista, nomeLista);
    }

    private void exibirListas() {
        System.out.println("\n=== Favoritos ===");
        exibirLista(dadosUsuario.favoritos, "Favoritos");
        System.out.println("\n=== Assistidas ===");
        exibirLista(dadosUsuario.assistidas, "Assistidas");
        System.out.println("\n=== Lista de Desejos ===");
        exibirLista(dadosUsuario.listaDesejos, "Lista de Desejos");
    }

    public static void main(String[] args) {
        try {
            RastreadorSeriesTV rastreador = new RastreadorSeriesTV();
            rastreador.executar();
        } catch (Exception e) {
            System.err.println("Erro fatal: " + e.getMessage());
        }
    }
}
