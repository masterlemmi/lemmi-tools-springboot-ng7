package com.lemzki.tools.people.db.service;

import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import org.springframework.transaction.annotation.Transactional;


//TODO: Test functionality of transaction rollback. Is it automatically reversing saves for what kinds of exceptions
@Transactional
public interface ComplexPersonService {

    //this will allow transactional rollbacks for any exceptions
   ComplexPersonDTO add (ComplexPersonDTO complexPerson);
}
