--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the 'License');
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an 'AS IS' BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements

INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b937fa4a-475f-11e5-a151-feff819cdc9f','PEN','Boveda de Nuevos Soles','http://localhost:8080/rrhh/rest/sucursales/bf1754a8-46b8-11e5-a151-feff819cdc9f/agencias/af8bedfe-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b937fe32-475f-11e5-a151-feff819cdc9f','USR','Boveda de Dolares Americanos','http://localhost:8080/rrhh/rest/sucursales/bf1754a8-46b8-11e5-a151-feff819cdc9f/agencias/af8bedfe-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b938003a-475f-11e5-a151-feff819cdc9f','EUR','Boveda de Euros','http://localhost:8080/rrhh/rest/sucursales/bf1754a8-46b8-11e5-a151-feff819cdc9f/agencias/af8bedfe-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b9380206-475f-11e5-a151-feff819cdc9f','PEN','Boveda de Nuevos Soles','http://localhost:8080/rrhh/rest/sucursales/bf1757d2-46b8-11e5-a151-feff819cdc9f/agencias/af8bf790-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b93803c8-475f-11e5-a151-feff819cdc9f','USR','Boveda de Dolares Americanos','http://localhost:8080/rrhh/rest/sucursales/bf1757d2-46b8-11e5-a151-feff819cdc9f/agencias/af8bf790-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA (ID,MONEDA,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b938081e-475f-11e5-a151-feff819cdc9f','EUR','Boveda de Euros','http://localhost:8080/rrhh/rest/sucursales/bf1757d2-46b8-11e5-a151-feff819cdc9f/agencias/af8bf790-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');

INSERT INTO CAJA (ID,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b2804940-4760-11e5-a151-feff819cdc9f','Caja 01','http://localhost:8080/rrhh/rest/sucursales/bf1754a8-46b8-11e5-a151-feff819cdc9f/agencias/af8bedfe-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO CAJA (ID,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b2804cec-4760-11e5-a151-feff819cdc9f','Caja 02','http://localhost:8080/rrhh/rest/sucursales/bf1754a8-46b8-11e5-a151-feff819cdc9f/agencias/af8bedfe-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO CAJA (ID,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b2804efe-4760-11e5-a151-feff819cdc9f','Caja 01','http://localhost:8080/rrhh/rest/sucursales/bf1757d2-46b8-11e5-a151-feff819cdc9f/agencias/af8bf790-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO CAJA (ID,DENOMINACION,AGENCIA,ESTADO,optlk) VALUES ('b28053ae-4760-11e5-a151-feff819cdc9f','Caja 02','http://localhost:8080/rrhh/rest/sucursales/bf1757d2-46b8-11e5-a151-feff819cdc9f/agencias/af8bf790-46b9-11e5-a151-feff819cdc9f','T','1/01/2015');

INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752f2d8-4761-11e5-a151-feff819cdc9f','b937fa4a-475f-11e5-a151-feff819cdc9f','b2804940-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752f652-4761-11e5-a151-feff819cdc9f','b937fe32-475f-11e5-a151-feff819cdc9f','b2804940-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752f7f6-4761-11e5-a151-feff819cdc9f','b938003a-475f-11e5-a151-feff819cdc9f','b2804940-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752f954-4761-11e5-a151-feff819cdc9f','b937fa4a-475f-11e5-a151-feff819cdc9f','b2804cec-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752fad0-4761-11e5-a151-feff819cdc9f','b937fe32-475f-11e5-a151-feff819cdc9f','b2804cec-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752fc38-4761-11e5-a151-feff819cdc9f','b938003a-475f-11e5-a151-feff819cdc9f','b2804cec-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5752ffee-4761-11e5-a151-feff819cdc9f','b9380206-475f-11e5-a151-feff819cdc9f','b2804efe-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('5753016a-4761-11e5-a151-feff819cdc9f','b93803c8-475f-11e5-a151-feff819cdc9f','b2804efe-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('575302be-4761-11e5-a151-feff819cdc9f','b938081e-475f-11e5-a151-feff819cdc9f','b2804efe-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('575303fe-4761-11e5-a151-feff819cdc9f','b9380206-475f-11e5-a151-feff819cdc9f','b28053ae-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('57530638-4761-11e5-a151-feff819cdc9f','b93803c8-475f-11e5-a151-feff819cdc9f','b28053ae-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
INSERT INTO BOVEDA_CAJA (ID,BOVEDA_ID,CAJA_ID,ESTADO,optlk) VALUES ('575307be-4761-11e5-a151-feff819cdc9f','b938081e-475f-11e5-a151-feff819cdc9f','b28053ae-4760-11e5-a151-feff819cdc9f','T','1/01/2015');
