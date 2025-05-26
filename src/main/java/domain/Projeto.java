package domain;

import java.sql.Timestamp;

public class Projeto {
    private Long id_projeto;
    private String nome;
    private String descricao;
    private Timestamp dataCriacao;

    public Projeto() { }

    public Projeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(Long id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id_projeto=" + id_projeto +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Projeto projeto = (Projeto) o;

        return id_projeto != null ? id_projeto.equals(projeto.id_projeto) : projeto.id_projeto == null;
    }

    @Override
    public int hashCode() {
        return id_projeto != null ? id_projeto.hashCode() : 0;
    }
}