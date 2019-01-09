package com.lemzki.tools.people.db.loader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jasongoodwin.monads.Try;
import com.lemzki.tools.people.db.enums.GenderE;
import com.lemzki.tools.people.db.model.Family;
import com.lemzki.tools.people.db.model.PersonDb;
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

    private static final LocalDate DEFAULT_DATE = LocalDate.of(1986, 3, 31);
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
                        PersonDb personDb = createPerson(record);
                        personRepository.save(personDb);
                    }
            );

        } catch (Exception e) {
            System.out.println(e);

            System.out.println("ERROR WITH PERSONLOADER");
        }
    }


    private String[] resolutions = {"100", "200", "300", "150", "250", "350"};
    private PersonDb createPerson(CSVRecord record) {
        PersonDb personDb = null;
        try {
            personDb = new PersonDb();
            personDb.setAddedBy(LemID.TEMP);
            personDb.setName(record.get("NAME"));
            int index = random.nextInt(resolutions.length);
            personDb.setPhotoUrl("https://source.unsplash.com/random/" + index + "x" + index);
            personDb.setNickname(record.get("NICKNAME"));
            personDb.setGender(GenderE.getEnum(record.get("GENDER")));
            personDb.setDateOfBirth(getDateFromString(record.get("DOB")));

            if(personDb.getNickname().equals("pencing")){
                personDb.setDateOfDeath(LocalDate.of(1990, 5, 12));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage() + "Unable to create PersonDb createFrom " + record);
        }
        return personDb;
    }

    private LocalDate  getDateFromString(String date){
        return Try.ofFailable(()->LocalDate.parse(date, formatter)).orElse(DEFAULT_DATE);
    }

    private void setChildren() {
        List<PersonDb> everyone = personRepository.findAll();
        Map<String, List<PersonDb>> map = everyone.stream().collect(Collectors.groupingBy(PersonDb::getNickname));

        PersonDb lem = map.get("lem").get(0);
        PersonDb rey = map.get("rey").get(0);
        PersonDb chichi = map.get("chichi").get(0);
        PersonDb cel = map.get("cel").get(0);
        PersonDb ace = map.get("ace").get(0);
        PersonDb zita = map.get("zita").get(0);
        PersonDb pencing = map.get("pencing").get(0);
        PersonDb mai = map.get("mai").get(0);
        PersonDb isli = map.get("isli").get(0);

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

        List<Family> families = Lists.newArrayList(parentLem, parentMai
                ,parentRey, parentCel, parentZita, parentPencing);
        families.forEach(family -> family.setAddedBy(LemID.TEMP));
        familyRepository.saveAll(families);


        String[] rs = {"BESTFRIEND", "WIFE", "ENEMY", "BFFS", "SOMEREL"};
        Map<String, RelationType> rmap = Arrays.stream(rs).map(r->{
            RelationType rt = new RelationType(r);
            return relationTypeRepository.save(rt);
        }).collect(Collectors.toMap(RelationType::getName, Function.identity()));





        Relationship maiLem = new Relationship();
        maiLem.setMain(lem);
        maiLem.setOther(mai);
        maiLem.setRelation(Sets.newHashSet(rmap.get("BESTFRIEND"), rmap.get("WIFE"), rmap.get(("ENEMIES"))));
        maiLem.setAddedBy(LemID.TEMP);




        Relationship teroyAce = new Relationship();
        teroyAce.setMain(chichi);
        teroyAce.setOther(ace);
        teroyAce.setRelation(Sets.newHashSet(rmap.get("BFFS")));
        teroyAce.setAddedBy(LemID.TEMP);

        Relationship pencingLem = new Relationship();
        pencingLem.setMain(pencing);
        pencingLem.setOther(lem);
        pencingLem.setRelation(Sets.newHashSet(rmap.get("SOMEREL")));
        pencingLem.setAddedBy(LemID.TEMP);

        relationshipRepository.saveAll(Sets.newHashSet(maiLem, teroyAce, pencingLem));

        Relationship chichiLem = new Relationship();
        chichiLem.setMain(lem);
        chichiLem.setOther(chichi);
        chichiLem.setRelation(Sets.newHashSet(rmap.get("BESTFRIEND")));
        chichiLem.setAddedBy(LemID.TEMP);

        relationshipRepository.save(chichiLem);
    }

}
