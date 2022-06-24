package xulambGames;

public class ClieneteEmpolgado extends Cliente {
    private static final int mensalidade = 10;
    private final double desconto = 0.9;

    public ClieneteEmpolgado(String nome, String nomeUsuario, String senha,String email) {
        super(nome, nomeUsuario, senha, mensalidade, email);
    }


    @Override
    public double getDesconto() {
        return desconto;
    }
    
}
