package br.com.acangasolucoes.erp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.repository.Contatos;
import br.com.acangasolucoes.erp.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Contato.class)
public class ContatoConverter implements Converter {
	

	private Contatos contatos;
	
	public ContatoConverter() {
	   contatos = CDIServiceLocator.getBean(Contatos.class);
	}


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
//		Contato retorno = null;
//		
//		if (StringUtils.isNotBlank(value)) {
//			retorno = this.contatos.buscarComEmpresaPeloId(new Long(value));
//		}
//		return retorno;
		Contato contato;
		
		if (value != null && !value.equals("")) {
			Long id = new Long(value);
			contato = contatos.porId(id);
			return contato;
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Contato contato = (Contato) value;
			return contato.getId() == null ? null : contato.getId().toString();			
		}
		return "";
	}

}
