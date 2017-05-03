package br.com.acangasolucoes.erp.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.acangasolucoes.erp.model.Usuario;

public class UsuarioConverterLst implements Converter {
	
	private List<Usuario> listaUsuarios;
	
	public UsuarioConverterLst(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		
		for (Usuario usuario : listaUsuarios) {
			if (id.equals(usuario.getId())) {
				return usuario;
			}
		}
		return null;
	}


	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value == null) {
			return null;
		}
		
		Usuario usuario = (Usuario) value;
		
		return usuario.getId().toString();
	}
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
