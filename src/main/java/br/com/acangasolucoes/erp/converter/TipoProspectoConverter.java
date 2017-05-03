package br.com.acangasolucoes.erp.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.acangasolucoes.erp.model.TipoProspecto;

//@FacesConverter(forClass = TipoProspecto.class)
public class TipoProspectoConverter implements Converter {
	
	//private TipoProspectos tipoProspectos;
	private List<TipoProspecto> listaTipoProspectos;
	
	public TipoProspectoConverter(List<TipoProspecto> listaTipoProspectos) {
		this.listaTipoProspectos = listaTipoProspectos;
	}

//	public TipoProspectoConverter(){
//		tipoProspectos = CDIServiceLocator.getBean(TipoProspectos.class);
//	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
//		TipoProspecto tipoProspecto;
//		
//		if (value != null && value.equals("")) {
//			Long id = new Long(value);
//			tipoProspecto = tipoProspectos.porId(id);
//			return tipoProspecto;			
//		}
//		return null;
		
		if (value == null) {
			return null;
		}
		Long id = Long.valueOf(value);
		
		for (TipoProspecto tipoProspecto : listaTipoProspectos) {
			if (id.equals(tipoProspecto.getId())) {
				return tipoProspecto;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        
		if (value == null) {
			return null;
		}
		
		TipoProspecto tipoProspecto = (TipoProspecto) value;
		
		return tipoProspecto.getId().toString();

	}
	public List<TipoProspecto> getListaTipoProspectos() {
		return listaTipoProspectos;
	}

	public void setListaEmpresas(List<TipoProspecto> listaTipoProspectos) {
		this.listaTipoProspectos = listaTipoProspectos;
	}
	
	

}
