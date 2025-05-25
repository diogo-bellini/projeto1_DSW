package domain;

public class Projeto {
    private Long id_projeto;
    private String nome;
    private String descricao;

    public Projeto() { }

    public Projeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id_projeto;
    }

    public void setId(Long id) {
        this.id_projeto = id;
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
}
