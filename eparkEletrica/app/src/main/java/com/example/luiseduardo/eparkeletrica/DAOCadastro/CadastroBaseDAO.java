package com.example.luiseduardo.eparkeletrica.DAOCadastro;
 
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 

public class CadastroBaseDAO extends SQLiteOpenHelper{
 
    public static final String TBL_CADASTRO = "cadastro";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";
 
    private static final String DATABASE_NAME = "cadastro.db";
    private static final int DATABASE_VERSION = 1;

    public CadastroBaseDAO(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase database) {

         String CREATE_CADASTRO = "create table " +
                TBL_CADASTRO + "( " + ID       + " integer primary key autoincrement, " +
                NOME     + " text not null, " +
                ENDERECO + " text not null, " +
                TELEFONE + " text not null);";

        database.execSQL(CREATE_CADASTRO);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + TBL_CADASTRO);
        onCreate(db);
    }
 
}