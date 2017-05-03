package br.com.acangasolucoes.erp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.acangasolucoes.erp.model.Grupo;
import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.Usuarios;
import br.com.acangasolucoes.erp.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
        Usuario usuario = usuarios != null ? usuarios.porEmail(email) : null;

        UsuarioSistema user = null;

        if (usuario != null) {
            user = new UsuarioSistema(usuario, getGrupos(usuario));
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return user;
    }

    private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Grupo grupo : usuario.getGrupos()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + grupo.getNome().toUpperCase()));
        }

        return authorities;
    }
}
