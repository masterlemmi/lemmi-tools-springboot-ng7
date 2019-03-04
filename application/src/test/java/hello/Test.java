package hello;

import org.junit.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Test {


    @org.junit.Test
    public void test() {
        //mustContainOnlyParenMinusPlusNumber

        String reg = "[()0-9\\-+]+";

        assertFalse("ABCDEFGHIJKL12345680".matches(reg));
        assertFalse("12345680*76%".matches(reg));
        assertFalse("AB".matches(reg));
        assertFalse("123$".matches(reg));
        assertFalse("1A".matches(reg));
//       assertFalse("(-+)))))".matches(reg));

        assertTrue("12-34".matches(reg));
        assertTrue("123+4".matches(reg));
        assertTrue("123(4)".matches(reg));
        assertTrue("12300099904".matches(reg));
        assertTrue("1".matches(reg));
        assertTrue("(-+)1".matches(reg));


    }


    @org.junit.Test
    public void test2() {
        //mustContainOnlyParenMinusPlusNumber

        String reg = "[()0-9\\-+]*[0-9]+[()0-9\\-+]*";

        assertFalse("ABCDEFGHIJKL12345680".matches(reg));
        assertFalse("12345680*76%".matches(reg));
        assertFalse("AB".matches(reg));
        assertFalse("123$".matches(reg));
        assertFalse("1A".matches(reg));
        assertFalse("(-+)))))".matches(reg));

        assertTrue("12-34".matches(reg));
        assertTrue("123+4".matches(reg));
        assertTrue("123(4)".matches(reg));
        assertTrue("--12300099904++".matches(reg));
        assertTrue("-(0(+0(-12300099904++".matches(reg));
        assertTrue("1".matches(reg));
        assertTrue("(-+)1".matches(reg));


    }
}
