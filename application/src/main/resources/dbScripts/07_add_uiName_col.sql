ALTER TABLE DEBT_LOAN
  ADD UI_NAME varchar(25);

  ALTER TABLE DEBT_LOAN
    ADD INTEREST NUMBER(3,2);


UPDATE DEBT_LOAN SET UI_NAME = 'RCBC Visa' where name = 'RCBC_VISA';
UPDATE DEBT_LOAN SET UI_NAME = 'Car Loan' where name = 'CAR_LOAN';
UPDATE DEBT_LOAN SET UI_NAME = 'RCBC Bankard' where name = 'RCBC_BANKARD';
UPDATE DEBT_LOAN SET UI_NAME = 'BPI' where name = 'BPI';
UPDATE DEBT_LOAN SET UI_NAME = 'BDO' where name = 'BDO';

UPDATE DEBT_LOAN SET INTEREST = 3.5;
