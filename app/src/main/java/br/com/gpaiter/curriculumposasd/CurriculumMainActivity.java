package br.com.gpaiter.curriculumposasd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.gpaiter.curriculumposasd.activitys.ExperienciaProfisionalActivity;
import br.com.gpaiter.curriculumposasd.activitys.FormacaoAcademicaActivity;
import br.com.gpaiter.curriculumposasd.activitys.PerfilActivity;
import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

public class CurriculumMainActivity extends AppCompatActivity {


    private Integer _ID = null;
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String DESCRICAO = "descricao";

    private TextView nome, email, descricao;

    // Utilizado para gravar no banco de dados
    private DatabaseHelper helper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_curriculum_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nome = (TextView) findViewById(R.id.nome);
        email = (TextView) findViewById(R.id.email);
        descricao = (TextView) findViewById(R.id.descricao);

        // prepara acesso ao banco de dados
        helper = new DatabaseHelper(this);

        // Pesquisa sempre pelo primeiro item da tabela
        pesquisarPerfilPrincipal();

    }

    private void pesquisarPerfilPrincipal() {
        database = helper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT _id, nome, email, descricao FROM " +
                        DatabaseHelper.TB_PERFIL + " WHERE _id = ?", new String[] { "1" }
        );

        if (cursor.getCount() > 0){
            cursor.moveToFirst();

            _ID = cursor.getInt(0);
            nome.setText(cursor.getString(1));
            email.setText(cursor.getString(2));
            descricao.setText(cursor.getString(3));
        }
        cursor.close();
        helper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_curriculum_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_perfil) {
            startActivity(new Intent(this, PerfilActivity.class));
            return true;
        }
        if (id == R.id.action_close) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void botaoSelecionado(View view){
        switch (view.getId()){
            case R.id.btnVisualizarFormacaoAcademica:
                startActivity(new Intent(this, FormacaoAcademicaActivity.class));
                break;
            case R.id.btnVisualizarFormacaoProfissional:
                startActivity(new Intent(this, ExperienciaProfisionalActivity.class));
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Aplicaçao Pausada", "Aplicaçao Pausada");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Aplicaçao Restartada", "Aplicaçao Restartada");
        this.pesquisarPerfilPrincipal();
    }
}
