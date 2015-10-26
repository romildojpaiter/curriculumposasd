package br.com.gpaiter.curriculumposasd.activitys;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gpaiter.curriculumposasd.R;
import br.com.gpaiter.curriculumposasd.dao.ExperienciaProfissionalDAO;
import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

public class CadastrarExperienciaProfissionalActivity extends AppCompatActivity {

    private Integer _ID = null;
    private EditText descricao, inicio, termino;
    private CheckBox empregoAtual;

    private ExperienciaProfissionalDAO DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_experiencia_profissional);

        DAO = new ExperienciaProfissionalDAO(this);

        descricao = (EditText) findViewById(R.id.descricao);
        inicio = (EditText) findViewById(R.id.inicio);
        termino = (EditText) findViewById(R.id.termino);
        empregoAtual = (CheckBox) findViewById(R.id.empregoAtual);


    }

    public void desabilitaTermino(View view){
        if(empregoAtual.isChecked()){
            termino.setText("");
            termino.setEnabled(false);
        } else {
            termino.setEnabled(true);
        }
    }


    public void salvarExperienciaProfissional(View view){

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ExperienciaProfissional.DESCRICAO, descricao.getText().toString());
        values.put(DatabaseHelper.ExperienciaProfissional.INICIO, inicio.getText().toString());
        values.put(DatabaseHelper.ExperienciaProfissional.TERMINO, termino.getText().toString());
        values.put(DatabaseHelper.ExperienciaProfissional.EMPREGO_ATUAL, empregoAtual.isChecked() ? 1 : 0);


        long resultado = DAO.insert(values);

        if (resultado > -1){
            String mensagem = "Experiência Profissional salvo com sucesso";
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            String mensagem = "Ocorreu um erro ao salvar a Experiência Profissional";
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        DAO.close();
        super.onDestroy();
    }
}
