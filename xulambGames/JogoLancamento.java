package xulambGames;


public class JogoLancamento extends Jogo{

    private double ajustePreco = 1.1;
    
    public JogoLancamento(){
        super();
    }
    public JogoLancamento(String nome, double preco) {
        super(nome, preco);
    }
    
    @Override
    public double getDesconto() {
        return ajustePreco;
    }
}
