package xulambGames;

import java.util.Random;

public class JogoPromocao extends Jogo {
    private int promocaoRangeMin = 70;
    private int promocaoRangeMax = 50;
    public JogoPromocao(){
        super();
    }
    public JogoPromocao(String nome, double preco) {
        super(nome, preco);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public double getDesconto() {
        double desconto=0;
        Random random = new Random();
		desconto = random.nextInt((promocaoRangeMin - promocaoRangeMax) + 1) + promocaoRangeMax;
        desconto = desconto / 100;
        return desconto;
    }
}
