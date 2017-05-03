package br.com.acangasolucoes.erp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.acangasolucoes.erp.model.Empresa;
import br.com.acangasolucoes.erp.repository.Empresas;
import br.com.acangasolucoes.erp.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter {
	
	private Empresas empresas;
	
	public EmpresaConverter() {
		empresas = CDIServiceLocator.getBean(Empresas.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Empresa empresa;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			empresa = empresas.porId(id);
			return empresa;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		
		if (value != null) {
			Empresa empresa = (Empresa) value;
			return empresa.getId() == null ? null : empresa.getId().toString();			
		}
		return "";
	}	

}
