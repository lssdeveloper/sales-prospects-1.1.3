package br.com.acangasolucoes.erp.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.acangasolucoes.erp.model.Usuario;
import br.com.acangasolucoes.erp.repository.Usuarios;
import br.com.acangasolucoes.erp.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

    private Usuarios usuarios;

    public UsuarioConverter() {
        usuarios = CDIServiceLocator.getBean(Usuarios.class);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Usuario usuario;

        if (s != null && !s.equals("")) {
            Long id = new Long(s);
            usuario = usuarios.porId(id);
            return usuario;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o != null) {
            Usuario usuario = (Usuario) o;
            return usuario.getId() == null ? null : usuario.getId().toString();
        }
        return "";
    }
}
