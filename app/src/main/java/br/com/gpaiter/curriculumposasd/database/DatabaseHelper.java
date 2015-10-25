package br.com.gpaiter.curriculumposasd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DE_DADOS = "CurriculumPosasd";
    private static int VERSAO = 1;

    public static final String TB_PERFIL = "perfil";
    public static final String TB_EXPERIENCIA_PROFISSIONAL = "experiencia_profissional";
    public static final String TB_FORMACAO_ACADEMICA = "formacao_academica";

    public DatabaseHelper(Context context) {
        super(context, BANCO_DE_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABELA DE PESSOA
        db.execSQL("CREATE TABLE perfil (_id INTEGER PRIMARY KEY, " +
            "nome TEXT, descricao TEXT, email TEXT);");

        // TABELA DE EXPERIENCIA PROFISSIONAL
        db.execSQL("CREATE TABLE experiencia_profissional ( _id INTEGER PRIMARY KEY, " +
            "descricao TEXT, inicio INTEGER, termino INTEGER, atual INTEGER);");

        // TABELA DE FORMACAO ACADEMICA
        db.execSQL("CREATE TABLE formacao_academica ( _id INTEGER PRIMARY KEY, " +
                "descricao TEXT, inicio INTEGER, termino INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
