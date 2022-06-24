package xulambGames;

public class ClienteFanatico extends Cliente {
    private static final int mensalidade = 25;
    private final double desconto = 0.70;

    public ClienteFanatico(String nome, String nomeUsuario, String senha,String email) {
        super(nome, nomeUsuario, senha, mensalidade, email);
    }


    @Override
    public double getDesconto() {
        return desconto;
    }

   
    
}
