package br.com.acangasolucoes.erp.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.service.CadastroUsuarioService;
import br.com.acangasolucoes.erp.service.NegocioException;
import br.com.acangasolucoes.erp.util.jsf.FacesUtil;

import java.io.Serializable;

@Named
@SessionScoped
public class CadastroUsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    @Produces
    @UsuarioEdicao
    private Usuario usuario;

    @Inject
    private Conversation conversacao;

    public CadastroUsuarioBean() {
 //       usuario = new Usuario();
        limpar();
    }

    public void salvar() throws NegocioException {
        try {
            usuario = cadastroUsuarioService.salvar(usuario);
            FacesUtil.addInfoMessage("Usuário salvo com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public void novo(){
        limpar();
    }

    public void usuarioAlterado(@Observes UsuarioAlteradoEvent event) {
        this.usuario = event.getUsuario();
    }

    public void inicializar() {
        if (this.usuario == null)
            usuario = new Usuario();

        if (conversacao.isTransient()) {
            conversacao.begin();
        }
    }

    private void limpar() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
