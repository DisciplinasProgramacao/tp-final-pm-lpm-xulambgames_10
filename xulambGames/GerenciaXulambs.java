package xulambGames;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.time.temporal.TemporalAdjusters.*;

public class GerenciaXulambs {
  public String MenosVendidos(List<Compra> compras) {

    ArrayList<Jogo> jogos = new ArrayList<>();
    ArrayList<String> jogoIterator = new ArrayList<String>();

    for (Compra compra : compras) {
      jogos.addAll(compra.getJogos());
    }

    Map<Object, Long> maisVendido = jogos.stream().collect(
        Collectors.groupingBy(jogo -> jogo.getNome(), Collectors.counting()));

    Long minMap = Collections.min(maisVendido.values());

    for (Map.Entry<Object, Long> jogo : maisVendido.entrySet()) {
      if (jogo.getValue() == minMap) {
        jogoIterator.add((String) jogo.getKey());
        break;
      }
    }

    StringBuilder st = new StringBuilder();
    for (String jogo : jogoIterator) {
      st.append("\nJogo: " + jogo.toString());
    }
    st.append(" Unidades vendidas: " + minMap);
    return st.toString();

  }

  public String MaisVendidos(List<Compra> compras) {

    ArrayList<Jogo> jogos = new ArrayList<>();
    ArrayList<String> jogoIterator = new ArrayList<String>();

    for (Compra compra : compras) {
      jogos.addAll(compra.getJogos());
    }

    Map<Object, Long> maisVendido = jogos.stream().collect(
        Collectors.groupingBy(jogo -> jogo.getNome(), Collectors.counting()));
    Long maxMap = Collections.max(maisVendido.values());

    for (Map.Entry<Object, Long> jogo : maisVendido.entrySet()) {
      if (jogo.getValue() == maxMap) {
        jogoIterator.add((String) jogo.getKey());
        break;
      }
    }

    StringBuilder st = new StringBuilder();
    for (String jogo : jogoIterator) {
      st.append("\nJogo:" + jogo);
    }
    st.append(" Unidades vendidas: " + maxMap);
    return st.toString();

  }

  public double valorMedioCompras(List<Compra> compras) {
    double valorMedio = 0;
    for (Compra compra : compras) {
      valorMedio += compra.getPrecoFinal();
    }
    valorMedio = valorMedio / compras.size();
    return valorMedio;
  }

  // public void historicoDeCompras(ArrayList<Compra> compras, Cliente cliente) {
  // ArrayList<Compra> comprasCliente = new ArrayList<Compra>();
  // for (Compra compra : compras) {
  // if (compra.getCliente().getNomeUsuario() == cliente.getNomeUsuario()) {
  // comprasCliente.add(compra);
  // }
  // }
  // for (Compra compra : comprasCliente) {
  // System.out.println(compra.toString());
  // }
  // }

  public void historicoDeComprasTipoData(ArrayList<Compra> compras, Cliente cliente, Class tipo, LocalDate data) {

    int comprasSumary = 0;
    LocalDate inicio = LocalDate.MIN;

    LocalDate fim = LocalDate.MAX;
    if (data != null) {
      fim = data.plusMonths(1);
      fim = fim.with(firstDayOfMonth());
      inicio = data.minusMonths(1);
      inicio = inicio.with(lastDayOfMonth());
    }

    for (Compra compra : compras) {
      if (compra.getCliente().getNomeUsuario().toLowerCase().equals(cliente.getNomeUsuario().toLowerCase())) {
        if (compra.getDataCompra().isBefore(fim) && compra.getDataCompra().isAfter(inicio)) {
          if (tipo != null) {

            if (compra.getJogos().stream().anyMatch(c -> c.getClass().equals(tipo))) {

              compra.getJogos().stream().filter(c -> c.getClass().equals(tipo))
                  .forEach(x -> System.out.println(x.toString()));
              comprasSumary++;
            }

          } else {
            System.out.println(compra.toString());
            comprasSumary++;
          }

        }
      }
    }
    if (comprasSumary == 0) {
      System.out.println("Não a compras dentro desses parâmetros");

    }
  }

  public double valorMensal(List<Compra> compras, LocalDate data) {

    double valorMensal = 0;

    LocalDate fim = data.plusMonths(1);
    fim = fim.with(firstDayOfMonth());
    LocalDate inicio = data.minusMonths(1);
    inicio = inicio.with(lastDayOfMonth());

    for (Compra compra : compras) {
      if (compra.getDataCompra().isBefore(fim) && compra.getDataCompra().isAfter(inicio)) {
        valorMensal += compra.getPrecoFinal();
      }

    }
    return valorMensal;
  }
}
