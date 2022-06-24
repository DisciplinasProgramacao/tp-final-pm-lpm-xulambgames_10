package xulambGames;

public class ClienteCadastrado extends Cliente {
    private static final int mensalidade = 0;
    private final double desconto = 1;

    public ClienteCadastrado(String nome, String nomeUsuario, String senha, String email) {
        super(nome, nomeUsuario, senha, mensalidade, email);
    }


  

    @Override
    public double getDesconto() {
        return desconto;
    }


    
}
