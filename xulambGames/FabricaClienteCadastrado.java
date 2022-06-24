package xulambGames;

public class FabricaClienteCadastrado implements IFabricaCliente {

    @Override
    public Cliente criar(String nome, String nomeUsuario, String senha, String email) {
        // TODO Auto-generated method stub
        return new ClienteCadastrado(nome, nomeUsuario, senha, email);
    }
    
}
