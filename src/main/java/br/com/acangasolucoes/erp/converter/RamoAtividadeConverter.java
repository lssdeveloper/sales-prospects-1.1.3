package br.com.acangasolucoes.erp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.acangasolucoes.erp.model.RamoAtividade;
import br.com.acangasolucoes.erp.repository.RamoAtividades;
import br.com.acangasolucoes.erp.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = RamoAtividade.class)
public class RamoAtividadeConverter implements Converter {

	private RamoAtividades ramoAtividades;
	
	public RamoAtividadeConverter() {
		ramoAtividades = CDIServiceLocator.getBean(RamoAtividades.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		RamoAtividade ramoAtividade;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			ramoAtividade = ramoAtividades.porId(id);
			return ramoAtividade;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			RamoAtividade ramoAtividade = (RamoAtividade) value;
			return ramoAtividade.getId() == null ? null : ramoAtividade.getId().toString();			
		}
		return "";
	}
}
