package domain;

import java.sql.Timestamp;

public class SessaoTeste {

    private int idSessao;
    private String nomeSessao;
    private Timestamp dataSessao;
    private String descricaoSessao;
    private String nomeTestador;
    private Long estrategiaId;
    private Long projetoId;
    private int usuarioId;
    private int tempo;

    public int getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public String getNomeSessao() {
        return nomeSessao;
    }

    public void setNomeSessao(String nomeSessao) {
        this.nomeSessao = nomeSessao;
    }

    public Timestamp getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(Timestamp dataSessao) {
        this.dataSessao = dataSessao;
    }

    public String getDescricaoSessao() {
        return descricaoSessao;
    }

    public void setDescricaoSessao(String descricaoSessao) {
        this.descricaoSessao = descricaoSessao;
    }

    public String getNomeTestador() {
        return nomeTestador;
    }

    public void setNomeTestador(String nomeTestador) {
        this.nomeTestador = nomeTestador;
    }

    public Long getEstrategiaId() {
        return estrategiaId;
    }

    public void setEstrategiaId(Long estrategiaId) {
        this.estrategiaId = estrategiaId;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
