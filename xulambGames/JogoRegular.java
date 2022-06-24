package xulambGames;

import java.util.Random;

public class JogoRegular extends Jogo {
    private int promocaoRangeMin =  100;
    private int promocaoRangeMax = 70;
    public JogoRegular(){
        super();
    }
    public JogoRegular(String nome, double preco) {
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
