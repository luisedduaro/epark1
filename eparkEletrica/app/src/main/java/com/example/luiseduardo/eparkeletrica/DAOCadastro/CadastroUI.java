package com.example.luiseduardo.eparkeletrica.DAOCadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luiseduardo.eparkeletrica.DAOConsumo.ConsumoVO;
import com.example.luiseduardo.eparkeletrica.R;

public class CadastroUI extends Activity {

    private static final int INCLUIR = 0;
    protected CadastroVO lContatoVO;
    protected EditText txtNome;
    protected EditText txtEndereco;
    protected EditText txtTelefone;
    private Button btnConfirmar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contato);

            final Bundle data = (Bundle) getIntent().getExtras();
            int lint = data.getInt("tipo");
            if (lint == INCLUIR) {
                lContatoVO = new CadastroVO();
            }else{
                lContatoVO = (CadastroVO)data.getSerializable("cadastro");
            }
             
            txtNome = (EditText)findViewById(R.id.edtNome);
            txtEndereco = (EditText)findViewById(R.id.edEndereco);
            txtTelefone = (EditText)findViewById(R.id.editTelefone);

            txtNome.setText(lContatoVO.getNome());
            txtEndereco.setText(lContatoVO.getEndereco());
            txtTelefone.setText(lContatoVO.getTelefone());

    }
 
    public void btnConfirmar_click(View view){

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                lContatoVO.setNome(txtNome.getText().toString());
                lContatoVO.setEndereco(txtEndereco.getText().toString());
                lContatoVO.setTelefone(txtTelefone.getText().toString());
                data.putExtra("agenda", lContatoVO);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
     
    public void btnCancelar_click(View view){

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
        }
    }