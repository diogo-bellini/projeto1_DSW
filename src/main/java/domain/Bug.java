package domain;

import java.sql.Timestamp;

public class Bug {
    private Long idBug;
    private String descricao;
    private Timestamp dataCriacao;
    private Long sessaoId;

    public Bug() {}

    public Bug(String descricao, Long sessaoId) {
        this.descricao = descricao;
        this.sessaoId = sessaoId;
    }

    public Long getIdBug() {
        return idBug;
    }

    public void setIdBug(Long idBug) {
        this.idBug = idBug;
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

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "idBug=" + idBug +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", sessaoId=" + sessaoId +
                '}';
    }
}
