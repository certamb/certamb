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

--------------------------------------------------------------------------------

INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,OPTLK) VALUES ('63331ce0-8d42-11e5-8994-feff819cdc9f','RECURSOS NATURALES','T',1);
INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,OPTLK) VALUES ('63331ce6-8d42-11e5-8994-feff819cdc9f','Direccion regional de turismo','T',1);
INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,OPTLK) VALUES ('63332394-8d42-11e5-8994-feff819cdc9f','Direccion regional de transporte','T',1);
INSERT INTO DIRECCION_REGIONAL (ID,DENOMINACION,ESTADO,OPTLK) VALUES ('633324fc-8d42-11e5-8994-feff819cdc9f','Direccion regional de infraestructura','T',1);

--------------------------------------------------------------------------------

INSERT INTO TRABAJADOR (ID,TIPO_DOCUMENTO,NUMERO_DOCUMENTO,USUARIO,DIRECCION_REGIONAL_ID,OPTLK) VALUES ('2bfc729e-8d43-11e5-8994-feff819cdc9f','DNI','46779354','recursosNaturales','63331ce0-8d42-11e5-8994-feff819cdc9f',1);
INSERT INTO TRABAJADOR (ID,TIPO_DOCUMENTO,NUMERO_DOCUMENTO,USUARIO,DIRECCION_REGIONAL_ID,OPTLK) VALUES ('2bfc751e-8d43-11e5-8994-feff819cdc9f','DNI','11111111','direccionRegional','63331ce6-8d42-11e5-8994-feff819cdc9f',1);

--------------------------------------------------------------------------------

INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ec220-8d44-11e5-8994-feff819cdc9f','Construccion de pisigranja','1000000','PERFIL','63331ce6-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ec716-8d44-11e5-8994-feff819cdc9f','Construccion de vivero de flores de alta tecnica','1000000','PERFIL','63332394-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ecd56-8d44-11e5-8994-feff819cdc9f','Construccion de chanchas de fulbol','1000000','PERFIL','633324fc-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ed148-8d44-11e5-8994-feff819cdc9f','Construccion de carretera ayacucho huanta','1000000','PERFIL','63331ce6-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ed2ec-8d44-11e5-8994-feff819cdc9f','Construccion de muralla de contencion muyurina','1000000','PERFIL','63332394-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ed5bc-8d44-11e5-8994-feff819cdc9f','Construccion de colegio inicial ayacucho','1000000','PERFIL','633324fc-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ed74c-8d44-11e5-8994-feff819cdc9f','Construccion de centro de telecomunicaciones ayacucho','1000000','PERFIL','63331ce6-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ed9cc-8d44-11e5-8994-feff819cdc9f','Construccion de reservorio huanta','1000000','PERFIL','63332394-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89edb66-8d44-11e5-8994-feff819cdc9f','Construccion de hospital regional','1000000','FACTIBILIDAD','633324fc-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89edf30-8d44-11e5-8994-feff819cdc9f','Construccion de infraestructura de desagues','1000000','FACTIBILIDAD','63331ce6-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ee0e8-8d44-11e5-8994-feff819cdc9f','Asfaltado de carretea','1000000','FACTIBILIDAD','63332394-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ee3cc-8d44-11e5-8994-feff819cdc9f','Ampliacion de las calles huamanga','1000000','FACTIBILIDAD','633324fc-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ee534-8d44-11e5-8994-feff819cdc9f','Instalacion de postes de alumbrado publico','1000000','FACTIBILIDAD','63331ce6-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89ee872-8d44-11e5-8994-feff819cdc9f','Mantenimiento del centro historico de huamanga','1000000','FACTIBILIDAD','63332394-8d42-11e5-8994-feff819cdc9f','PROCESO',1);
INSERT INTO PROYECTO (ID,DENOMINACION,MONTO,TIPO,DIRECCION_REGIONAL_ID,ESTADO,OPTLK) VALUES ('e89eea02-8d44-11e5-8994-feff819cdc9f','Contruccion de centro de seguridad ayacucho','1000000','FACTIBILIDAD','633324fc-8d42-11e5-8994-feff819cdc9f','PROCESO',1);

--------------------------------------------------------------------------------

INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdabfa-8dec-11e5-8994-feff819cdc9f','e89ec220-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdae52-8dec-11e5-8994-feff819cdc9f','e89ec716-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb0be-8dec-11e5-8994-feff819cdc9f','e89ecd56-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb21c-8dec-11e5-8994-feff819cdc9f','e89ed148-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb564-8dec-11e5-8994-feff819cdc9f','e89ed2ec-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb65e-8dec-11e5-8994-feff819cdc9f','e89ed5bc-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb726-8dec-11e5-8994-feff819cdc9f','e89ed74c-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb7ee-8dec-11e5-8994-feff819cdc9f','e89ed9cc-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb8ac-8dec-11e5-8994-feff819cdc9f','e89edb66-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdb96a-8dec-11e5-8994-feff819cdc9f','e89edf30-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdba28-8dec-11e5-8994-feff819cdc9f','e89ee0e8-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdbca8-8dec-11e5-8994-feff819cdc9f','e89ee3cc-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdbd84-8dec-11e5-8994-feff819cdc9f','e89ee534-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdbe42-8dec-11e5-8994-feff819cdc9f','e89ee872-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
INSERT INTO HISTORIAL_PROYECTO (ID,PROYECTO_ID,PROCEDIMIENTO_ID,FECHA,CATEGORIA,RESOLUCION,OBSERVACION,FECHA_VIGENCIA_DESDE,FECHA_VIGENCIA_HASTA,ESTADO,OPTLK) VALUES ('bcbdbf00-8dec-11e5-8994-feff819cdc9f','e89eea02-8d44-11e5-8994-feff819cdc9f','de7865ce-8d46-11e5-8994-feff819cdc9f','01/01/2015',null,null,null,null,null,'T',1);
