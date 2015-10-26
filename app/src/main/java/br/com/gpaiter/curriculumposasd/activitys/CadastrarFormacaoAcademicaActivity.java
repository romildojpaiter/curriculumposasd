package br.com.gpaiter.curriculumposasd.activitys;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gpaiter.curriculumposasd.R;
import br.com.gpaiter.curriculumposasd.dao.FormacaoAcademicaDAO;
import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

public class CadastrarFormacaoAcademicaActivity extends AppCompatActivity {

    private Integer _ID = null;
    private EditText descricao, inicio, termino;

    private FormacaoAcademicaDAO DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_formacao_academica);

        DAO = new FormacaoAcademicaDAO(this);

        descricao = (EditText) findViewById(R.id.descricao);
        inicio = (EditText) findViewById(R.id.inicio);
        termino = (EditText) findViewById(R.id.termino);

    }


    public void salvarFormacaoAcademica(View view){

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FormacaoAcademica.DESCRICAO, descricao.getText().toString());
        values.put(DatabaseHelper.FormacaoAcademica.INICIO, inicio.getText().toString());
        values.put(DatabaseHelper.FormacaoAcademica.TERMINO, termino.getText().toString());


        long resultado = DAO.insert(values);

        if (resultado > -1){
            String mensagem = "Formação Academica salva com sucesso";
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            String mensagem = "Ocorreu um erro ao salvar a Formação Acadêmica";
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        DAO.close();
        super.onDestroy();
    }
}
