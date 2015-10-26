package br.com.gpaiter.curriculumposasd.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gpaiter.curriculumposasd.R;
import br.com.gpaiter.curriculumposasd.dao.FormacaoAcademicaDAO;
import br.com.gpaiter.curriculumposasd.domain.FormacaoAcademica;

public class FormacaoAcademicaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private List<Map<String, Object>> formacoesAcademicas;
    private ListView listView;

    private FormacaoAcademicaDAO DAO;

    private AlertDialog alertDialog;
    private AlertDialog confirmacaoDialog;
    private int formacaoAcademicaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcacao_academica);

        DAO = new FormacaoAcademicaDAO(this);

        listView= (ListView) findViewById(R.id.formacaoAcademicaListView);
        atualizaFormacaoAcademica();

        this.alertDialog = criaAlertDialog();
        this.confirmacaoDialog = criaDialogConfirmacao();
    }

    private void atualizaFormacaoAcademica() {
        String[] de = {"tipoFormacao", "descricao", "inicio", "termino"};
        int[] para = { R.id.tipoProjeto, R.id.descricao, R.id.inicio, R.id.termino };
        SimpleAdapter adapter = new SimpleAdapter(this, listarFormacaoAcademia(), R.layout.formacao_academica_listview, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formacao_academica, menu);
        return true;
    }

    private List<? extends Map<String, Object>> listarFormacaoAcademia() {

        formacoesAcademicas = new ArrayList<Map<String,Object>>();

        List<FormacaoAcademica> lista = DAO.getListaOrdenada();

        for (FormacaoAcademica formacaoAcademica : lista) {

            Map<String, Object> item = new HashMap<String, Object>();
            item.put("tipoFormacao", R.drawable.ic_cube_outline_black_18dp);
            item.put("id", formacaoAcademica.getId());
            item.put("descricao", formacaoAcademica.getDescricao());
            item.put("inicio", formacaoAcademica.getInicio());
            item.put("termino", formacaoAcademica.getTermino());
            formacoesAcademicas.add(item);
        }

        return formacoesAcademicas;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        this.formacaoAcademicaSelecionada = position;
        alertDialog.show();

    }

    private AlertDialog criaAlertDialog() {
        final CharSequence[] items = {
                getString(R.string.remover)
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(items, this);
        return builder.create();
    }


    @Override
    public void onClick(DialogInterface dialog, int item) {


        Long id = (Long) formacoesAcademicas.get(formacaoAcademicaSelecionada).get("id");
        Log.d("Opçao Selecionada: ", " " + item + " " + id);

        switch (item) {
            case 0 :
                confirmacaoDialog.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                formacoesAcademicas.remove(formacaoAcademicaSelecionada);
                DAO.remover(id);
                listView.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE :
                confirmacaoDialog.dismiss();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cadastrar) {
            startActivity(new Intent(this, CadastrarFormacaoAcademicaActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Aplicaçao Restartada", "Aplicaçao Restartada");
        this.atualizaFormacaoAcademica();
    }

    private AlertDialog criaDialogConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_formacao_academica);
        builder.setPositiveButton(getString(R.string.sim), this);
        builder.setNegativeButton(getString(R.string.nao), this);

        return builder.create();
    }

}
