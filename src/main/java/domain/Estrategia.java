package domain;

public class Estrategia {
    private Long id_estrategia;
    private String nome;
    private String descricao;
    private String exemplo;
    private String dica;

    public Estrategia (){ }

    public Estrategia (String nome, String descricao, String exemplo, String dica){
        this.nome = nome;
        this.descricao = descricao;
        this.exemplo = exemplo;
        this.dica = dica;
    }

    public Long getid_estrategia(){
        return id_estrategia;
    }


    public void setid_estrategia(Long id_estrategia){
        this.id_estrategia = id_estrategia;
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

    public String getExemplo() {
        return exemplo;
    }
    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }

    public String getDica() {
        return dica;
    }
    public void setDica(String dica) {
        this.dica = dica;
    }

    public void setId_estrategia(long idEstrategia) {
        Estrategia estrategia = this;
        estrategia.id_estrategia = idEstrategia;
    }
}




