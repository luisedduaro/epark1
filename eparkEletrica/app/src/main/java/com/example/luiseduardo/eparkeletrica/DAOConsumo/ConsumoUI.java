package com.example.luiseduardo.eparkeletrica.DAOConsumo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.luiseduardo.eparkeletrica.R;

public class ConsumoUI extends Activity {
    private static final int INCLUIR = 0;

    private ConsumoVO lConsumoVO;
    private EditText txtRegistro_inicial;
    private EditText txtRegistro_final;
    private EditText txtMes;
    private Button btnConfirmar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo);

            final Bundle data = (Bundle) getIntent().getExtras();
            int lint = data.getInt("tipo");
            if (lint == INCLUIR){

                lConsumoVO = new ConsumoVO();
            }else{

                lConsumoVO = (ConsumoVO)data.getSerializable("consumo");
            }

            txtRegistro_inicial = (EditText)findViewById(R.id.edtRegistroInicial);
            txtRegistro_final = (EditText)findViewById(R.id.edtRegistroFinal);
            txtMes = (EditText)findViewById(R.id.editMes);
     
            txtRegistro_inicial.setText(lConsumoVO.getRegistro_inicial());
            txtRegistro_final.setText(lConsumoVO.getRegistro_final());
            txtMes.setText(lConsumoVO.getMes());

    }
 
    public void btnConfirmar_click(View view){

        btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent();
                lConsumoVO.setRegistro_inicial(txtRegistro_inicial.getText().toString());
                lConsumoVO.setRegistro_final(txtRegistro_final.getText().toString());
                lConsumoVO.setMes(txtMes.getText().toString());
                data.putExtra("agenda", lConsumoVO);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
     
    public void btnCancelar_click(View view){

        btnCancelar =(Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

}