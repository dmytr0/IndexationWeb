package xyz.dimonick.Services;


import org.joda.time.YearMonth;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class MinSalary {

    private static YearMonth startPeriod = new YearMonth(2016, 1);

    static HashMap<YearMonth, BigDecimal> getMinWagesList(){
        Document doc;
        HashMap<YearMonth, BigDecimal>  map = new HashMap<YearMonth, BigDecimal>() ;
        BigDecimal HUNDRED = new BigDecimal("100");
        System.setProperty("javax.net.ssl.trustStore", "minfin.jks");
        try {
            String indexUrl = "https://index.minfin.com.ua/labour/wagemin/";
            doc = Jsoup.connect(indexUrl)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Element tbody = doc.getElementsByTag("tbody").get(0);
            Elements rows = tbody.getElementsByTag("tr");
            for (int i = 1; i < rows.size(); i++) {
                Elements column = rows.get(i).getElementsByTag("td");
                column.get(0).getElementsByTag("small").remove();
                String period = column.get(0).text().split("  ")[0];
                int year = Integer.parseInt(period.split("\\.")[2]);
                int month = Integer.parseInt(period.split("\\.")[1]);
                YearMonth ym = new YearMonth(year, month);
                if (ym.compareTo(startPeriod) >= 0){
                    String currentWage = column.get(4).text().replace(',', '.');
                    BigDecimal wage = new BigDecimal(currentWage);
                    map.put(ym, wage);
                 }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    static BigDecimal getMinWages(YearMonth ym){

        HashMap<YearMonth, BigDecimal> map = getMinWagesList();
        YearMonth current = new YearMonth(startPeriod);
        BigDecimal result = null;

        while(current.compareTo(ym) <= 0){
            if(map.get(current) !=null){
                result = map.get(current);
            }
            current = current.plusMonths(1);
        }

        return result;
    }
}
