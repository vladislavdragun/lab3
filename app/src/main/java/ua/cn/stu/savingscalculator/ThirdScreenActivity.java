package ua.cn.stu.savingscalculator;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Arrays;

import ua.cn.stu.savingscalculator.tasks.CalculationsCallable;


public class ThirdScreenActivity extends AppCompatActivity  implements RetainFragment.ViewStateListener {
    private RetainFragment retainFragment;
    private  TextView Sy;
    private TextView Sc;
    private TextView W ;
    private TextView Sh ;
    private TextView Sl ;
    private TextView H ;
    private TextView r ;
    private Button calculateButton;
    private ProgressBar progressBar;
    private TextView numberTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdscreen_layout);
        retainFragment = (RetainFragment) getSupportFragmentManager().findFragmentByTag(RetainFragment.TAG);
        if(retainFragment==null){
            retainFragment = new RetainFragment();
            getSupportFragmentManager().beginTransaction().add(retainFragment, RetainFragment.TAG).commit();
        }

        calculateButton = this.findViewById(R.id.Calculate);
        progressBar=this.findViewById(R.id.progress);
         numberTextView=this.findViewById(R.id.resultTextView);
         Sy = this.findViewById(R.id.Sy);
         Sc = this.findViewById(R.id.Sc);
       W = this.findViewById(R.id.W);
         Sh = this.findViewById(R.id.Sh);
         Sl = this.findViewById(R.id.Sl);
        H = this.findViewById(R.id.H);
         r = this.findViewById(R.id.R);



        //String [] result = retainFragment.generateAnswer("zxc").split(" ");
        CalculationsCallable calculations = new CalculationsCallable();
//       Sy.setText(String.valueOf(calculations.getSy((int) FirstScreenActivity.getProfitPerMonth())));
//        Sc.setText(String.valueOf(calculations.getSc()));
//        W.setText(String.valueOf(Math.round(calculations.getW(SecondScreenActivity.getSelectedCurrency()))));
//        Sh.setText(String.valueOf((Math.round(calculations.getSh(SecondScreenActivity.getSelectedCurrency())))));
//        Sl.setText(String.valueOf(calculations.getSl()));
//        H.setText(String.valueOf(Math.round(calculations.getH(SecondScreenActivity.getSelectedCurrency()))));
//        r.setText(String.valueOf(Math.round(calculations.getR(SecondScreenActivity.getSelectedCurrency()))));
       Button OKButton = this.findViewById(R.id.OKButton);
        OKButton.setOnClickListener(v ->
                {
                    Intent intent1 = new Intent(this, MenuActivity.class);
                    startActivity(intent1);

                }
        );

        calculateButton.setOnClickListener(v->{
            retainFragment.generateNumber(0,50);
        });
        retainFragment.setListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        retainFragment.setListener(null);
    }

    @Override
    public void onNewState(ViewState state) {

        calculateButton.setEnabled(state.isButtonEnabled);
        progressBar.setVisibility(state.showProgress ? View.VISIBLE:View.GONE);
        //numberTextView.setVisibility(state.showResult?View.VISIBLE:View.GONE);

       String[] result = state.result.split(",");
       if(result.length>3)

        {
            Sy.setText(String.valueOf(result[0]));
            Log.d("huita", Arrays.toString(result));
            Sc.setText(String.valueOf(result[1]));
            W.setText(String.valueOf(result[2]));
            Sh.setText(String.valueOf(result[3]));
            Sl.setText(String.valueOf(result[4]));
            H.setText(String.valueOf(result[5]));
            r.setText(String.valueOf(result[6]));
            CalculationsCallable.stringList.clear();
           // numberTextView.setText(state.result);
        }

    }
}

