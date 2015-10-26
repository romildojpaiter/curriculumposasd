package br.com.gpaiter.curriculumposasd.domain;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class ExperienciaProfissional {

    private long id;
    private String descricao;
    private Integer inicio;
    private Integer termino;
    private boolean empregoAtual;

    public ExperienciaProfissional() {
    }

    public ExperienciaProfissional(long id, String descricao, Integer inicio, Integer termino, boolean empregoAtual) {
        this.id = id;
        this.descricao = descricao;
        this.inicio = inicio;
        this.termino = termino;
        this.empregoAtual = empregoAtual;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getTermino() {
        return termino;
    }

    public void setTermino(Integer termino) {
        this.termino = termino;
    }

    public boolean isEmpregoAtual() {
        return empregoAtual;
    }

    public void setEmpregoAtual(boolean empregoAtual) {
        this.empregoAtual = empregoAtual;
    }
}
