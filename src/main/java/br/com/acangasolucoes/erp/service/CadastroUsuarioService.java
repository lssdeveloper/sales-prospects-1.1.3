package br.com.acangasolucoes.erp.service;

import javax.inject.Inject;

import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.Usuarios;
import br.com.acangasolucoes.erp.util.jpa.Transactional;

import java.io.Serializable;

public class CadastroUsuarioService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) throws NegocioException {
        Usuario clone = usuarios.porEmail(usuario.getEmail());

        if (clone != null && usuario.getId() == null)
            throw new NegocioException("Já existe um usuário com o e-mail " + usuario.getEmail());

        return usuarios.salvar(usuario);
    }
}
