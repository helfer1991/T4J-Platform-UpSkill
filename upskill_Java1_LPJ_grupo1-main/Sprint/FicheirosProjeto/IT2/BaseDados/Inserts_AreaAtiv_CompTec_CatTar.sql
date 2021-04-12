Select * from CompetenciaTecnica
select * from grauProficiencia

Insert into AreaAtividade (id,descBreve,descDetalhada) values ('Aa-001','Informatica','Informatica, programação, web dev etc...');
Insert into AreaAtividade (id,descBreve,descDetalhada) values ('Aa-002','Marketing','Placards, Social Media, Tv,Anuncios etc...');
Insert into AreaAtividade (id,descBreve,descDetalhada) values ('Aa-003','Fotografia','Fotografia etc...');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-001','Java','Programador','Aa-001');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 1','Junior','CompTec-001');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 2','Senior','CompTec-001');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-004','Windows','Tecnico','Aa-001');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 1','Junior','CompTec-004');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 2','Senior','CompTec-004');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 3','Ancient','CompTec-004');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-002','Anuncios','Produção Anuncios','Aa-002');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 1','Sem Experiencia','CompTec-002');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 2','1-3 Meses Experiencia','CompTec-002');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 3','6-12 Meses Experiencia','CompTec-002');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel 4','12+ Meses Experiencia','CompTec-002');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-005','Panfletos','Produção Panfletos','Aa-002');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel D','Newbie','CompTec-005');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel E','Semi-Experienced','CompTec-005');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel F','Experienced','CompTec-005');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-003','Fotos Alta Res','Camara 4k','Aa-003');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel A','200- Lifetime Fotos','CompTec-003');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel B','1000- Lifetime Fotos','CompTec-003');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Nivel C','1000+ Lifetime Fotos','CompTec-003');

Insert into CompetenciaTecnica (codunico,descBreve,descDetalhada,idAreaAtividade) values ('CompTec-006','Video Alta Res','Camara 4k','Aa-003');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Novo','200- horas filmagem','CompTec-006');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Medio','1000- horas filmagem','CompTec-006');
insert into grauProficiencia (valor,designacao,codcompetenciatecnica)values('Bom','1000+ horas filmagem','CompTec-006');

Insert into categoriatarefa (descricao,idAreaAtividade) values ('Java Dev','Aa-001');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(1,'CompTec-001',1,'Nivel 2');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(1,'CompTec-004',0,'Nivel 1');

Insert into categoriatarefa (descricao,idAreaAtividade) values ('Testing Java','Aa-001');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(2,'CompTec-001',1,'Nivel 1');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(2,'CompTec-004',1,'Nivel 2');


Insert into categoriatarefa (descricao,idAreaAtividade) values ('Nestle Anuncios','Aa-002');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(3,'CompTec-002',1,'Nivel 3');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(3,'CompTec-005',0,'Nivel E');

Insert into categoriatarefa (descricao,idAreaAtividade) values ('Panfletos Dali','Aa-002');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(4,'CompTec-002',0,'Nivel 2');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(4,'CompTec-005',1,'Nivel F');

Insert into categoriatarefa (descricao,idAreaAtividade) values ('Fotografar Ali','Aa-003');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(5,'CompTec-003',1,'Nivel C');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(5,'CompTec-006',0,'Medio');

Insert into categoriatarefa (descricao,idAreaAtividade) values ('Filmar Ali','Aa-003');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(6,'CompTec-003',0,'Nivel B');
insert into caraterCT (idCategoriaTarefa,codCompetenciaTecnica,obrigatorio,grauminimoproficiencia)values(6,'CompTec-006',1,'Bom');


FreeLancerTestClose
26d5CUeNiM

testingestorXPTO
HKoWlTO0eP

testingcolabXPTO
LrMGYKxDFM

FreeLancerTestCloses
dB7mo6UaTA