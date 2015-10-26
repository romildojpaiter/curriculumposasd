package br.com.gpaiter.curriculumposasd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;
import br.com.gpaiter.curriculumposasd.domain.ExperienciaProfissional;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class ExperienciaProfissionalDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public ExperienciaProfissionalDAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private void getWriteDb() {
        db = helper.getWritableDatabase();
    }

    private void getReadDb(){
        db = helper.getReadableDatabase();
    }

    public void close(){
        helper.close();
    }

    public long insert(ContentValues values) {
        this.getWriteDb();
        long resultado = -1;
        if (values.get(DatabaseHelper.ExperienciaProfissional._ID) != null){
            resultado = db.update(DatabaseHelper.ExperienciaProfissional.TABELA, values, "_id = ?", new String[]{ DatabaseHelper.ExperienciaProfissional._ID });
        } else {
            resultado = db.insert(DatabaseHelper.ExperienciaProfissional.TABELA, null, values);
        }
        return resultado;
    }

    public List<ExperienciaProfissional> getLista(){

        this.getReadDb();
        List<ExperienciaProfissional> experiencias = new ArrayList<ExperienciaProfissional>();

        Cursor cursor = db.query(DatabaseHelper.ExperienciaProfissional.TABELA,
                DatabaseHelper.ExperienciaProfissional.COLUNAS,
                null, null, null, null, null);

        while (cursor.moveToNext()){
            ExperienciaProfissional experiencia = criarExperiencia(cursor);
            experiencias.add(experiencia);
        }

        return experiencias;
    }

    public List<ExperienciaProfissional> getListaOrdenada(){

        this.getReadDb();
        List<ExperienciaProfissional> experiencias = new ArrayList<ExperienciaProfissional>();

        String orderBy = DatabaseHelper.ExperienciaProfissional._ID + " DESC";
        Cursor cursor = db.query(DatabaseHelper.ExperienciaProfissional.TABELA,
                DatabaseHelper.ExperienciaProfissional.COLUNAS,
                null, null, null, null, orderBy);

        while (cursor.moveToNext()){
            ExperienciaProfissional experiencia = criarExperiencia(cursor);
            experiencias.add(experiencia);
        }

        return experiencias;
    }

    private ExperienciaProfissional criarExperiencia(Cursor cursor) {
        ExperienciaProfissional experiencia = new ExperienciaProfissional();

        experiencia.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.ExperienciaProfissional._ID)));
        experiencia.setDescricao(cursor.getString(cursor.getColumnIndex(DatabaseHelper.ExperienciaProfissional.DESCRICAO)));
        experiencia.setInicio(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ExperienciaProfissional.INICIO)));
        experiencia.setTermino(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ExperienciaProfissional.TERMINO)));
        experiencia.setEmpregoAtual(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ExperienciaProfissional.EMPREGO_ATUAL)) == 1 ? true : false);

        return  experiencia;
    }
}
