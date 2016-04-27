package xyz.dimonick.Services;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class WebController {

    Solution ic;
    private String bp;
    private String cp;
    private String resNew;
    private String resOld;

    public WebController() {

        ic =  Solution.getInstance();
    }

    public String getResNew(){
        BigDecimal coef = ic.solve(bp, cp, true);
        BigDecimal indexation = ic.getMinzp().multiply(coef).setScale(2, RoundingMode.HALF_UP);
        resNew = "Коэффициент: " + coef + "\t Индексация: " + indexation + " грн.";
        return resNew;
    }

    public String getResOld(){
        BigDecimal coef = ic.solve(bp, cp, false);
        BigDecimal indexation = ic.getMinzp().multiply(coef).setScale(2, RoundingMode.HALF_UP);
        resOld = "Коэффициент: " + coef + "\t Индексация: " + indexation + " грн.";
        return resOld;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public List<String> getBase(){
        List<String> arr = ic.getBasePerList();
        Collections.reverse(arr);
        return arr;
    }

    public List<String> getCalc(){
        List<String> arr = ic.getCalcPerList();
        Collections.reverse(arr);
        return arr;
    }


}
