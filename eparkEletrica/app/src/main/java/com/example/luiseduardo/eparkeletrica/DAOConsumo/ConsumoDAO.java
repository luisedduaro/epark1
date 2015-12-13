package com.example.luiseduardo.eparkeletrica.DAOConsumo;
 
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ConsumoDAO {
 
    private SQLiteDatabase database;
    private BaseDAOConsumo dbHelper;
 
    private String[] colunas = {BaseDAOConsumo.ID,
                                BaseDAOConsumo.REGISTRO_INICIO,
                                BaseDAOConsumo.REGISTRO_FINAL,
                                BaseDAOConsumo.MES };
 
    public ConsumoDAO(Context context) {
        dbHelper = new BaseDAOConsumo(context);
    }
 
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
 
    public void close() {
        dbHelper.close();
    }
 
    public long inserir(ConsumoVO pValue) {
        ContentValues values = new ContentValues();
     
        values.put(BaseDAOConsumo.REGISTRO_INICIO, pValue.getRegistro_inicial());
        values.put(BaseDAOConsumo.REGISTRO_FINAL, pValue.getRegistro_final());
        values.put(BaseDAOConsumo.MES, pValue.getMes());
         
        return database.insert(BaseDAOConsumo.TBL_CONSUMO, null, values);
    }
     
     
    public int alterar(ConsumoVO pValue) {
        long id = pValue.getId();
        ContentValues values = new ContentValues();
         
        values.put(BaseDAOConsumo.REGISTRO_INICIO, pValue.getRegistro_inicial());
        values.put(BaseDAOConsumo.REGISTRO_FINAL, pValue.getRegistro_final());
        values.put(BaseDAOConsumo.MES, pValue.getMes());

        return database.update(BaseDAOConsumo.TBL_CONSUMO, values, BaseDAOConsumo.ID + " = " + id, null);
    }
 
    public void excluir(ConsumoVO pValue) {
        long id = pValue.getId();
         
        database.delete(BaseDAOConsumo.TBL_CONSUMO, BaseDAOConsumo.ID + " = " + id, null);
    }
 
    public List<ConsumoVO> Consultar() {
        List<ConsumoVO> lstConsumo = new ArrayList<ConsumoVO>();
 
        Cursor cursor = database.query(BaseDAOConsumo.TBL_CONSUMO, colunas,
                null, null, null, null, BaseDAOConsumo.MES);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ConsumoVO lConsumoVO = cursorToConsumo(cursor);
            lstConsumo.add(lConsumoVO);
            cursor.moveToNext();
        }
         
        cursor.close();
        return lstConsumo;
    }
 
        private ConsumoVO cursorToConsumo(Cursor cursor) {
        ConsumoVO lConsumoVO = new ConsumoVO();
        lConsumoVO.setId(cursor.getLong(0));
        lConsumoVO.setRegistro_inicial(cursor.getString(1));
        lConsumoVO.setRegistro_inicial(cursor.getString(2));
        lConsumoVO.setMes(cursor.getString(3));
        return lConsumoVO;
    }

}