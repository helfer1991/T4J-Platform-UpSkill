--------------------------------------------------------
--  DDL for Procedure ADDANUNCIO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDANUNCIO" (
    p_idAnuncio anuncio.id%type,
    p_dtInicioPublicacao anuncio.dtInicioPublicacao%type,
    p_dtFimPublicacao anuncio.dtFimPublicacao%type,
    p_dtInicioCandidatura anuncio.dtInicioCandidatura%type,
    p_dtFimCandidatura anuncio.dtFimCandidatura%type,
    p_dtInicioSeriacao anuncio.dtInicioSeriacao%type,
   	p_dtFimSeriacao anuncio.dtFimSeriacao%type,
   	p_idTipoRegimento anuncio.idTipoRegimento%type,
    p_nifOrganizacao anuncio.nifOrganizacao%type) 
    is
    begin 
insert into Anuncio (id, dtInicioPublicacao, dtFimPublicacao, dtInicioCandidatura, dtFimCandidatura, dtInicioSeriacao, dtFimSeriacao, idTipoRegimento, nifOrganizacao)
 values (p_idAnuncio, p_dtInicioPublicacao, p_dtFimPublicacao, p_dtInicioCandidatura, p_dtFimCandidatura, p_dtInicioSeriacao, p_dtFimSeriacao, p_idTipoRegimento, p_nifOrganizacao);
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDAREAATIVIDADE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDAREAATIVIDADE" ( 
    p_codunico AreaAtividade.Id%type, 
    p_descricao AreaAtividade.descBreve%type, 
    p_descdetailed AreaAtividade.descDetalhada%type) 
    is
    begin 
insert into AreaAtividade(Id, descBreve, descDetalhada) values (p_codunico, p_descricao, p_descdetailed); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCANDIDATURA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCANDIDATURA" ( 
    p_dataCandidatura candidatura.dataCandidatura%type,
    p_valorPretendido candidatura.valorPretendido%type,
    p_nrDias candidatura.nrDias%type,
    p_txtApresentacao candidatura.txtApresentacao%type,
    p_txtMotivacao candidatura.txtMotivacao%type,
   	p_nifFreelancer candidatura.nifFreelancer%type,
   	p_idAnuncio candidatura.idanuncio%type) 
    is
    begin 
insert into Candidatura (dataCandidatura, valorPretendido, nrDias, txtApresentacao, txtMotivacao, nifFreelancer, idAnuncio, active)
 values (p_dataCandidatura,p_valorPretendido,p_nrDias,p_txtApresentacao,p_txtMotivacao,p_nifFreelancer,p_idAnuncio, 'true');
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCARATERCT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCARATERCT" ( 
    p_idCatTar caraterct.idcategoriatarefa%type,
    p_CodCompTec caraterct.codcompetenciatecnica%type,
    p_Obrigatorio caraterct.obrigatorio%type,
    p_GrauMinimoProf caraterct.grauminimoproficiencia%type) 
    is
    begin 
insert into CARATERCT(IDCATEGORIATAREFA, CODCOMPETENCIATECNICA, OBRIGATORIO, GRAUMINIMOPROFICIENCIA) values (p_idCatTar,p_CodCompTec,p_Obrigatorio,p_GrauMinimoProf); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCATEGORIATAREFA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCATEGORIATAREFA" ( 
    p_descricao CategoriaTarefa.descricao%type,
    p_AreaAtividade categoriatarefa.idareaatividade%type) 
    is
    begin 
insert into CategoriaTarefa(DESCRICAO, IDAREAATIVIDADE) values (p_descricao, p_AreaAtividade); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCLASSIFICACAO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCLASSIFICACAO" ( 
    p_idProcessoSeriacao classificacao.idprocessoseriacao%type,
    p_dataHora classificacao.datahora%type,
    p_lugarClassificacao classificacao.lugarclassificacao%type,
    p_idCandidatura classificacao.idcandidatura%type,
    p_niffreelancer classificacao.niffreelancer%type) 
    is
    begin 
Insert into Classificacao(idProcessoSeriacao,dataHora,lugarClassificacao,idCandidatura,niffreelancer)
values (p_idProcessoSeriacao, p_dataHora,p_lugarClassificacao,p_idCandidatura,p_niffreelancer); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCOLABORADOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCOLABORADOR" ( 
    p_email colaborador.emailcolaborador%type, 
    p_niforg colaborador.niforganizacao%type) 
    is
    begin 
INSERT INTO Colaborador (emailcolaborador, niforganizacao) VALUES (p_email, p_niforg); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDCOMPETENCIATECNICA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDCOMPETENCIATECNICA" ( 
    p_codUnico competenciatecnica.codunico%type, 
    p_descricao competenciatecnica.descbreve%type, 
    p_descdetailed competenciatecnica.descdetalhada%type,
    p_idAreaAtividade CompetenciaTecnica.idareaatividade%type) 
    is
    begin 
INSERT INTO COMPETENCIATECNICA (CODUNICO, DESCBREVE, DESCDETALHADA,IDAREAATIVIDADE) VALUES(p_codUnico, p_descricao, p_descdetailed,p_idAreaAtividade); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDENDERECOPOSTAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDENDERECOPOSTAL" ( 
    p_morada enderecopostal.morada%type, 
    p_codPostal enderecopostal.codigopostal%type,
    p_localidade enderecopostal.localidade%type) 
    is
    begin 
INSERT INTO ENDERECOPOSTAL (MORADA, CODIGOPOSTAL, LOCALIDADE) VALUES(p_morada, p_codPostal,p_localidade); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDFREELANCER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDFREELANCER" ( 
    p_nif freelancer.nif%type, 
    p_email freelancer.email%type, 
    p_idenderecoPostal freelancer.idenderecopostal%type) 
    is
    begin 
insert into Freelancer(nif, email, idenderecoPostal) values (p_nif, p_email, p_idenderecoPostal); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDGRAUPROFICIENCIA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDGRAUPROFICIENCIA" ( 
    p_valor grauproficiencia.valor%type, 
    p_designacao grauproficiencia.designacao%type,
    p_codCompetenciaTecnica grauproficiencia.codcompetenciatecnica%type) 
    is
    begin 
INSERT INTO GRAUPROFICIENCIA (VALOR, DESIGNACAO, CODCOMPETENCIATECNICA) VALUES(p_valor, p_designacao,p_codCompetenciaTecnica); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDHABILITACAOACADEMICA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDHABILITACAOACADEMICA" ( 
    p_grau habilitacaoacademica.grau%type, 
    p_designacaoCurso habilitacaoacademica.designacaocurso%type, 
    p_nomeInstituicao habilitacaoacademica.nomeinstituicao%type,
    p_medicaCurso habilitacaoacademica.mediacurso%type,
    p_nifFreelancer habilitacaoacademica.niffreelancer%type) 
    is
    begin 
insert into HabilitacaoAcademica(Grau,Designacaocurso,NomeInstituicao,MediaCurso,NifFreelancer)
values (p_grau, p_designacaoCurso, p_nomeInstituicao, p_medicaCurso, p_nifFreelancer); 
end;


/
--------------------------------------------------------
--  DDL for Procedure ADDLISTACOLABORADORPROCESSOSERIACAO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDLISTACOLABORADORPROCESSOSERIACAO" ( 
    p_idProcessoSeriacao listacolaboradorprocessoseriacao.idprocessoseriacao%type,
    p_emailColaborador listacolaboradorprocessoseriacao.emailcolaborador%type) 
    is
    begin 
Insert into ListaColaboradorProcessoSeriacao(idProcessoSeriacao,emailColaborador)
values (p_idProcessoSeriacao, p_emailColaborador); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDORGANIZACAO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDORGANIZACAO" ( 
    p_nif organizacao.nif%type, 
    p_email organizacao.email%type,
    p_idenderecopostal organizacao.idenderecopostal%type,
    p_emailgestor organizacao.emailgestor%type,
    p_website organizacao.website%type) 
    is
    begin 
INSERT INTO ORGANIZACAO (NIF, EMAIL, IDENDERECOPOSTAL, EMAILGESTOR, WEBSITE) VALUES(p_nif, p_email,p_idenderecopostal,p_emailgestor,p_website); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDPROCESSOSERIACAO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDPROCESSOSERIACAO" ( 
    p_dataRealizacao processoseriacao.datarealizacao%type,
    p_emailColaborador processoseriacao.emailcolaborador%type,
    p_idAnuncio processoseriacao.idanuncio%type) 
    is
    begin 
Insert into PROCESSOSERIACAO (DATAREALIZACAO, EMAILCOLABORADOR, IDANUNCIO) VALUES (p_dataRealizacao, p_emailColaborador,p_idAnuncio); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDRECONHECIMENTOCT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDRECONHECIMENTOCT" ( 
    p_dataReconhecimento reconhecimentoct.datareconhecimento%type, 
    p_nifFreelancer reconhecimentoct.niffreelancer%type, 
    p_valorCompetenciaTecnicaReconhecido reconhecimentoct.valorcompetenciatecnicareconhecido%type,
    p_codCompetenciaTecnica reconhecimentoct.codcompetenciatecnica%type) 
    is
    begin 
insert into ReconhecimentoCT(dataReconhecimento, NIFFreelancer , valorcompetenciatecnicareconhecido,codcompetenciatecnica)
values (p_dataReconhecimento, p_nifFreelancer, p_valorCompetenciaTecnicaReconhecido,p_codCompetenciaTecnica); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDTAREFA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDTAREFA" (
    p_referencia tarefa.referencia%type,
    p_designacao tarefa.designacao%type,
    p_descInformal tarefa.descinformal%type,
    p_desctecnica tarefa.desctecnica%type,
    p_duracaoest tarefa.duracaoest%type,
    p_custoest tarefa.custoest%type,
    p_emailcolaborador tarefa.emailcolaborador%type,
    p_niforganizacao tarefa.niforganizacao%type,
    p_idcattar tarefa.idcattar%type
    )
    is
    BEGIN
    INSERT INTO TAREFA (REFERENCIA, DESIGNACAO, DESCINFORMAL, DESCTECNICA, DURACAOEST, CUSTOEST, EMAILCOLABORADOR, NIFORGANIZACAO, IDCATTAR) 
    VALUES(p_referencia,p_designacao,p_descInformal,p_desctecnica,p_duracaoest,p_custoest,p_emailcolaborador,p_niforganizacao,p_idcattar);
END;

/
--------------------------------------------------------
--  DDL for Procedure ADDTIPOREGIMENTO
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDTIPOREGIMENTO" ( 
    p_designacao tiporegimento.designacao%type, 
    p_descricao tiporegimento.descricao%type) 
    is
    begin 
INSERT INTO TIPOREGIMENTO (DESIGNACAO, DESCRICAO) VALUES (p_designacao, p_descricao); 
end;

/
--------------------------------------------------------
--  DDL for Procedure ADDUTILIZADOR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."ADDUTILIZADOR" ( 
    p_email utilizador.email%type, 
    p_nome utilizador.nome%type,
    p_telefone utilizador.telefone%type,
    p_role utilizador.role%type,
    p_password utilizador.password%type) 
    is
    begin 
INSERT INTO UTILIZADOR (EMAIL, NOME, TELEFONE, ROLE, PASSWORD) VALUES(p_email, p_nome,p_telefone,p_role,p_password); 
end;

/
--------------------------------------------------------
--  DDL for Procedure CHANGECANDIDATURA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."CHANGECANDIDATURA" (
    p_id candidatura.id%type,
    p_valorPretendido candidatura.valorPretendido%type,
    p_nrDias candidatura.nrDias%type,
    p_txtApresentacao candidatura.txtApresentacao%type,
    p_txtMotivacao candidatura.txtMotivacao%type)
    is
    begin 
UPDATE Candidatura
SET valorpretendido = p_valorPretendido, nrDias = p_nrDias, txtApresentacao = p_txtApresentacao, txtMotivacao = p_txtMotivacao
WHERE id = p_id;
end;

/
--------------------------------------------------------
--  DDL for Procedure REMOVECANDIDATURA
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "UPSKILL_BD_TURMA1_10"."REMOVECANDIDATURA" (
    p_id candidatura.id%type)
    is
    begin 
UPDATE Candidatura SET active = 'false' WHERE id = p_id;
end;

/