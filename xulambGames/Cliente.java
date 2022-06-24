package xulambGames;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
    private String nome;
    private String nomeUsuario;
    private String senha;
    private int mensalidade;
    private String email;

    public Cliente(String nome,String nomeUsuario, String senha, int mensalidade,String email){
        setNome(nome);
        setNomeUsuario(nomeUsuario);
        setSenha(senha);
        setMensalidade(mensalidade);
        setEmail(email);
    }
    
    public String getNome(){
        return this.nome;
    }
    public String getNomeUsuario(){
        return this.nomeUsuario;
    }
    public String getSenha(){
        return this.senha;
    }
    public int getMensalidade(){
        return this.mensalidade;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setMensalidade(int mensalidade){
        this.mensalidade = mensalidade;
    }
    public String getEmail(){
        return this.email;
    }
    private void setEmail(String email){
        this.email = email;
    }
    public abstract double getDesconto();
}
