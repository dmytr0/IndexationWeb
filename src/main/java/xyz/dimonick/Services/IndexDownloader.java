package xyz.dimonick.Services;


import org.joda.time.YearMonth;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;


class IndexDownloader {

    static HashMap<YearMonth, BigDecimal> getIdexes() {
        Document doc;
        HashMap<YearMonth, BigDecimal> map = new HashMap<YearMonth, BigDecimal>();
        BigDecimal HUNDRED = new BigDecimal("100");
        System.setProperty("javax.net.ssl.trustStore", "minfin.jks");
        try {
            String indexUrl = "https://index.minfin.com.ua/economy/index/inflation/";
            doc = Jsoup.connect(indexUrl)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Element tbody = doc.getElementsByTag("tbody").get(0);
            Elements rows = tbody.getElementsByTag("tr");
            for (int i = 1; i < rows.size(); i++) {
                Elements column = rows.get(i).getElementsByTag("td");
                int year = Integer.parseInt(rows.get(i).getElementsByTag("th").get(0).text());
                for (int j = 0; j < 12; j++) {
                    String currentIndex = column.get(j).text().replace(',', '.');
                    if (currentIndex.length() > 2) {
                        BigDecimal index = new BigDecimal(currentIndex).divide(HUNDRED);
                        map.put(new YearMonth(year, (j + 1)), index);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
