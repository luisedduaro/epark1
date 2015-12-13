package com.example.luiseduardo.eparkeletrica;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main_calcular extends AppCompatActivity {

    private EditText ednumero1;
    private EditText ednumero2;
    private Button btsomar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ednumero1 = (EditText)findViewById(R.id.ednumero1);
        ednumero2 = (EditText)findViewById(R.id.ednumero2);

        Button btsomar = (Button)findViewById(R.id.btsomar);

        btsomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double num1 = Double.parseDouble(ednumero1.getText().toString());
                double num2 = Double.parseDouble(ednumero2.getText().toString());
                double resultado = (num1-num2)/0.8;
                double media = resultado/30;
                AlertDialog.Builder mensagem = new
                        AlertDialog.Builder(Main_calcular.this);
                mensagem.setTitle("Aviso");
                mensagem.setMessage("Soma:" + resultado);
                mensagem.setNeutralButton("OK", null);
                mensagem.show();
            }
        });


}
}
