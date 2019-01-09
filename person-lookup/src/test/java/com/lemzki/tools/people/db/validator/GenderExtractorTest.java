package com.lemzki.tools.people.db.validator;

import com.lemzki.tools.people.db.enums.GenderE;
import com.lemzki.tools.people.db.exception.GenderInconsistentException;
import com.lemzki.tools.people.db.exception.GenderUndeterminedException;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class GenderExtractorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void extractGenderMustReturnFemale() {
        List<String> testGenders = Lists.newArrayList("f", "F", "FEMALE", "female");
        GenderE gender = extractFromGenderList(testGenders);
        assertSame(gender, GenderE.FEMALE);
    }

    @Test
    public void extractGenderMustReturnMale() {
        List<String> testGenders = Lists.newArrayList("m", "M", "MALE", "male");
        GenderE gender = extractFromGenderList(testGenders);
        assertSame(gender, GenderE.MALE);
    }


    @Test(expected = GenderInconsistentException.class)
    public void extractGenderMustReturnException() {
        List<String> testGenders = Lists.newArrayList("m", "F", "MALE", "male");
        GenderE gender = extractFromGenderList(testGenders);
        assertSame(gender, GenderE.MALE);
    }

    @Test(expected = GenderUndeterminedException.class)
    public void extractGenderMustReturnException2() {
        List<String> testGenders = Lists.newArrayList("m", "T", "MALE", "male");
        GenderE gender = extractFromGenderList(testGenders);
        assertSame(gender, GenderE.MALE);
    }


    private GenderE extractFromGenderList(List<String> testGenders){
        Set<GenderE> set = testGenders.stream()
                .map(GenderE::getEnum)
                .collect(toSet());
        if(set.size() == 1){
            return set.iterator().next();
        } else {
            throw new GenderInconsistentException("Can't determine gender from Set: " + set);
        }

    }
}