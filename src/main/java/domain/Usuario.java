package domain;

import java.sql.Timestamp;

public class Usuario {
    private Long id_usuario;
    private String nome;
    private String email;
    private String senha;
    private Papel papel;
    private Timestamp data_criacao;

    public Usuario(Long id) {
        this.id_usuario = id;
    }

    public Usuario(String nome, String login, String senha, Papel papel) {
        super();
        this.nome = nome;
        this.email = login;
        this.senha = senha;
        this.papel = papel;
    }

    public Usuario(Long id, String nome, String login, String senha, Papel papel) {
        super();
        this.id_usuario = id;
        this.nome = nome;
        this.email = login;
        this.senha = senha;
        this.papel = papel;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return email;
    }

    public void setLogin(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public Timestamp getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Timestamp data_criacao) {
        this.data_criacao = data_criacao;
    }
}
