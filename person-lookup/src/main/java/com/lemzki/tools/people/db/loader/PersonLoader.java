package com.lemzki.tools.people.db.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jasongoodwin.monads.Try;
import com.lemzki.tools.people.db.enums.Gender;
import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.Person;
import com.lemzki.tools.people.db.model.RelationType;
import com.lemzki.tools.people.db.model.Relationship;
import com.lemzki.tools.people.db.repository.FamilyRepository;
import com.lemzki.tools.people.db.repository.PersonRepository;
import com.lemzki.tools.people.db.repository.RelationTypeRepository;
import com.lemzki.tools.people.db.repository.RelationshipRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PersonLoader {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FamilyRepository familyRepository;

    @Autowired
    RelationTypeRepository relationTypeRepository;

    @Autowired
    RelationshipRepository relationshipRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final static LocalDate defaultDOB = LocalDate.MIN;

    private Random random = new Random();



    public void loadPersons(){
        savePeopleFromFile();
        setChildren();
    }

    private void savePeopleFromFile() {
        try {

            File file = new ClassPathResource("person.csv").getFile();

            Reader in = new FileReader(file);


            CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in).forEach(
                    record -> {
                        Person person = createPerson(record);
                        personRepository.save(person);
                    }
            );

        } catch (Exception e) {
            System.out.println(e);

            System.out.println("ERROR WITH PERSONLOADER");
        }
    }


    private String[] resolutions = {"100", "200", "300", "150", "250", "350"};
    private Person createPerson(CSVRecord record) {
        Person person = null;
        try {
            person = new Person();
            person.setName(record.get("NAME"));
            int index = random.nextInt(resolutions.length);
            person.setPhotoUrl("https://source.unsplash.com/random/" + index + "x" + index);
            person.setNickname(record.get("NICKNAME"));
            person.setGender(Gender.getEnum(record.get("GENDER")));
            person.setDateOfBirth(getDateFromString(record.get("DOB")));

            if(person.getNickname().equals("pencing")){
                person.setDateOfDeath(LocalDate.of(1990, 5, 12));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Unable to create Person createFrom " + record);
        }
        return person;
    }

    private LocalDate  getDateFromString(String date){
        return Try.ofFailable(()->LocalDate.parse(date, formatter)).orElse(LocalDate.MIN);
    }

    private void setChildren() {
        List<Person> everyone = personRepository.findAll();
        Map<String, List<Person>> map = everyone.stream().collect(Collectors.groupingBy(Person::getNickname));

        Person lem = map.get("lem").get(0);
        Person rey = map.get("rey").get(0);
        Person chichi = map.get("chichi").get(0);
        Person cel = map.get("cel").get(0);
        Person ace = map.get("ace").get(0);
        Person zita = map.get("zita").get(0);
        Person pencing = map.get("pencing").get(0);
        Person mai = map.get("mai").get(0);
        Person isli = map.get("isli").get(0);

        Family parentLem = new Family(lem);
        parentLem.childrenAre(chichi);

        Family parentRey = new Family(rey);
        parentRey.childrenAre(lem, isli);

        Family parentMai = new Family(mai);
        parentMai.childrenAre(chichi);

        Family parentCel = new Family(cel);
        parentCel.childrenAre(lem);

        Family parentZita = new Family(zita);
        parentZita.childrenAre(ace);

        Family parentPencing = new Family(pencing);
        parentPencing.childrenAre(cel, zita, lem);

        familyRepository.saveAll(Lists.newArrayList(parentLem, parentMai
        ,parentRey, parentCel, parentZita, parentPencing));


        String[] rs = {"BESTFRIEND", "WIFE", "ENEMY", "BFFS", "SOMEREL"};
        Map<String, RelationType> rmap = Arrays.stream(rs).map(r->{
            RelationType rt = new RelationType(r);
            return relationTypeRepository.save(rt);
        }).collect(Collectors.toMap(RelationType::getName, Function.identity()));





        Relationship maiLem = new Relationship();
        maiLem.setMain(lem);
        maiLem.setOther(mai);
        maiLem.setRelation(Sets.newHashSet(rmap.get("BESTFRIEND"), rmap.get("WIFE"), rmap.get(("ENEMIES"))));





        Relationship teroyAce = new Relationship();
        teroyAce.setMain(chichi);
        teroyAce.setOther(ace);
        teroyAce.setRelation(Sets.newHashSet(rmap.get("BFFS")));

        Relationship pencingLem = new Relationship();
        pencingLem.setMain(pencing);
        pencingLem.setOther(lem);
        pencingLem.setRelation(Sets.newHashSet(rmap.get("SOMEREL")));

        relationshipRepository.saveAll(Sets.newHashSet(maiLem, teroyAce, pencingLem));

        Relationship chichiLem = new Relationship();
        chichiLem.setMain(lem);
        chichiLem.setOther(chichi);
        chichiLem.setRelation(Sets.newHashSet(rmap.get("BESTFRIEND")));

        relationshipRepository.save(chichiLem);
    }

}
