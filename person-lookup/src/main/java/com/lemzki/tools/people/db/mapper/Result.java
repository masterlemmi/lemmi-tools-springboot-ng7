package com.lemzki.tools.people.db.mapper;

import com.lemzki.tools.people.db.model.PersonDb;

public abstract class Result {

    boolean success;
    String error;
    PersonDb mapped;

    public boolean passed(){return success;}

    public PersonDb get() {return mapped;}

    public String error() {return this.error;}

    public static class Fail extends Result {
        public Fail(PersonDb mapped, String error) {
            super.success = false;
            super.mapped = mapped;
            super.error = error;
        }
    }

    public static class Success extends Result {
        public Success(PersonDb mapped) {
            super.success = true;
            super.mapped = mapped;
        }
    }

    @Override
    public String toString() {
        String status = success? "PASSED" : "FAILED";
        return "STATUS: " + status + " -> Error: " + this.error + " Data: " + this.mapped;
    }
}
