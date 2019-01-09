/*
package com.lemzki.tools.familydb;

import com.lemzki.tools.people.db.FamilyDbApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.com.lemzki.tools.security.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= FamilyDbApplication.class)
public class PersonRepositoryTests {
    @Autowired
    PersonRepository personRepository;

    @Test
    public void testGetParents(){
        PersonDb person = new PersonDb();
        person.setId(1l);
       List<PersonDb> parents = personRepository.findParentsOf(person);
parents.forEach(p-> System.out.println(p.getName()));
    }

    @Test
    public void testGetIdIfNullID(){
        //Optional<PersonDb> person = personRepository.findById(null);
       // System.out.println("GETTING EPRSON: " + person.get());
    }

}
*/
