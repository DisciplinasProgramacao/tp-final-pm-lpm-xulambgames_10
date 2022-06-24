package xulambGames;

public class FabricaClienteFanatico implements IFabricaCliente {


    @Override
    public Cliente criar(String nome, String nomeUsuario, String senha, String email) {
        // TODO Auto-generated method stub
        return new ClienteFanatico(nome, nomeUsuario, senha, email);
    }
    
}
