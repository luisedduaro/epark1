package com.example.luiseduardo.eparkeletrica.DAOCadastro;
 
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CadastroDAO {
 
    private SQLiteDatabase database;
    private CadastroBaseDAO dbHelper;
 
    //Campos da tabela Agenda
    private String[] colunas = {CadastroBaseDAO.ID,
                                CadastroBaseDAO.NOME,
                                CadastroBaseDAO.ENDERECO,
                                CadastroBaseDAO.TELEFONE };
 
    public CadastroDAO(Context context) {
        dbHelper = new CadastroBaseDAO(context);
    }
 
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
 
    public void close() {
        dbHelper.close();
    }
 
    public long inserir(CadastroVO pValue) {
        ContentValues values = new ContentValues();
     
        values.put(CadastroBaseDAO.NOME, pValue.getNome());
        values.put(CadastroBaseDAO.ENDERECO, pValue.getEndereco());
        values.put(CadastroBaseDAO.TELEFONE, pValue.getTelefone());
         
        return database.insert(CadastroBaseDAO.TBL_CADASTRO, null, values);
    }
     
     
    public int Alterar(CadastroVO pValue) {
        long id = pValue.getId();
        ContentValues values = new ContentValues();
         
        values.put(CadastroBaseDAO.NOME, pValue.getNome());
        values.put(CadastroBaseDAO.ENDERECO, pValue.getEndereco());
        values.put(CadastroBaseDAO.TELEFONE, pValue.getTelefone());
 
        return database.update(CadastroBaseDAO.TBL_CADASTRO, values, CadastroBaseDAO.ID + " = " + id, null);
    }
 
    public void Excluir(CadastroVO pValue) {
        long id = pValue.getId();

        database.delete(CadastroBaseDAO.TBL_CADASTRO, CadastroBaseDAO.ID + " = " + id, null);
    }
 
    public List<CadastroVO> Consultar() {
        List<CadastroVO> lstCadastro = new ArrayList<CadastroVO>();
 
        Cursor cursor = database.query(CadastroBaseDAO.TBL_CADASTRO, colunas,
                null, null, null, null, CadastroBaseDAO.NOME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CadastroVO lCadastroVO = cursorToCadastro(cursor);
            lstCadastro.add(lCadastroVO);
            cursor.moveToNext();
        }

        cursor.close();
        return lstCadastro;
    }
 

    private CadastroVO cursorToCadastro(Cursor cursor) {
        CadastroVO lCadastroVO = new CadastroVO();
        lCadastroVO.setId(cursor.getLong(0));
        lCadastroVO.setNome(cursor.getString(1));
        lCadastroVO.setEndereco(cursor.getString(2));
        lCadastroVO.setTelefone(cursor.getString(3));
        return lCadastroVO;
    }
}