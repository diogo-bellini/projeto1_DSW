package domain;
import java.sql.Timestamp;


public class SessaoTeste {

    private int id_sessao;
    private String nome_sessao;
    private Timestamp data_sessao;
    private String descricao_sessao;
    private String nomeTestador;

    private int estrategia_id;

    private int projeto_id;

    private int usuario_id;

    private int tempo;

    public int getId() {
        return id_sessao;
    }

    public void setId(int id_sessao) {
        this.id_sessao = id_sessao;
    }

    public String getDescricao() {
        return descricao_sessao;
    }

    public void setDescricao(String descricao) {
        this.descricao_sessao = descricao_sessao;
    }

    public String getNomeTestador() {
        return nomeTestador;
    }

    public void setNomeTestador(String nomeTestador) {
        this.nomeTestador = nomeTestador;
    }

    public int getEstrategiaId() {
        return estrategia_id;
    }

    public void setEstrategiaId(int estrategia_id ) {
        this.estrategia_id = estrategia_id;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getProjetoId() {
        return projeto_id;
    }

    public void setProjetoId(int projetoId) {
        this.projeto_id = projeto_id;
    }

    public int getUsuarioId() {
        return usuario_id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuario_id = usuario_id;
    }

    public Timestamp getDataSessao() {
        return data_sessao;
    }

    public void setDataSessao(Timestamp data_sessao) {
        this.data_sessao = data_sessao;
    }
}
