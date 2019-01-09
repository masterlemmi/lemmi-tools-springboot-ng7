package com.lemzki.tools.people.db.validator;

import com.google.api.services.people.v1.model.Gender;
import com.google.api.services.people.v1.model.Relation;
import com.jasongoodwin.monads.Try;
import com.lemzki.tools.people.db.enums.GenderE;
import com.lemzki.tools.people.db.exception.GenderInconsistentException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GenderExtractor {

    private List<Gender> genderList;
    private List<Relation> relations;


    GenderExtractor(Builder builder) {
        this.genderList = builder.genderList;
        this.relations = builder.relations;
    }

    public GenderE extractGender() {
    return Try.ofFailable(this::extractFromGenderList).orElse(extractFromRelations());
    }

    private GenderE extractFromRelations(){
        Set<GenderE> set = relations.stream()
                .filter(gender->gender.getType().equalsIgnoreCase("gender"))
                .map(Relation::getPerson)//person will be the placeholder for M,F,FEMALE, etc
                .map(GenderE::getEnum)
                .collect(toSet());
        return extract(set);
    }


    private GenderE extractFromGenderList(){
        Set<GenderE> set = this.genderList.stream()
                .map(Gender::getValue)
                .map(GenderE::getEnum)
                .collect(toSet());
        return extract(set);
    }

    private GenderE extract( Set<GenderE> set){
        if(set.size() == 1){
            return set.iterator().next();
        } else {
            throw new GenderInconsistentException("Can't determine gender from Set: " + set);
        }
    }





    public static class Builder {
        private List<Gender> genderList;
        private List<Relation> relations;


        public Builder from(List<Gender> genders) {
            this.genderList = genderList;
            return this;
        }

        public Builder orFrom(List<Relation> relations) {
            this.relations = relations;
            return this;
        }

        public GenderExtractor build() {
            return new GenderExtractor(this);
        }
    }

}
