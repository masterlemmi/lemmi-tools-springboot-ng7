/*
package com.lemzki.tools.familydb;

import com.lemzki.tools.people.db.FamilyDbApplication;
import com.lemzki.tools.people.db.dto.ComplexPersonDTO;
import com.lemzki.tools.people.db.mapper.ResourceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FamilyDbApplication.class)
public class PersonMapperTest {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ResourceMapper<ComplexPersonDTO, PersonDb> mapper;

    @Test
    public void mapPersonModel(){
        PersonDb person = personRepository.findByNickname("lem");
        ComplexPersonDTO dto = mapper.mapModel(person);
        System.out.println(dto);
    }

}
*/
