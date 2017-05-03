package br.com.acangasolucoes.erp.repository.filter;

import java.io.Serializable;

public class CidadeFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descricao;
    private String nomeEstado;


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }
}
