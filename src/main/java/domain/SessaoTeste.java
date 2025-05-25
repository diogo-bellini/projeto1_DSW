package domain;

import java.sql.Timestamp;

public class SessaoTeste {

    private Long idSessao;
    private String descricao;
    private String nomeTestador;
    private Long estrategiaId;
    private int tempo;
    private Long projetoId;
    private Long usuarioId;
    private Status status;
    private Timestamp dataCriacao;

    // Getters e Setters

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
