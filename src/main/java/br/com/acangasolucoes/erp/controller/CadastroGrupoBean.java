package br.com.acangasolucoes.erp.controller;


import org.primefaces.context.RequestContext;

import br.com.acangasolucoes.erp.model.Grupo;
import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.service.CadastroUsuarioService;
import br.com.acangasolucoes.erp.service.NegocioException;
import br.com.acangasolucoes.erp.util.jsf.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
public class CadastroGrupoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    @UsuarioEdicao
    private Usuario usuario;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    @Inject
    private Event<UsuarioAlteradoEvent> usuarioAlteradoEventEvent;

    private Grupo grupo;
    private List<Grupo> gruposDetails;



    public CadastroGrupoBean() {
        grupo = new Grupo();
        gruposDetails = new ArrayList<>();
    }

    public void adicionar() throws NegocioException {
        try {
            this.usuario.getGrupos().add(grupo);
            this.usuario = cadastroUsuarioService.salvar(usuario);
            usuarioAlteradoEventEvent.fire(new UsuarioAlteradoEvent(this.usuario));
            gruposDetails = this.usuario.getGrupos();
            FacesUtil.addInfoMessage("Grupo salvo com sucesso.");
            RequestContext.getCurrentInstance().update(Arrays.asList("frm:msg"));
            limpar();

        } catch (NegocioException e) {
            FacesUtil.addErrorMessage("Não foi possível salvar o grupo.");
        }
    }

    private void limpar() {
        grupo = new Grupo();
    }

    public void inicializar() {
        if (usuario.getGrupos().size() > 0)
            gruposDetails = usuario.getGrupos();
    }

    /**
     *
     */

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getGruposDetails() {
        return gruposDetails;
    }
}
