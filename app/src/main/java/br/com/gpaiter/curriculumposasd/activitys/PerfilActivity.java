package br.com.gpaiter.curriculumposasd.activitys;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gpaiter.curriculumposasd.R;
import br.com.gpaiter.curriculumposasd.database.DatabaseHelper;

public class PerfilActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_perfil);

        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        descricao = (EditText) findViewById(R.id.descricao);

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
    }

    public void salvarPerfil(View view) {

        database = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, nome.getText().toString());
        values.put(EMAIL, email.getText().toString());
        values.put(DESCRICAO, descricao.getText().toString());

        long resultado = -1;
        if (_ID != null){
            resultado = database.update(DatabaseHelper.TB_PERFIL, values, "_id = ?", new String[]{ _ID.toString() });
        } else {
            resultado = database.insert(DatabaseHelper.TB_PERFIL, null, values);
        }

        if ( resultado != -1){
            String mensagem = "Perfil salvo com sucesso";
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
            finish();
        } else {
            String mensagem = "Ocorreu um erro ao salvar o Perfil";
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}
