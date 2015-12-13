package com.example.luiseduardo.eparkeletrica.DAOCadastro;
 
import java.util.List;

import android.app.Activity;
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

import com.example.luiseduardo.eparkeletrica.R;

public class MainCadastro extends ListActivity {
    private static final int INCLUIR = 0;
    private static final int ALTERAR = 1;

    protected CadastroDAO lCadastroDAO;
    protected List<CadastroVO> lstCadastro;
    protected CadastroAdapter adapter;
    private Button btnCadastro;
    boolean blnShort = false;
    int Posicao = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lCadastroDAO = new CadastroDAO(this);
        lCadastroDAO.open();

        lstCadastro = lCadastroDAO.Consultar();

        adapter = new CadastroAdapter(this, lstCadastro);
        setListAdapter(adapter);

        registerForContextMenu(getListView());
    }

    public void onClick(View view) {

        btnCadastro = (Button) findViewById(R.id.add);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirContato();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CadastroVO lCadastroVO = null;

            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                        lCadastroVO = (CadastroVO)data.getExtras().getSerializable("cadastro");

                if (requestCode == INCLUIR) {
                        lCadastroDAO.open();
                         lCadastroDAO.inserir(lCadastroVO);
                        lstCadastro.add(lCadastroVO);
                    }
                }else if (requestCode == ALTERAR){
                    lCadastroDAO.open();
                    lCadastroDAO.Alterar(lCadastroVO);
                    lstCadastro.set(Posicao, lCadastroVO);
                }

                adapter.notifyDataSetChanged();
            }


    private void inserirContato(){

            Intent it = new Intent(this, CadastroUI.class);
            it.putExtra("tipo", INCLUIR);
            startActivityForResult(it, INCLUIR);
    }    
     
    @Override
    protected void onResume() {
        lCadastroDAO.open();
        super.onResume();
    }
 
    @Override
    protected void onPause() {
        lCadastroDAO.close();
        super.onPause();
    }

    @Override   
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            if (!blnShort)
            {
                Posicao = info.position;
            }

            menu.setHeaderTitle("Selecione:");
            String[] menuItems = getResources().getStringArray(R.array.menu);             
            for (int i = 0; i<menuItems.length; i++) {                
                menu.add(Menu.NONE, i, i, menuItems[i]);            
            }        

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {       
        CadastroVO lCadastroVO = null;
        int menuItemIndex = item.getItemId();
        lCadastroVO = (CadastroVO) getListAdapter().getItem(Posicao);

            if (menuItemIndex == 0) {

                Intent it = new Intent(this, CadastroUI.class);
                it.putExtra("tipo", ALTERAR);
                it.putExtra("cadastro", lCadastroVO);
                startActivityForResult(it, ALTERAR);

            } else if (menuItemIndex == 1) {

                lCadastroDAO.Excluir(lCadastroVO);
                lstCadastro.remove(lCadastroVO);
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