package xyz.dimonick.Services;


import org.joda.time.YearMonth;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class MinSalaryTest {
    @Test
    public void checkDownload(){

        Map<YearMonth, BigDecimal> map = MinSalary.getMinWagesList();
        assertNotNull(map);
    }
    @Test
    public void checkResult(){
        BigDecimal res = MinSalary.getMinWages(new YearMonth(2016, 5));
        System.out.println(res);
        assertTrue(res.compareTo(new BigDecimal("1450"))==0);
        res = MinSalary.getMinWages(new YearMonth(2016, 4));
        assertTrue(res.compareTo(new BigDecimal("1378"))==0);
        res = MinSalary.getMinWages(new YearMonth(2014, 4));
        assertNull(res);
    }
}
