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
--------------------------------------------------------------------------------
  
INSERT INTO ETAPA (ID,DENOMINACION,ORDEN) VALUES ('622a09ba-8813-11e5-af63-feff819cdc9f','Clasificacion','1');
INSERT INTO ETAPA (ID,DENOMINACION,ORDEN) VALUES ('622a0c44-8813-11e5-af63-feff819cdc9f','Solicitud','2');
INSERT INTO ETAPA (ID,DENOMINACION,ORDEN) VALUES ('622a0d52-8813-11e5-af63-feff819cdc9f','Resolucion','3');
INSERT INTO ETAPA (ID,DENOMINACION,ORDEN) VALUES ('622a09cf-8813-11e5-af63-feff819cdc9f','Renovacion','4');

--------------------------------------------------------------------------------

INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de7865ce-8d46-11e5-8994-feff819cdc9f','622a09ba-8813-11e5-af63-feff819cdc9f','PROCESO','1','En proceso registro','INSTITUCION','1','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de786934-8d46-11e5-8994-feff819cdc9f','622a09ba-8813-11e5-af63-feff819cdc9f','PROCESO','2','En clasificacion','INSTITUCION','7','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de786a4c-8d46-11e5-8994-feff819cdc9f','622a09ba-8813-11e5-af63-feff819cdc9f','PROCESO','3','Solicitud TDR','SOLICITANTE','15','T','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de786b1e-8d46-11e5-8994-feff819cdc9f','622a09ba-8813-11e5-af63-feff819cdc9f','PROCESO','4','TDR en revision','INSTITUCION','7','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de786bf0-8d46-11e5-8994-feff819cdc9f','622a09ba-8813-11e5-af63-feff819cdc9f','PROCESO','5','Emision resolucion y aprobacion de TDR','INSTITUCION','0','F','T','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de786cc2-8d46-11e5-8994-feff819cdc9f','622a0c44-8813-11e5-af63-feff819cdc9f','PROCESO','1','Solicitud de certificacion','INSTITUCION','0','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de787082-8d46-11e5-8994-feff819cdc9f','622a0c44-8813-11e5-af63-feff819cdc9f','PROCESO','2','Solicitud de Opinion Tecnica','SOLICITANTE','15','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de7871a4-8d46-11e5-8994-feff819cdc9f','622a0c44-8813-11e5-af63-feff819cdc9f','PROCESO','3','Opinion Tecnica en revision','INSTITUCION','7','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de78728a-8d46-11e5-8994-feff819cdc9f','622a0d52-8813-11e5-af63-feff819cdc9f','APROBADO','1','Emision de resolucion y aprobacion de Certificacion','INSTITUCION','0','F','T','T');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de787352-8d46-11e5-8994-feff819cdc9f','622a0d52-8813-11e5-af63-feff819cdc9f','DESAPROBADO','2','Declaracion de abandono','INSTITUCION','0','F','T','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de78741a-8d46-11e5-8994-feff819cdc9f','622a0d52-8813-11e5-af63-feff819cdc9f','ABANDONO','3','Emision de resolucion y desaprobacion de Certificacion','INSTITUCION','0','F','T','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de787906-8d46-11e5-8994-feff819cdc9f','622a09cf-8813-11e5-af63-feff819cdc9f','PROCESO','1','Solicitud de renovacion','INSTITUCION','15','F','F','F');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de787a14-8d46-11e5-8994-feff819cdc9f','622a09cf-8813-11e5-af63-feff819cdc9f','APROBADO','2','Emision de resolucion y aprobacion de renovacion','INSTITUCION','0','F','T','T');
INSERT INTO PROCEDIMIENTO (ID,ETAPA_ID,ESTADO,ORDEN,DENOMINACION,RESPONSABLE,PLAZO,REQUIERE_CATEGORIA,REQUIERE_RESOLUCION,REQUIERE_FECHA_VIGENCIA) VALUES ('de787ae6-8d46-11e5-8994-feff819cdc9f','622a09cf-8813-11e5-af63-feff819cdc9f','DESAPROBADO','3','Emision de resolucion y desaprobacion de renovacion','INSTITUCION','0','F','T','F');

--------------------------------------------------------------------------------

INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce10c4e-8d48-11e5-ab56-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','de786934-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce22f66-8d48-11e5-ab56-feff819cdc9f','de786934-8d46-11e5-8994-feff819cdc9f','de786a4c-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce2340c-8d48-11e5-ab56-feff819cdc9f','de786934-8d46-11e5-8994-feff819cdc9f','de78728a-8d46-11e5-8994-feff819cdc9f','2');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce237e0-8d48-11e5-ab56-feff819cdc9f','de786a4c-8d46-11e5-8994-feff819cdc9f','de786b1e-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce23b14-8d48-11e5-ab56-feff819cdc9f','de786b1e-8d46-11e5-8994-feff819cdc9f','de786bf0-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce23e3e-8d48-11e5-ab56-feff819cdc9f','de786b1e-8d46-11e5-8994-feff819cdc9f','de786a4c-8d46-11e5-8994-feff819cdc9f','2');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce240fa-8d48-11e5-ab56-feff819cdc9f','de786bf0-8d46-11e5-8994-feff819cdc9f','de786cc2-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce2449c-8d48-11e5-ab56-feff819cdc9f','de786cc2-8d46-11e5-8994-feff819cdc9f','de787082-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce24712-8d48-11e5-ab56-feff819cdc9f','de787082-8d46-11e5-8994-feff819cdc9f','de7871a4-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce24a5a-8d48-11e5-ab56-feff819cdc9f','de7871a4-8d46-11e5-8994-feff819cdc9f','de78728a-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce24d8e-8d48-11e5-ab56-feff819cdc9f','de7871a4-8d46-11e5-8994-feff819cdc9f','de78741a-8d46-11e5-8994-feff819cdc9f','2');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce25040-8d48-11e5-ab56-feff819cdc9f','de78728a-8d46-11e5-8994-feff819cdc9f','de787906-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce25356-8d48-11e5-ab56-feff819cdc9f','de787906-8d46-11e5-8994-feff819cdc9f','de787a14-8d46-11e5-8994-feff819cdc9f','1');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce25694-8d48-11e5-ab56-feff819cdc9f','de787906-8d46-11e5-8994-feff819cdc9f','de787ae6-8d46-11e5-8994-feff819cdc9f','2');
INSERT INTO SUGERENCIA (ID,PROCEDIMIENTO_ID,PROCEDIMIENTO_SUGERENCIA_ID,PRIORIDAD) VALUES ('5ce2598c-8d48-11e5-ab56-feff819cdc9f','de787a14-8d46-11e5-8994-feff819cdc9f','de787906-8d46-11e5-8994-feff819cdc9f','1');
