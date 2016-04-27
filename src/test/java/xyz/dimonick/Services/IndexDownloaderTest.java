package xyz.dimonick.Services;


import org.junit.Test;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class IndexDownloaderTest {

    @Test
    public void checkDownload(){

        Map<YearMonth, BigDecimal> map = IndexDownloader.getIdexes();
        assertNotNull(map);
    }
}
