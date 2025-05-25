package tvmaze.com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SerieList {
    private List<Serie> series;

    public SerieList() {
        this.series = new ArrayList<>();
    }

    public void adicionar(Serie serie) {
        if (!series.contains(serie)) {
            series.add(serie);
        }
    }

    public void remover(String id) {
        series.removeIf(serie -> serie.getId().equals(id));
    }

    public List<Serie> getSeries() {
        return new ArrayList<>(series);
    }

    public void ordenarPorNome() {
        series.sort(Comparator.comparing(Serie::getNome));
    }

    public void ordenarPorNota() {
        series.sort(Comparator.comparing(Serie::getNotaGeral, Comparator.reverseOrder()));
    }

    public void ordenarPorEstado() {
        series.sort(Comparator.comparing(Serie::getEstado));
    }

    public void ordenarPorDataEstreia() {
        series.sort(Comparator.comparing(Serie::getDataEstreia));
    }

    public void exibir() {
        if (series.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }
        for (Serie serie : series) {
            System.out.println(serie);
            System.out.println("---------------");
        }
    }
}