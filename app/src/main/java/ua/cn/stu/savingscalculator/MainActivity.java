package ua.cn.stu.savingscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        Button firstScreenButton = this.findViewById(R.id.firstScreenButton);
        Button exitButton = this.findViewById(R.id.exitButton);





        exitButton.setOnClickListener(v->
                {

                    System.exit (0);
                }
        );

        firstScreenButton.setOnClickListener(v->
                {

                    Intent intent1 = new Intent(this, FirstScreenActivity.class);
                    startActivity(intent1);

                }
        );
    }


}
