package ua.cn.stu.savingscalculator;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public  class CalculationsService   {

        public final String DOWNLOAD = "download";
    private  static List<Currency> currencyList = new ArrayList<>();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *

     */

    public static  List<Currency> getCurrencylist()
    {
        return  currencyList;
    }

    public static int getSy(int profitPerMonth)  //Гіпотетичний повний річний дохід у гривні (SY) без обміну валюти:
    {
        return profitPerMonth*12;
    }

   public static double getSc() //Кількість гривень (Sc), витрачених на обмін валюти:
   {
       return getSy((int) FirstScreenActivity.getProfitPerMonth()) * FirstScreenActivity.getMonthPercentage();
   }

   public static double getCi(int i, double Cstart, double Cend) // Інтерпольований курс
   {
        return  Cstart+ (i*((Cend-Cstart)/12));
   }

    public static double getW (int currency) // Кількість валюти (W), придбаної за рік:
    {

            Currency currency1 = getCurrencylist().get(currency);
        double sum=0;
        for(int i=1;i<=12;i++)
        {
            sum+= (FirstScreenActivity.getProfitPerMonth()* FirstScreenActivity.getMonthPercentage())/getCi(i,currency1.getcStart(),currency1.getcEnd());
        }
        return  sum;

    }

    public static  double getSh(int currency)  // Гривнева вартість придбаної валюти (SH) на кінець року:
    {
        Currency currency1 = getCurrencylist().get(currency);
        return  getW(currency)*currency1.getcEnd();
    }

    public static double getSl() // ) Гривневий залишок (SL):
    {
        return  getSy((int) FirstScreenActivity.getProfitPerMonth())-getSc();
    }

    public static double getH(int currency)
    {

        return getSh(currency) +getSl();
    }





    public static double getR(int currency)
    {
        return  getH(currency)-getSy((int) FirstScreenActivity.getProfitPerMonth());
    }


}

