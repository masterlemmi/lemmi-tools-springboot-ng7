--#ORACLE
 create table debt_loan (
       id number(19,0),
       name varchar(255),
       primary key (id)
    )


--#ORACLE
 create table due (
         id number(19,0),
         amount number (15,2),
         DUE_DATE date,
         debt_id number(19,0)  REFERENCES DEBT_LOAN(id),
         primary key (id)
     )

