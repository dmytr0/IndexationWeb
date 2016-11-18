package xyz.dimonick.Services;


import org.joda.time.YearMonth;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    private static volatile Solution instance;
    private HashMap<YearMonth, BigDecimal> indexes;
    private List<String> BasePerList;
    private List<String> CalcPerList;
    private YearMonth startIndexesPeriod = YearMonth.parse("2000-01");
    private YearMonth endIndexesPeriod;
    private static final YearMonth startCalc = YearMonth.parse("2016-01");
    private BigDecimal newLimit = new BigDecimal("1.03");
    private BigDecimal oldLimit = new BigDecimal("1.01");






    static Solution getInstance() {
        Solution localInstance = instance;
        if (localInstance == null) {
            synchronized (Solution.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Solution();
                }
            }
        }
        return localInstance;
    }

    private Solution(){
        init();
    }

//    void startIndexScheduler(){
//        long day = 86400000;
//        Timer time = new Timer();
//        DownloadScheduler ds = new DownloadScheduler();
//        time.schedule(ds, 0, day);
//    }


    private void init(){
        indexes = IndexDownloader.getIdexes();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        startIndexScheduler();
        fillBasePeriod();
        try {
            fillCalcPeriod();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //fill avalible base period

    private void fillBasePeriod()
    {
        BasePerList = new ArrayList<String>(indexes.size());
        YearMonth tmpPeriod = getStartIndexesPeriod();
        for (int i = 0; i < indexes.size(); i++) {
            BasePerList.add(tmpPeriod.toString());
            tmpPeriod = tmpPeriod.plusMonths(1);
        }
        setEndIndexesPeriod(tmpPeriod.minusMonths(1));
    }

    // fill avalible calculation period

    private void fillCalcPeriod() throws Exception {
        YearMonth tmpPeriod;
        YearMonth maxAvalible = getEndIndexesPeriod().plusMonths(2);
        if (maxAvalible.compareTo(getStartCalc())<0) {
            throw new Exception("Not all indexes are loaded!");
        }
        tmpPeriod = getStartCalc();
        ArrayList<String> list = new ArrayList<String>();
        while(tmpPeriod.compareTo(maxAvalible) <= 0) {
            list.add(tmpPeriod.toString());
            tmpPeriod = tmpPeriod.plusMonths(1);
        }
        CalcPerList = new ArrayList<String>(list.size());
        for (String aList : list) {
            CalcPerList.add(aList);
        }

    }

    /**
     * @param basePer base period such as"2007-12", not null
     * @param calcPeriod pay period such as"2007-12", not null
     * @param method true - 103% accept to all indexes, false - accept to all payroll from 01.2016
     * @return indexation coefficient, not null
     */

    BigDecimal solve (String basePer, String calcPeriod, Boolean method){
        init();
        if(basePer == null || calcPeriod == null) {
            throw new IllegalArgumentException("Parameters are incorrect");
        }
        BigDecimal coefficient = BigDecimal.ONE;
        BigDecimal limit;
        BigDecimal bound = BigDecimal.ZERO;
        YearMonth base = YearMonth.parse(basePer);
        YearMonth calc = YearMonth.parse(calcPeriod);
        ArrayList<BigDecimal> excessLimit = new ArrayList<BigDecimal>();

        if (base.compareTo(calc.minusMonths(2))>0) {
            return BigDecimal.ZERO.setScale(3, RoundingMode.HALF_UP);
        }
        for(YearMonth i = base.plusMonths(1); i.compareTo(calc.minusMonths(1))< 0; i = i.plusMonths(1) ){

            if(indexes.get(i)==null){
                return new BigDecimal("-1");
            }

            if(bound.compareTo(BigDecimal.ZERO)> 0) {
                bound = bound.multiply(indexes.get(i));
            }
            else bound = indexes.get(i);


            if(!method && i.compareTo(startCalc) < 0){
                limit = oldLimit;
            }
            else {
                limit = newLimit;
            }
            if(bound.compareTo(limit)>=0) {
                excessLimit.add(bound.setScale(3, RoundingMode.HALF_UP));
                bound = BigDecimal.ZERO;
            }
        }

        for(BigDecimal count: excessLimit) {
            coefficient = coefficient.multiply(count);
        }

        coefficient = coefficient.subtract(BigDecimal.ONE);
        if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
            coefficient = BigDecimal.ZERO ;
        }
        coefficient = coefficient.setScale(3, RoundingMode.HALF_UP);

        return coefficient;
    }



    public HashMap<YearMonth, BigDecimal> getIndexes() {
        return indexes;
    }

    void setIndexes(HashMap<YearMonth, BigDecimal> indexes) {
        this.indexes = indexes;
    }

    List<String> getBasePerList() {
        return BasePerList;
    }

    List<String> getCalcPerList() {
        return CalcPerList;
    }


    private YearMonth getEndIndexesPeriod() {
        return endIndexesPeriod;
    }

    private void setEndIndexesPeriod(YearMonth endIndexesPeriod) {
        this.endIndexesPeriod = endIndexesPeriod;
    }

    private YearMonth getStartIndexesPeriod() {
        return startIndexesPeriod;
    }

    private static YearMonth getStartCalc() {
        return startCalc;
    }
}
