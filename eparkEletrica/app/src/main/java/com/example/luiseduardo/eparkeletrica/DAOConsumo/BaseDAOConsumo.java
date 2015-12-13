package com.example.luiseduardo.eparkeletrica.DAOConsumo;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
//Classe respons�vel pela cria��o do Banco de Dados e tabelas
public class BaseDAOConsumo extends SQLiteOpenHelper
{
 
    public static final String TBL_CONSUMO = "consumo";
    public static final String ID = "_id";
    public static final String REGISTRO_INICIO = "registroInicio";
    public static final String REGISTRO_FINAL = "registroFinal";
    public static final String MES = "mes";
 
    private static final String DATABASE_NAME = "consumo.db";
    private static final int DATABASE_VERSION = 1;

    public BaseDAOConsumo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase database) {

     String CREATE_AGENDA = "create table " +
                TBL_CONSUMO + "( " + ID       + " integer primary key autoincrement, " +
                REGISTRO_INICIO     + " text not null, " +
                REGISTRO_FINAL + " text not null, " +
                MES + " text not null);";

        database.execSQL(CREATE_AGENDA);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TBL_CONSUMO);
        onCreate(db);
    }
 
}