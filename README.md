




:TODO:
-googleId in user validate
-user foreign key to each personDb/rel/anything else user specific

LISTENer to authentication success
SAVE/UPDATE USER


COMMANDS:
RUN application mvn spring-boot:run -pl application
BUILD aLL mvn clean install

Integrate with Angular Project:
cd to angula project mvn

LT_SECRET configured in intellij setting

SQL SCRIPTS:
$ cat file.sql | heroku pg:psql --app app_name
$ echo "select * from table;" | heroku pg:psql --app app_name
$ heroku pg:psql --app app_name < file.sql

VIEW LOGS:
heroku logs -a app_nme
