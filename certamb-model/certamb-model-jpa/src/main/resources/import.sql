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

INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,optlk) VALUES ('3b3f49d0-efd2-4954-a22d-7f0b5eacc0ea','Direccion regional de turismo','T','1/01/2015');
INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,optlk) VALUES ('1c6daaf8-899a-4708-a6cf-87191ed14b8a','Direccion regional de transporte','T','1/01/2015');
INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,optlk) VALUES ('2a13b5ce-f4fa-4531-8a4f-e603aabd4018','Direccion regional de salud','T','1/01/2015');

INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,optlk) VALUES ('74ce73e3-d62f-447d-9e35-7884dec459e6','Construccion de pisigranja',100000,'PERFIL','3b3f49d0-efd2-4954-a22d-7f0b5eacc0ea','1/01/2015');
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,optlk) VALUES ('68dbb415-4224-42f9-8447-8f08716157ea','Construccion de vivero de flores de alta tecnica',100000,'PERFIL','2a13b5ce-f4fa-4531-8a4f-e603aabd4018','1/01/2015');
