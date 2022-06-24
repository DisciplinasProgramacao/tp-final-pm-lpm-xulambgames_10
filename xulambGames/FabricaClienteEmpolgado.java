package xulambGames;

public class FabricaClienteEmpolgado implements IFabricaCliente{


    @Override
    public Cliente criar(String nome, String nomeUsuario, String senha, String email) {
        // TODO Auto-generated method stub
        return new ClieneteEmpolgado(nome,nomeUsuario,senha,email);
    }
    
}
