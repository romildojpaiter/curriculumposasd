package br.com.gpaiter.curriculumposasd.domain;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class FormacaoAcademica {

    private long id;
    private String descricao;
    private Integer inicio;
    private Integer termino;

    public FormacaoAcademica() {
    }

    public FormacaoAcademica(long id, String descricao, Integer inicio, Integer termino) {
        this.id = id;
        this.descricao = descricao;
        this.inicio = inicio;
        this.termino = termino;
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
}
