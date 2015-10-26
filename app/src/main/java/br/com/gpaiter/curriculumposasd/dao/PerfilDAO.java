package br.com.gpaiter.curriculumposasd.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class PerfilDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public PerfilDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb() {
        if (db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
    }
}
