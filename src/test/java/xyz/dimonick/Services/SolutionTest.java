package xyz.dimonick.Services;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SolutionTest {

    private Solution ic = Solution.getInstance();


    @Test
    public void testPer() {

        List<String> listBase = ic.getBasePerList();
        List<String> listCalc = ic.getCalcPerList();

        assertNotNull(listBase);
        assertNotNull(listCalc);

    }

    @Test
    public void solveTest() {
        String answer = ic.solve("2015-01", "2016-01", true).toString();
        assertEquals(answer, "0.371");
        String answer2 = ic.solve("2015-01", "2016-01", false).toString();
        assertEquals(answer2, "0.380");

    }


}
