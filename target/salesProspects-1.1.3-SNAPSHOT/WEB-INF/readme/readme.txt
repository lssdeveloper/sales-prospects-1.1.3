	<groupId>com.sales_prospect</groupId>
	<artifactId>salesProspects</artifactId>
	<version>0.1.2-SNAPSHOT</version>
	
	Alterações realizadas:
	Problema
	-Foi encontrado um bug no cadastro de empresas:
		Ao editar a uma empresa o dialog não exibia os conteúdos respectivos a empresa cadastrada.
		O erro estava relacionado ao campo ramoAtividade.descricao, conhecido como LazyInitializationException no Hibernate.
		
		A solução encontrada foi no modelo alterar o relacionamento com RamoAtividade.
		
	@NotNull
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name = "ramo_atividade_id", nullable = false)
	private RamoAtividade ramoAtividade;
	
	Outras alterações, estas menos importantes.
	
	-Todos os cadastros, no modelo foi incluído nos campos com descrição o .toUpperCase(), respectivamente, ex: setDescricao
	-Incluído o Logo da Acanga.
	
	
	[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building sales_prospects 1.1.2-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ salesProspects ---
[INFO] Deleting /home/leandro/git/sales-prospects/sales-prospects/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ salesProspects ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 6 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.6.0:compile (default-compile) @ salesProspects ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 67 source files to /home/leandro/git/sales-prospects/sales-prospects/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ salesProspects ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.6.0:testCompile (default-testCompile) @ salesProspects ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /home/leandro/git/sales-prospects/sales-prospects/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ salesProspects ---
[INFO] 
[INFO] --- maven-war-plugin:2.2:war (default-war) @ salesProspects ---
[INFO] Packaging webapp
[INFO] Assembling webapp [salesProspects] in [/home/leandro/git/sales-prospects/sales-prospects/target/salesProspects-1.1.2-SNAPSHOT]
[INFO] Processing war project
[INFO] Copying webapp resources [/home/leandro/git/sales-prospects/sales-prospects/src/main/webapp]
[INFO] Webapp assembled in [575 msecs]
[INFO] Building war: /home/leandro/git/sales-prospects/sales-prospects/target/salesProspects-1.1.2-SNAPSHOT.war
[INFO] WEB-INF/web.xml already added, skipping
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 5.783 s
[INFO] Finished at: 2017-04-27T19:24:18-03:00
[INFO] Final Memory: 24M/264M
[INFO] ------------------------------------------------------------------------