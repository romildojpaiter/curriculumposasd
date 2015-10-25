package br.com.gpaiter.curriculumposasd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gpaiter.curriculumposasd.activitys.ExperienciaProfisionalListActivity;
import br.com.gpaiter.curriculumposasd.activitys.FormcacaoAcademicaListActivity;
import br.com.gpaiter.curriculumposasd.activitys.PerfilActivity;
import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

public class CurriculumMainActivity extends AppCompatActivity {


    private Integer _ID = null;
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String DESCRICAO = "descricao";

    private EditText nome, email, descricao;

    // Utilizado para gravar no banco de dados
    private DatabaseHelper helper;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_curriculum_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
                startActivity(new Intent(this, FormcacaoAcademicaListActivity.class));
                break;
            case R.id.btnVisualizarFormacaoProfissional:
                startActivity(new Intent(this, ExperienciaProfisionalListActivity.class));
                break;

        }
        Toast.makeText(this, "Item selecionado: " + view.getId(), Toast.LENGTH_SHORT).show();
    }
}
