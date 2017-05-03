package br.com.acangasolucoes.erp.controller;

import br.com.acangasolucoes.erp.model.Usuario;

public class UsuarioAlteradoEvent {

    private Usuario usuario;

    public UsuarioAlteradoEvent(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
