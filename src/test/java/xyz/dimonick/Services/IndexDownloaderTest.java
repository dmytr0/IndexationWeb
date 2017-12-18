package xyz.dimonick.Services;


import org.joda.time.YearMonth;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class IndexDownloaderTest {

    @Test
    public void checkDownload() {

        Map<YearMonth, BigDecimal> map = IndexDownloader.getIdexes();
        assertNotNull(map);
    }
}
