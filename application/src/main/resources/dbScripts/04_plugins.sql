--oracle
--ALTER TABLE PLUGIN MODIFY  IDE VARCHAR(20);

--psql
ALTER TABLE PLUGIN ALTER COLUMN  IDE TYPE VARCHAR(20);

INSERT INTO PLUGIN (id, ide, name, link) VALUES (1, 'INTELLIJ','BashSupport','https://plugins.jetbrains.com/plugin/4230-bashsupport');
INSERT INTO PLUGIN (id, ide, name, link) VALUES (2, 'INTELLIJ','Scala','https://plugins.jetbrains.com/plugin/1347-scala');
INSERT INTO PLUGIN (id, ide, name, link) VALUES (3, 'ECLIPSE','SonarLint','https://www.sonarlint.org/eclipse/');
INSERT INTO PLUGIN (id, ide, name, link) VALUES (4, 'ECLIPSE','ECD','https://marketplace.eclipse.org/content/enhanced-class-decompiler');
INSERT INTO PLUGIN (id, ide, name, link) VALUES (5, 'INTELLIJ','Lombok','https://plugins.jetbrains.com/plugin/6317-lombok-plugin');

