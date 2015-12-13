package com.example.luiseduardo.eparkeletrica;

import android.app.Activity;
import android.content.Intent;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.luiseduardo.eparkeletrica.DAOCadastro.MainCadastro;
import com.example.luiseduardo.eparkeletrica.DAOConsumo.MainConsumo;

public class MainActivity extends Activity {

    private Button btnCadastrar;
    private Button btnConsumo;
    private Button btnCalendario;
    private Button btnAlarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainprincipal);

    }

            public void btnDados(View view){
                btnCadastrar = (Button)findViewById(R.id.buttonDados);
                btnCadastrar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        startActivity(new Intent(getApplicationContext(), MainCadastro.class));

                    }
                });
            }

    public void btnConsumo(View view){

        btnConsumo = (Button)findViewById(R.id.buttonConsumo);
        btnConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainConsumo.class));

            }
        });
    }

    public void btnCalendario (View view){

        btnCalendario = (Button)findViewById(R.id.buttonCalendario);
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                startActivity(intent);
            }
        });
    }

    public void btnAlarme (View view){

        btnAlarme = (Button)findViewById(R.id.buttonCalendario);
        btnAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openClockIntent);

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Epark - Controle de Energia");
                startActivity(i);

            }
        });
    }
}
