package xulambGames;

import javax.management.InvalidAttributeValueException;

public class FabricaCliente {
    static Cliente criar(String tipo,String nome, String nomeUsuario, String senha, String email) throws InvalidAttributeValueException{
        switch (tipo.toLowerCase()){
            case "cadastrado": return new ClienteCadastrado(nome,nomeUsuario,senha,email);
            case "empolgado": return new ClieneteEmpolgado(nome,nomeUsuario,senha,email);
            case "fanatico": return new ClienteFanatico(nome,nomeUsuario,senha,email);
            default: throw new InvalidAttributeValueException("Tipo de cliente inexistente");
        }
    }   
}

