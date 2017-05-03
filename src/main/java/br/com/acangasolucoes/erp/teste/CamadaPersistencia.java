package br.com.acangasolucoes.erp.teste;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/*
import br.com.acangasolucoes.erp.model.Contato;
import br.com.acangasolucoes.erp.model.Prospeccao;
import br.com.acangasolucoes.erp.model.TipoProspeccao;
import br.com.acangasolucoes.erp.repository.Prospeccoes;
*/

public class CamadaPersistencia {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AcangaPU");
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
	

		
		//Testando persistência em RAMO DE ATIVIDADES
/*
		RamoAtividade ramoAtividade = new RamoAtividade();
		ramoAtividade.setDescricao("Segurança");
	
		ramoAtividades.salvar(ramoAtividade);
		
		em.getTransaction().commit();
		
		//Verificando se a inserão funcionou
		List<RamoAtividade> listaRamoAtividade = ramoAtividades.todas();
		System.out.println(listaRamoAtividade);
		
		em.close();
		emf.close();

		RamoAtividades ramoAtividades = new RamoAtividades(em);
		Empresas empresas = new Empresas(em);
		Usuarios usu = new Usuarios(em);
		TipoProspectos tps = new TipoProspectos(em);
		
		//Buscando as informações do banco
		List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.todas();
		List<Empresa> listaDeEmpresas = empresas.todas();
		List<Usuario> listaUsu = usu.todos();	
		List<TipoProspecto> listaTPs = tps.todos();
		System.out.println(listaDeEmpresas);
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println(listaDeRamoAtividades);
		System.out.println("-------------------------------------------------------------------------------------------------------");		
		System.out.println(listaUsu);
		System.out.println("-------------------------------------------------------------------------------------------------------");		
		System.out.println(listaTPs);
		System.out.println("-------------------------------------------------------------------------------------------------------");		
*/
		em.close();
		emf.close();
		/*
		
		//Testando a persistência em Prospeccao

		//Criando um objeto Contato;
		Contato c1 = new Contato();
		c1.setId(Long.valueOf(1));
		
		
		//Obtendo um objeto TipoProspeccao (Enum)
		
		Prospeccoes p = new Prospeccoes(em);
		
		Prospeccao p1 = new Prospeccao();
		p1.setContato(c1);
		p1.setTipo(TipoProspeccao.VISITA);
		p1.setDataCadastro(new Date());
		p1.setObservacao("Esse é um teste de persistência na tabela de prospecção,após inserção deletar.");
		p.salvar(p1);
		em.getTransaction().commit();
		//Verificando se a inserão funcionou
		List<Prospeccao> lstProspeccao = p.todas();
		System.out.println(lstProspeccao);
		
		em.close();
		emf.close();
		
		*/
/*

*/		
/*		
		//Declarando os repositórios
		RamoAtividades ramoAtividades = new RamoAtividades(em);
		Empresas empresas = new Empresas(em);
		
		//Buscando as informações do banco
		List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar("");
		List<Empresa> listaDeEmpresas = empresas.pesquisar("");
		System.out.println(listaDeEmpresas);
		
		//Criando uma empresa
		Empresa empresa = new Empresa();		
		empresa.setNomeFantasia("Acanga Soluções");
		empresa.setCnpj("41.952.519/0001-57");
		empresa.setRazaoSocial("Acanga Soluções 41952519000157");
		empresa.setTipo(TipoEmpresa.LTDA);
		empresa.setDataCadastro(new Date());
		empresa.setRamoAtividade(listaDeRamoAtividades.get(9));
		
		//Salvando a empresa
		empresas.salvar(empresa);
	
		//Criando um contato
		
		Contatos contatos = new Contatos(em);
		
		Contato contato = new Contato();
		contato.setNome("Leandro Serra");
		contato.setEmail("leandro.serra@acangasolucoes.com.br");
		contato.setEmpresa(listaDeEmpresas.get(3)); 
		contato.setTelefone("(21)3377-9852");
		contato.setCelular("(21)98308-2828");
		contato.setDataCadastro(new Date());
		
		//salvando o contato
		contatos.salvar(contato);
		
		//Criando uma Ação
		Acoes acoes = new Acoes(em);
		
		Acao acao = new Acao();
		List<Contato> listaDeContatos = contatos.pesquisar("Leandro");
		acao.setContato(listaDeContatos.get(0));
		acao.setTipo(TipoAcao.TEL);
		acao.setData(new Date());
		
		acoes.salvar(acao);
		
		em.getTransaction().commit();
		
		//Verificando se a inserão funcionou
		List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
		System.out.println(listaDeEmpresas2);
		
		
		System.out.println(listaDeContatos);
		
		List<Acao> listaDeAcao = acoes.pesquisar(TipoAcao.TEL);
		System.out.println(listaDeAcao);
		
		em.close();
		emf.close();
*/		
	}

}
