package br.com.acangasolucoes.erp.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.Usuarios;
import br.com.acangasolucoes.erp.repository.filter.UsuarioFilter;
import br.com.acangasolucoes.erp.service.NegocioException;
import br.com.acangasolucoes.erp.util.jsf.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    private Usuario usuarioSelecionado;

    private UsuarioFilter filtro;

    private List<Usuario> usuariosFiltrados;

    public PesquisaUsuariosBean() {
        usuariosFiltrados = new ArrayList<>();
        filtro = new UsuarioFilter();
    }

    public void pesquisar() {
        usuariosFiltrados = usuarios.filtratos(filtro);
    }

    public void remover() throws NegocioException {
        try {
            usuarios.remover(usuarioSelecionado);
            usuariosFiltrados.remove(usuarioSelecionado);
            FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " removido com sucesso");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public void setFiltro(UsuarioFilter filtro) {
        this.filtro = filtro;
    }
}
