package xulambGames;

import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class Jogo implements Serializable{
    private double preco;
    private String nome;
    public Jogo(){
        setNome("vazio");
        setPreco(0);
    }

    public Jogo(String nome, double preco){
        setNome(nome);
        setPreco(preco);
    }

    public double getPreco(){
        return preco;
    }
    public String getNome(){
        return this.nome;
    }
    private void setNome(String nome){
        this.nome = nome;
    }
    private void setPreco(double preco){
        this.preco = preco;
    }
    public abstract double getDesconto();
    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return getNome() + " " + " RS" + decimalFormat.format(getPreco()* getDesconto());
    }
}
