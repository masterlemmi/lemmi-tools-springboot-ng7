package com.lemzki.tools.people.db.validator;

import com.google.api.services.people.v1.model.Gender;
import com.google.api.services.people.v1.model.Relation;
import com.jasongoodwin.monads.Try;

import java.util.List;

public class GenderExtractor {

    private List<Gender> genderList;
    private List<Relation> relations;


    GenderExtractor(Builder builder) {
        this.genderList = builder.genderList;
        this.relations = builder.relations;
    }

    public com.lemzki.tools.people.db.enums.Gender extractGender() {
        Try  (getGender(this.genderList)).;
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
