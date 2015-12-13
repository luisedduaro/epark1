package com.example.luiseduardo.eparkeletrica.DAOConsumo;
 
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luiseduardo.eparkeletrica.Main_calcular;
import com.example.luiseduardo.eparkeletrica.R;

public class MainConsumo extends ListActivity{

    private static final int INCLUIR = 0;
    private static final int ALTERAR = 1;
    private Button btnCalcular;
    private Button btnConsumo;

    private ConsumoDAO lConsumoDAO;
    private List<ConsumoVO> lstContatos;
    private ConsumoAdapter adapter;

    boolean blnShort = false;
    int Posicao = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_consumo);

        lConsumoDAO = new ConsumoDAO(this);
        lConsumoDAO.open();
        lstContatos = lConsumoDAO.Consultar();
        adapter = new ConsumoAdapter(this, lstContatos);
        setListAdapter(adapter);
        registerForContextMenu(getListView());
    }

    public void onClick(View view) {

        btnConsumo = (Button)findViewById(R.id.add);
        btnConsumo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirContato();
            }
        });
    }

    public void btnCalcular(View view){

        btnCalcular = (Button)findViewById(R.id.bCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Main_calcular.class));
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ConsumoVO lConsumoVO = null;

            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == Activity.RESULT_OK) {
                lConsumoVO = (ConsumoVO)data.getExtras().getSerializable("consumo");

                if (requestCode == INCLUIR){

                    if (!lConsumoVO.getRegistro_inicial().equals("")) {

                        lConsumoDAO.open();
                        lConsumoDAO.inserir(lConsumoVO);
                        lstContatos.add(lConsumoVO);
                    }
                }else if (requestCode == ALTERAR){
                    lConsumoDAO.open();
                    lConsumoDAO.alterar(lConsumoVO);
                    lstContatos.set(Posicao, lConsumoVO);
                }
                adapter.notifyDataSetChanged();
            }
    }

    private void inserirContato(){

        Intent it = new Intent(this, ConsumoUI.class);
        it.putExtra("tipo", INCLUIR);
            startActivityForResult(it, INCLUIR);
    }

    @Override
    protected void onResume() {
        lConsumoDAO.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        lConsumoDAO.close();
        super.onPause();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            if (!blnShort)
            {
                Posicao = info.position;
            }
            blnShort = false;

            menu.setHeaderTitle("Selecione:");
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ConsumoVO lConsumoVO = null;

            int menuItemIndex = item.getItemId();
            lConsumoVO = (ConsumoVO) getListAdapter().getItem(Posicao);

            if (menuItemIndex == 0){

                Intent it = new Intent(this, ConsumoUI.class);
                it.putExtra("tipo", ALTERAR);
                it.putExtra("consumo", lConsumoVO);
                startActivityForResult(it, ALTERAR);
            }else if (menuItemIndex == 1){

                lConsumoDAO.excluir(lConsumoVO);
                lstContatos.remove(lConsumoVO);
                adapter.notifyDataSetChanged();
            }
        return true;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Posicao = position;
        blnShort = true;
        this.openContextMenu(l);
    }
}