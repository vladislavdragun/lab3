package ua.cn.stu.savingscalculator.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import ua.cn.stu.savingscalculator.Currency;
import ua.cn.stu.savingscalculator.FirstScreenActivity;
import ua.cn.stu.savingscalculator.SecondScreenActivity;

public class CalculationsCallable implements Callable<String> {

    private final List<Currency> currencyList = Arrays.asList(
            new Currency("USD", 23.6862,28.2605),
            new Currency("EUR", 26.4220,34.6375));

    public static List<String> stringList = new ArrayList<>();
    public List<Currency> getCurrencylist()
    {
        return  currencyList;
    }

    public int getSy(int profitPerMonth)  //Гіпотетичний повний річний дохід у гривні (SY) без обміну валюти:
    {
        return profitPerMonth*12;
    }

    public double getSc() //Кількість гривень (Sc), витрачених на обмін валюти:
    {
        return getSy((int)FirstScreenActivity.getProfitPerMonth()) *FirstScreenActivity.getMonthPercentage();
    }

    public double getCi(int i, double Cstart, double Cend) // Інтерпольований курс
    {
        return  Cstart+ (i*((Cend-Cstart)/12));
    }

    public double getW (int currency) // Кількість валюти (W), придбаної за рік:
    {

        Currency currency1 = getCurrencylist().get(currency);
        double sum=0;
        for(int i=1;i<=12;i++)
        {
            sum+= (FirstScreenActivity.getProfitPerMonth()*FirstScreenActivity.getMonthPercentage())/getCi(i,currency1.getcStart(),currency1.getcEnd());
        }
        return  sum;

    }

    public double getSh(int currency)  // Гривнева вартість придбаної валюти (SH) на кінець року:
    {
        Currency currency1 = getCurrencylist().get(currency);
        return  getW(currency)*currency1.getcEnd();
    }

    public double getSl() // ) Гривневий залишок (SL):
    {
        return  getSy((int)FirstScreenActivity.getProfitPerMonth())-getSc();
    }

    public double getH(int currency)
    {

        return getSh(currency) +getSl();
    }

    public double getR(int currency)
    {
        return  getH(currency)-getSy((int)FirstScreenActivity.getProfitPerMonth());
    }

    @Override
    public String call() throws Exception {
       CalculationsCallable calculations = new CalculationsCallable();
      stringList.add(String.valueOf(calculations.getSy((int)FirstScreenActivity.getProfitPerMonth())));
       stringList.add(String.valueOf(calculations.getSc()));
        stringList.add(String.valueOf(Math.round(calculations.getW(SecondScreenActivity.getSelectedCurrency()))));
        stringList.add(String.valueOf((Math.round(calculations.getSh(SecondScreenActivity.getSelectedCurrency())))));
        stringList.add(String.valueOf(calculations.getSl()));
        stringList.add(String.valueOf(Math.round(calculations.getH(SecondScreenActivity.getSelectedCurrency()))));
       stringList.add(String.valueOf(Math.round(calculations.getR(SecondScreenActivity.getSelectedCurrency()))));
        StringBuilder zxc= new StringBuilder();
        for (String item: stringList)
        {
               zxc.append(item).append(",");
        }
        //Thread.sleep(1000);
        return zxc.toString();

    }

}
