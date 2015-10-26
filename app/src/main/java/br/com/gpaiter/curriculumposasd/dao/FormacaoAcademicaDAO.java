package br.com.gpaiter.curriculumposasd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;
import br.com.gpaiter.curriculumposasd.domain.FormacaoAcademica;

/**
 * Created by romildopaiter on 10/25/15.
 */
public class FormacaoAcademicaDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public FormacaoAcademicaDAO(Context context){
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
        if (values.get(DatabaseHelper.FormacaoAcademica._ID) != null){
            resultado = db.update(DatabaseHelper.FormacaoAcademica.TABELA, values, "_id = ?", new String[]{ DatabaseHelper.FormacaoAcademica._ID });
        } else {
            resultado = db.insert(DatabaseHelper.FormacaoAcademica.TABELA, null, values);
        }
        return resultado;
    }

    public List<FormacaoAcademica> getLista(){

        this.getReadDb();
        List<FormacaoAcademica> experiencias = new ArrayList<FormacaoAcademica>();

        Cursor cursor = db.query(DatabaseHelper.FormacaoAcademica.TABELA,
                DatabaseHelper.FormacaoAcademica.COLUNAS,
                null, null, null, null, null);

        while (cursor.moveToNext()){
            FormacaoAcademica experiencia = criarExperiencia(cursor);
            experiencias.add(experiencia);
        }

        return experiencias;
    }

    public List<FormacaoAcademica> getListaOrdenada(){

        this.getReadDb();
        List<FormacaoAcademica> experiencias = new ArrayList<FormacaoAcademica>();

        String orderBy = DatabaseHelper.FormacaoAcademica._ID + " DESC";
        Cursor cursor = db.query(DatabaseHelper.FormacaoAcademica.TABELA,
                DatabaseHelper.FormacaoAcademica.COLUNAS,
                null, null, null, null, orderBy);

        while (cursor.moveToNext()){
            FormacaoAcademica experiencia = criarExperiencia(cursor);
            experiencias.add(experiencia);
        }

        return experiencias;
    }

    private FormacaoAcademica criarExperiencia(Cursor cursor) {
        FormacaoAcademica experiencia = new FormacaoAcademica();

        experiencia.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.FormacaoAcademica._ID)));
        experiencia.setDescricao(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FormacaoAcademica.DESCRICAO)));
        experiencia.setInicio(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FormacaoAcademica.INICIO)));
        experiencia.setTermino(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FormacaoAcademica.TERMINO)));

        return  experiencia;
    }

    public void remover(Long id) {
        this.getWriteDb();
        String[] where = new String[] { id.toString() };
        db.delete(DatabaseHelper.FormacaoAcademica.TABELA, "_id = ?", where);
    }
}
