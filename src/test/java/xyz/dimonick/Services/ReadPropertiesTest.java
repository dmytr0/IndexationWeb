package xyz.dimonick.Services;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReadPropertiesTest {

    @Test
    public void readPropertiesTest(){

        String answer = ReadProperties.getProperty("minimal_salary");
        assertNotNull(answer);

    }
}
