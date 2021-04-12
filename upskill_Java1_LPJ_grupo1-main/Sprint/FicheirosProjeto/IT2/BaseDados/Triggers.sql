--------------------------------------------------------
--  DDL for Trigger TRGVALIDADATARECONHECIMENTOCT
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "UPSKILL_BD_TURMA1_10"."TRGVALIDADATARECONHECIMENTOCT" after insert or update on ReconhecimentoCT for each row
begin
 if trunc(:new.dataReconhecimento) >= trunc(sysdate) then
 raise_application_error(-20000, 'Erro na data de realização do Reconhecimento de Competencia Tecnica');
 end if;
end;

/
ALTER TRIGGER "UPSKILL_BD_TURMA1_10"."TRGVALIDADATARECONHECIMENTOCT" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRGVALIDADATASANUNCIO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "UPSKILL_BD_TURMA1_10"."TRGVALIDADATASANUNCIO" after insert or update on Anuncio for each row
begin
	if trunc(:new.dtFimPublicacao) <= trunc(:new.dtInicioPublicacao) then
 raise_application_error(-20001, 'Erro! Data de Fim de Publicação não pode ser antes do Inicio da Publicação');
 end if;
 if trunc(:new.dtInicioCandidatura) <= trunc(:new.dtFimPublicacao) then
 raise_application_error(-20001, 'Erro! Data de Inicio de Candidatura não pode ser depois do Fim da Publicação');
 end if;
   if trunc(:new.dtFimCandidatura) <= trunc(:new.dtInicioCandidatura) then
 raise_application_error(-20001, 'Erro! Data de Fim de Candidatura não pode ser antes do Inicio da Candidaturas');
 end if;
 if trunc(:new.dtInicioSeriacao) <= trunc(:new.dtFimCandidatura) then
 raise_application_error(-20001, 'Erro! Data de Inicio de Seriação não pode ser antes da data de Fim de Candidaturas');
 end if;
 if trunc(:new.dtFimSeriacao) <= trunc(:new.dtInicioSeriacao) then
 raise_application_error(-20001, 'Erro! Data de Fim de Seriação não pode ser antes da data de Inicio de Seriação');
 end if;
end;
