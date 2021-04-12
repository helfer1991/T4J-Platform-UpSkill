--------------------------------------------------------
--  DDL for Function GETALLANUNCIOS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLANUNCIOS" return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
 open v_ret for
 select * from ANUNCIO;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETALLANUNCIOSDISPONIVEIS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLANUNCIOSDISPONIVEIS" return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
 open v_ret for
 select * from ANUNCIO where dtiniciocandidatura <= (trunc(sysdate)) AND dtfimcandidatura >= (trunc(sysdate));
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETALLCANDIDATURAS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLCANDIDATURAS" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Candidatura;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETALLCANDIDATURASBYANUNCIO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLCANDIDATURASBYANUNCIO" (p_idAnuncio varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Candidatura join freelancer on candidatura.niffreelancer = freelancer.nif where idAnuncio = p_idAnuncio and active = 'true';
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETALLCANDIDATURASBYFREELANCEREMAIL
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLCANDIDATURASBYFREELANCEREMAIL" (p_email varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Candidatura 
 join freelancer on freelancer.nif = candidatura.niffreelancer
 where freelancer.email = p_email and candidatura.active = 'true';
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETALLCANDIDATURASDISPBYFREELANCEREMAIL
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLCANDIDATURASDISPBYFREELANCEREMAIL" (p_email varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Candidatura join freelancer on freelancer.nif = candidatura.niffreelancer join anuncio on anuncio.id = candidatura.idanuncio where freelancer.email = p_email and anuncio.dtiniciocandidatura <= (trunc(sysdate)) 
 and anuncio.dtfimcandidatura >= (trunc(sysdate));
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETALLORGANIZACOES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETALLORGANIZACOES" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    select * from utilizador join organizacao on utilizador.email = organizacao.email join enderecopostal on organizacao.idenderecopostal = enderecopostal.id;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETANUNCIOBYREF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETANUNCIOBYREF" (p_referencia varchar)  return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
 open v_ret for
 select * from ANUNCIO WHERE id = p_referencia;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETANUNCIOSBYORG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETANUNCIOSBYORG" (orgNif NUMBER) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * from anuncio where anuncio.niforganizacao = orgNif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETAREAATIVIDADEBYID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETAREAATIVIDADEBYID" (idArea VARCHAR) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT ID ,DESCBREVE ,DESCDETALHADA  FROM areaAtividade where id = idArea;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETAREASATIVIDADE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETAREASATIVIDADE" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from AreaAtividade;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCANDIDATURABYID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCANDIDATURABYID" (p_id number)  return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Candidatura join freelancer on candidatura.niffreelancer = freelancer.nif where id = p_id;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCARATERCTBYCATEGORIATAREFAID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCARATERCTBYCATEGORIATAREFAID" (p_idCategoriaTarefa varchar)  return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
  open v_ret for
 select * from CARATERCT where IDCATEGORIATAREFA = p_idCategoriaTarefa;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETCATEGORIATAREFA
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCATEGORIATAREFA" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from CategoriaTarefa;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCATEGORIATAREFABYID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCATEGORIATAREFABYID" (p_idCategoriaTarefa varchar)  return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
  open v_ret for
 select * from CategoriaTarefa where ID = p_idCategoriaTarefa;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETCLASSIFICACAOBYIDPROCSERIACAO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCLASSIFICACAOBYIDPROCSERIACAO" (idProcSeriacao string) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    select * from classificacao where idprocessoseriacao = idProcSeriacao;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCLASSIFICACOESBYPROCESSOSERIACAO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCLASSIFICACOESBYPROCESSOSERIACAO" (id varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    select * from classificacao where idProcessoSeriacao = id;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOLABORADOR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOLABORADOR" (p_email varchar)  return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Colaborador 
 join Utilizador on Colaborador.emailcolaborador = utilizador.email where emailcolaborador = p_email;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOLABORADORES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOLABORADORES" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT utilizador.email,utilizador.nome,utilizador.telefone,colaborador.niforganizacao FROM Colaborador
    join utilizador on Colaborador.emailColaborador = utilizador.email;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOLABORADORESBYORG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOLABORADORESBYORG" (orgNif NUMBER) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT utilizador.email,utilizador.nome,utilizador.telefone,colaborador.niforganizacao FROM Colaborador
 join utilizador on Colaborador.emailColaborador = utilizador.email where colaborador.niforganizacao = orgNif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOLABORADORESORGANIZACAO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOLABORADORESORGANIZACAO" (nif_org organizacao.nif%type) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select *
 from Colaborador
 where niforganizacao = nif_org;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOMPETENCIASTECNICAS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOMPETENCIASTECNICAS" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from CompetenciaTecnica ;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOMPETENCIASTECNICASBYAREAATIVIDADE
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOMPETENCIASTECNICASBYAREAATIVIDADE" (p_idAreaAtividade varchar)  return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from competenciatecnica where idareaatividade = p_idAreaAtividade;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOMPETENCIATECNICA
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOMPETENCIATECNICA" (p_codUnico varchar)  return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from CompetenciaTecnica where codUnico = p_codUnico;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETCOMPETENCIATECNICABYDESC
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETCOMPETENCIATECNICABYDESC" (p_descricao varchar)  return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
 open v_ret for
 select * from CompetenciaTecnica where descBreve = p_descricao;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETFREELANCERBYEMAIL
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETFREELANCERBYEMAIL" (p_email varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from freelancer join utilizador on utilizador.email = freelancer.email join enderecopostal on freelancer.idenderecopostal = enderecopostal.id where freelancer.email = p_email;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETFREELANCERBYNIF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETFREELANCERBYNIF" (nif number) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from freelancer
 join utilizador on utilizador.email = freelancer.email
 join enderecopostal on freelancer.idenderecopostal = enderecopostal.id 
 where nif = nif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETFREELANCERS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETFREELANCERS" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from freelancer 
 join utilizador on freelancer.email = utilizador.email 
 join enderecopostal on freelancer.idenderecopostal = enderecopostal.id;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETGESTORORG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETGESTORORG" (orgNif NUMBER) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select utilizador.nome, utilizador.email, utilizador.telefone from organizacao 
 join utilizador on organizacao.emailgestor = utilizador.email 
 where organizacao.emailgestor = utilizador.email and organizacao.nif = orgNif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETGRAUSPROFICIENCIA
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETGRAUSPROFICIENCIA" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from GrauProficiencia;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETGRAUSPROFICIENCIABYCOMPTECNICA
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETGRAUSPROFICIENCIABYCOMPTECNICA" (codCompTec varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * FROM GRAUPROFICIENCIA WHERE CODCOMPETENCIATECNICA = codCompTec;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETGRAUSPROFICIENCIABYCOMPTECNICAANDVALOR
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETGRAUSPROFICIENCIABYCOMPTECNICAANDVALOR" (codCompTec varchar,valor varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * FROM GRAUPROFICIENCIA WHERE CODCOMPETENCIATECNICA = codCompTec and valor = valor;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETHABILITACOESACADEMICAS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETHABILITACOESACADEMICAS" (nif number) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from habilitacaoacademica where niffreelancer = nif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETORGANIZACOESASUSER
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETORGANIZACOESASUSER" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    select * from utilizador join organizacao on utilizador.email = organizacao.emailGestor;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETORGBYCOLABEMAIL
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETORGBYCOLABEMAIL" (p_email varchar)  return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from Organizacao
join enderecopostal on organizacao.idenderecopostal = enderecopostal.id
join utilizador on organizacao.email = utilizador.email
where nif = (select niforganizacao from colaborador where emailColaborador = p_email);
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETORGBYNIF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETORGBYNIF" (orgNif NUMBER) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from organizacao where nif = orgNif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETPARTICIPANTESPROCESSOSERIACAO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETPARTICIPANTESPROCESSOSERIACAO" (anuncioid string) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    select * from listacolaboradorprocessoseriacao where idprocessoseriacao = anuncioid;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETPROCESSOSSERIACAOBYORGNIF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETPROCESSOSSERIACAOBYORGNIF" (nif number) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
    SELECT * FROM PROCESSOSERIACAO join anuncio on processoseriacao.idanuncio = anuncio.id where anuncio.niforganizacao = nif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETRECONHECIMENTOSCT
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETRECONHECIMENTOSCT" (nif number) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select * from reconhecimentoCT where niffreelancer = nif;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETTAREFABYREF
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTAREFABYREF" (p_referencia varchar)  return sys_refcursor is
 v_ret sys_refcursor;
BEGIN
 open v_ret for
 select * from TAREFA WHERE REFERENCIA = p_referencia;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETTAREFAS
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTAREFAS" return sys_refcursor is
v_ret sys_refcursor;
BEGIN
  open v_ret for
 select * from Tarefa;
 return v_ret;
END;

/
--------------------------------------------------------
--  DDL for Function GETTAREFASORGANIZACAO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTAREFASORGANIZACAO" (nif_org organizacao.nif%type) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 select *
 from Tarefa
 where niforganizacao = nif_org;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETTIPOREGIMENTOBYDESIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTIPOREGIMENTOBYDESIG" (designacao varchar) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * FROM TIPOREGIMENTO WHERE DESIGNACAO = designacao;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETTIPOREGIMENTOBYID
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTIPOREGIMENTOBYID" (givenId number) return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * FROM TIPOREGIMENTO WHERE id = givenId;
 return v_ret;
end;

/
--------------------------------------------------------
--  DDL for Function GETTIPOSREGIMENTO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "UPSKILL_BD_TURMA1_10"."GETTIPOSREGIMENTO" return sys_refcursor is
 v_ret sys_refcursor;
begin
 open v_ret for
 SELECT * FROM tipoRegimento;
 return v_ret;
end;

/