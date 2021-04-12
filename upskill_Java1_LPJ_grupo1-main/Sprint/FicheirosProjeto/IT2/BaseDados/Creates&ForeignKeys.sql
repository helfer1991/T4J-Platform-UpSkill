create table Utilizador(
email varchar(50)
 constraint pkUser primary key,
nome varchar(40)
 constraint nnUserNome not null,
telefone integer
 constraint nnUserTelefone not null
 constraint ckUserTelefone check (regexp_like(telefone, '^\d{6,}$'))
);

create table Classificacao (
idProcessoSeriacao varchar(10)
 constraint nnClassificacaoidProcSer not null,
dataHora date
 constraint nnClassificacaoDataHora not null,
lugarClassificacao integer
 constraint nnClassificacaoLugarClassificacao not null,
idCandidatura integer
 constraint nnClassificacaoIdCandidatura not null,
 constraint pkClassificacao primary key (idProcessoSeriacao,idCandidatura)
);


create table Organizacao (
nif integer 
 constraint pkOrganizacao primary key
 constraint ckOrganizacaoNIF check (regexp_like(nif, '^\d{9}$')),
email varchar(50)
 constraint nnOrganizacaoEmail not null
 constraint ukOrganizacaoEmail UNIQUE,
idEnderecoPostal integer
 constraint nnOrgIdEndPostal not null,
emailGestor varchar(50),
website varchar(50) not null 
);

create table Freelancer (
nif integer 
 constraint pkFreelancer primary key
 constraint ckFreelancerNIF check (regexp_like(nif, '^\d{9}$')),
email varchar(50)
 constraint nnFreelancerEmail not null
 constraint ukFreelancerEmail UNIQUE,
idEnderecoPostal integer
 constraint nnFreelancerIdEndPostal not null
);

create table Candidatura(
id integer generated as identity constraint pkCandidatura primary key,
dataCandidatura date constraint nnCandDate not null,
valorPretendido FLOAT constraint nnCandValorPretendido not null,
nrDias integer constraint nnCandNrDias not null,
txtApresentacao varchar(50),
txtMotivacao varchar(50),
nifFreelancer integer 
 constraint nnCandNifFreelancer not null
 constraint ckCandNifFreelancer check (regexp_like(nifFreelancer, '^\d{9}$')),
idAnuncio varchar(7)
 constraint nnidAnuncio not null
);


create table ListaColaboradorProcessoSeriacao (
idProcessoSeriacao varchar(10)
 constraint nnListaColabProcSeridProcSer not null,
emailColaborador varchar(50)
 constraint nnListaColabProcSerEmailColaborador not null
);

create table Anuncio (
id varchar(7)
constraint pkAnuncio primary key,
dtInicioPublicacao date
 constraint nnAnunciodtInicioPub not null,
dtFimPublicacao date
constraint nnAnunciodtFimPub not null,
dtInicioCandidatura date
constraint nnAnunciodtInicioCand not null,
dtFimCandidatura date
constraint nnAnunciodtFimCand not null,
dtInicioSeriacao date
 constraint nnAnunciodtInicioSer not null,
dtFimSeriacao date
 constraint nnAnunciodtFimSer not null,
idTipoRegimento integer
 constraint nnAnuncioidTipoReg not null,
nifOrganizacao integer 
 constraint ckAnuncioNifOrganizacao check (regexp_like(nifOrganizacao, '^\d{9}$'))
);

create table Colaborador (
emailColaborador varchar(50)
 constraint pkColaborador primary key
 constraint nnColaboradorEmail not null,
nifOrganizacao integer
 constraint nnColaboradorNifOrganizacao not null
 constraint ckColaboradorNifOrganizacao check (regexp_like(nifOrganizacao, '^\d{9}$'))
);

create table EnderecoPostal(
id integer generated as identity
 constraint pkEnderecoPostal primary key,
morada varchar(40) constraint nnEndPostalMorada not null,
codigoPostal varchar(40) 
 constraint nnEndPostalCodPostal not null,
localidade varchar(40)
 constraint nnEndPostalLocalidade not null
);

create table HabilitacaoAcademica(
id integer generated as identity
 constraint pkHabilitacaoAcademica primary key,
grau varchar(40) constraint nnHabAcadGrau not null,
designacaoCurso varchar(40) constraint nnHabAcadDesigCurso not null,
nomeInstituicao varchar(40) constraint nnHabAcadNomeInstituicao not null,
mediaCurso FLOAT 
 constraint nnHabAcadMediaCurso not null
 constraint ckHabAcadMediaCursoPositive check (mediaCurso > 0)
 constraint ckHabAcadMediaCursoUnder20 check (mediaCurso <= 20),
nifFreelancer integer 
 constraint nnHabAcadNifFreelancer not null
 constraint ckHabAcadNifFreelancer check (regexp_like(nifFreelancer, '^\d{9}$'))
);




create table TipoRegimento(
id integer generated as identity
 constraint pkTipoRegimento primary key,
designacao varchar(50)
 constraint nnTipoRegimentoDesig not null,
descricao varchar(50)
 constraint nnTipoRegimentodescricao not null
);

create table ProcessoSeriacao(
dataRealizacao date 
 constraint nnProcessoSeriacaoDataRealizacao not null,
emailColaborador varchar(50) 
 constraint nnProcessoSeriacaoEmailColaborador not null,
idAnuncio varchar(10) 
constraint nnProcessoSeriacaoIdAnuncio not null
constraint pkProcessoSeriacao primary key
);


create table Tarefa(
referencia varchar(7) 
 constraint nnTarefaReferencia not null,
designacao varchar(50)
 constraint nnTarefaDesign not null,
descInformal varchar(50)
 constraint nnTarefaDescInformal not null,
descTecnica varchar(200)
 constraint nnTarefaDescTecnica not null,
duracaoEst integer 
 constraint nnTarefaDuracaoEst not null,
custoEst FLOAT
 constraint nnTarefaCustoEstimado not null
 constraint ckTarefaCustoEstimado check (custoEst > 0),
emailColaborador varchar(50) 
 constraint nnTarefaEmailColaborador not null,
 nifOrganizacao integer
 constraint nnTarefanifOrganizacao not null
 constraint ckTarefanifOrganizacao check(regexp_like(nifOrganizacao, '^\d{9}$')),
idCatTar integer
 constraint nnTarefanidCatTar not null,
 constraint pkTarefa primary key (referencia,nifOrganizacao)
);



create table ReconhecimentoCT(
dataReconhecimento date 
 constraint nnReconhecimentoCTdata not null,
nifFreelancer integer
 constraint nnReconhecimentoCTnifFreeLancer not null
 constraint ckReconhecimentoCTnifFreeLancer  check(regexp_like(nifFreelancer, '^\d{9}$')),
valorCompetenciaTecnicaReconhecido varchar(10)
 constraint nnRecCTvalorCompetenciaTecnicaReconhecido not null,
codCompetenciaTecnica varchar(12) 
 constraint nnRecCTcodCompetenciaTecnica not null,
 constraint pkReconhecimentoCT primary key (nifFreelancer,valorCompetenciaTecnicaReconhecido,codCompetenciaTecnica)
);



create table CategoriaTarefa(
id integer generated as identity
 constraint pkCategoriaTarefa primary key,
descricao varchar(50)
 constraint nnCategoriaTarefaDescricao not null,
idAreaAtividade  varchar(10)
 constraint nnCategoriaTarefaIdAreaAtividade not null
);

create table CompetenciaTecnica(
codUnico varchar(30)
 constraint pkCompetenciaTecnica primary key,
descBreve varchar(50)
 constraint nnCompetenciaTecnicaDescBreve not null,
descDetalhada  varchar(200)
 constraint nnCompetenciaTecnicaDescDetalhada not null,
idAreaAtividade  varchar(30)
 constraint nnCompetenciaTecnicaIdAreaAtividade not null
);


create table AreaAtividade(
id varchar(30)
 constraint pkAreaAtividade primary key,
descBreve varchar(50)
 constraint nnAreaAtividadeDescBreve not null,
descDetalhada  varchar(200)
 constraint nnAreaAtividadeDescDetalhada not null
);

create table GrauProficiencia(
valor varchar(20)
 constraint nnGrauProficienciaValor not null,
designacao varchar(100)
 constraint nnGrauProficienciaDesignacao not null,
codCompetenciaTecnica varchar(20)
 constraint nnGrauProficienciacodCompetenciaTecnica not null,
 CONSTRAINT pkGrauProficiencia PRIMARY KEY (valor,codCompetenciaTecnica)
);

create table CaraterCT(
idCategoriaTarefa integer
 constraint nnCaraterCTIdCategoriaTarefa not null,
codCompetenciaTecnica varchar(30)
 constraint nnCaraterCTcodCompetenciaTecnica not null,
obrigatorio integer
 constraint ckCaraterCTObrigatorio check ((obrigatorio = 0) OR (obrigatorio = 1))
 constraint nnCaraterCTObrigatorio not null,
grauMinimoProficiencia varchar(20)
 constraint nnCaraterCTGrauMinimoProficiencia not null,
 CONSTRAINT pkCaraterCT PRIMARY KEY (idCategoriaTarefa,codCompetenciaTecnica)
);

create table Atribuicao(
id integer generated as identity,
nifOrganizacao number 
    constraint nnAtribNifOrg not null
    constraint ckAtribNifOrg check (regexp_like(nifOrganizacao, '^\d{9}$')),
nifFreelancer number 
    constraint nnAtribNifFreelancer not null
    constraint ckAtribNifFreelancer check (regexp_like(nifFreelancer, '^\d{9}$')),
dataInicioRealizacao date
    constraint nnAtribDataInicio not null,
dataFimRealizacao date
    constraint nnAtribDataFim not null,
valor float
    constraint nnAtribValor not null,
idAnuncio varchar(10)
    constraint nnAtribIdAnuncio not null,
constraint pkAtribuicao primary key (id,idAnuncio)
);



---------- Foreign Keys -------------

alter table Organizacao
    add constraint fk_OrgUtilizadorEmail foreign key (email) references Utilizador(email)
    add constraint fk_OrgEndPostalID foreign key (idEnderecoPostal) references EnderecoPostal(id)
    add constraint fk_OrgGestorEmailUtilizador foreign key (emailGestor) references Utilizador(email);

alter table Classificacao
    add constraint fk_ClassificacaoProcessoSeriacaoId foreign key (idProcessoSeriacao) references ProcessoSeriacao(idAnuncio)
    add constraint fk_ClassificacaoCandidaturaId foreign key (idCandidatura) references Candidatura(id);

alter table ListaColaboradorProcessoSeriacao
	add constraint fk_ColaboradorProcessoSeriacaoProcessoSeriacao foreign key (idProcessoSeriacao) references ProcessoSeriacao(idAnuncio)
	add constraint fk_ColaboradorProcessoSeriacaoColaborador foreign key (emailColaborador) references Colaborador(emailColaborador);

alter table Anuncio
	add constraint fk_AnuncioTipoRegimentoId foreign key (idTipoRegimento) references TipoRegimento(id)
	add constraint fk_AnuncioTarefaRefNif foreign key (id,nifOrganizacao) references Tarefa(referencia,nifOrganizacao);

alter table Colaborador
	add constraint fk_ColaboradorUtilizadorEmail foreign key (emailColaborador) references Utilizador(email)
	add constraint fk_ColaboradorOrganizacaoNifOrg foreign key (nifOrganizacao) references Organizacao(nif);

alter table HabilitacaoAcademica
	add constraint fk_HabilitacaoAcademicaFreelancerNif foreign key (nifFreelancer) references Freelancer(nif);

alter table Candidatura
    add constraint fk_CandFreelancerNif foreign key (nifFreelancer) references Freelancer(nif)
    add constraint fk_CandAnuncioId foreign key (idAnuncio) references Anuncio(id)

alter table ProcessoSeriacao
	add constraint fk_ProcSeriaColaborador foreign key (emailColaborador) references Colaborador(emailColaborador)
	add constraint fk_ProcSeriaIdAnuncio foreign key (idAnuncio) references Anuncio(id);

alter table Tarefa
    add constraint fk_TarefaColaborador foreign key (emailColaborador) references Colaborador(emailColaborador)
    add constraint fk_TarefaOrganizacao foreign key (nifOrganizacao) references Organizacao(nif)
    add constraint fk_TarefaCategoriaTarefa foreign key (idCatTar) references CategoriaTarefa(id);

alter table ReconhecimentoCT
    add constraint fk_ReconhecimentoCTFreelancer foreign key (nifFreelancer) references Freelancer(nif)
    add constraint fk_ReconhecimentoCTGrauCompTecnica foreign key (valorCompetenciaTecnicaReconhecido,codCompetenciaTecnica) references GrauProficiencia(valor,codCompetenciaTecnica);

alter table CategoriaTarefa
	add constraint fk_CategoriaTarefaAreaAtividade foreign key (idAreaAtividade) references AreaAtividade(id);

alter table CompetenciaTecnica
	add constraint fk_CompetenciaTecnicaAreaAtividade foreign key (idAreaAtividade) references AreaAtividade(id);
	
alter table CaraterCT
	add constraint fk_CaraterCTCatTarefa foreign key (idCategoriaTarefa) references CategoriaTarefa(id)
	add constraint fk_CaraterCTGrauProf foreign key (grauMinimoProficiencia,codCompetenciaTecnica) references GrauProficiencia(valor,codCompetenciaTecnica);

alter table GrauProficiencia
    add constraint fk_GrauProfCompTecnica foreign key (codCompetenciaTecnica) references CompetenciaTecnica(codUnico);

alter table Freelancer
	add constraint fk_FreelancerUtilizadorEmail foreign key (email) references Utilizador(email)
	add constraint fk_FreelancerEndPostalId foreign key (idEnderecoPostal) references  EnderecoPostal(id);

alter table Atribuicao
    add constraint fk_AtribNifOrg foreign key (nifOrganizacao) references Organizacao(nif)
    add constraint fk_AtribNifFreelancer foreign key (nifFreelancer) references Freelancer(nif)
    add constraint fk_AtribAnuncio foreign key (idAnuncio) references Anuncio(id);



drop table utilizador cascade constraint;
drop table Organizacao cascade constraint;
drop table Freelancer cascade constraint;
drop table ListaColaboradorProcessoSeriacao cascade constraint;
drop table Anuncio cascade constraint;
drop table Colaborador cascade constraint;
drop table EnderecoPostal cascade constraint;
drop table HabilitacaoAcademica cascade constraint;
drop table TipoRegimento cascade constraint;
drop table Tarefa cascade constraint;
drop table ReconhecimentoCT cascade constraint;
drop table CategoriaTarefa cascade constraint;
drop table CompetenciaTecnica cascade constraint;
drop table AreaAtividade cascade constraint;
drop table GrauProficiencia cascade constraint;
drop table CaraterCT cascade constraint;

drop table Classificacao cascade constraint;
drop table ProcessoSeriacao cascade constraint;
drop table Candidatura cascade constraint;