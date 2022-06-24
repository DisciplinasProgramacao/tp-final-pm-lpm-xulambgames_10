package xulambGames;

public class JogoPremium extends Jogo {
    private double ajustePreco = 1;
    public JogoPremium(){
        super();
    }
    public JogoPremium(String nome, double preco) {
        super(nome, preco);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public double getDesconto() {
        return ajustePreco;
    }
}
