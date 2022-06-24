package xulambGames;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Compra implements Serializable{

    private List<Jogo> jogos;
    private LocalDate dataCompra;
    private Cliente cliente;
    private double precoFinal;

    public Compra(List<Jogo> jogos, Cliente cliente) {
        setDataCompra();
        setJogos(jogos);
        setCliente(cliente);
        calculaPreco();
    }

    private void calculaPreco() {
        double precoFinal = 0;
        for (Jogo jogo : jogos) {
            precoFinal += (jogo.getPreco() * jogo.getDesconto());
        }
        precoFinal = precoFinal * calculaDesconto();
        precoFinal = precoFinal * cliente.getDesconto();
        // desconto compra
        // desconto cliente
        setPrecoFinal(precoFinal);
    }

    private double calculaDesconto() {
        double descontoVinte = 0.8;
        double descontoDez = 0.9;
        int jogosPromocao = jogos.stream().filter(j -> j.getClass().equals(JogoPromocao.class))
                .collect(Collectors.toList()).size();
        int jogosRegulares = jogos.stream().filter(j -> j.getClass().equals(JogoRegular.class))
                .collect(Collectors.toList()).size();
        int jogosPremium = jogos.stream().filter(j -> j.getClass().equals(JogoPremium.class))
                .collect(Collectors.toList()).size();
        int jogosLancamento = jogos.stream().filter(j -> j.getClass().equals(JogoLancamento.class))
                .collect(Collectors.toList()).size();

        if (jogosLancamento >= 2) {
            return descontoVinte;
        }
        if (jogosPremium >= 3) {
            return descontoVinte;
        }
        if (jogosRegulares >= 5) {
            return descontoVinte;
        }
        if (jogosPremium == 2 && (jogosPromocao + jogosRegulares + jogosLancamento) >= 1) {
            return descontoVinte;
        }
        if (jogosRegulares == 3 && (jogosPromocao + jogosPremium + jogosLancamento)>= 1) {
            return descontoVinte;
        }

        if (jogosPremium == 2) {
            return descontoDez;
        }
        if (jogosRegulares == 4) {
            return descontoDez;
        }
        return 1;
    }

    private void setDataCompra() {
        this.dataCompra = LocalDate.now();
    }

    public LocalDate getDataCompra() {
        return this.dataCompra;
    }

    private void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public List<Jogo> getJogos() {
        return this.jogos;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrecoFinal() {
        
        return this.precoFinal;
    }

    private void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }
    @Override
        public String toString(){
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            StringBuilder st = new StringBuilder();
            for (Jogo jogo :jogos){
                st.append("\nJogo:" + jogo.toString());
            }
            st.append("\nUsuario:"+ cliente.getNomeUsuario());
            st.append("\nPreço final com desconto: " + decimalFormat.format(getPrecoFinal()));
            return st.toString();
    }
}
