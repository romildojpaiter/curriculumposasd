package br.com.gpaiter.curriculumposasd.activitys;

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
import br.com.gpaiter.curriculumposasd.dao.ExperienciaProfissionalDAO;
import br.com.gpaiter.curriculumposasd.domain.ExperienciaProfissional;

public class ExperienciaProfisionalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private List<Map<String, Object>> experienciasProfissionais;
    private ListView listView;

    private ExperienciaProfissionalDAO DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencia_profisional);

        DAO = new ExperienciaProfissionalDAO(this);
        listView = (ListView) findViewById(R.id.experienciaProfissionalListView);
        atualizaListaExperiencias();

    }

    private void atualizaListaExperiencias() {
        String[] de = {"tipoProjeto", "descricao", "inicio", "termino"};
        int[] para = { R.id.tipoProjeto, R.id.descricao, R.id.inicio, R.id.termino };
        SimpleAdapter adapter = new SimpleAdapter(this, listarExperienciaProfissional(), R.layout.experiencia_profissional_listview, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<? extends Map<String, Object>> listarExperienciaProfissional() {

        List<ExperienciaProfissional> lista = DAO.getListaOrdenada();
        experienciasProfissionais = new ArrayList<Map<String,Object>>();

        for (ExperienciaProfissional experiencia : lista) {

            Map<String, Object> item = new HashMap<String, Object>();
            item.put("tipoProjeto", R.drawable.negocios);
            item.put("descricao", experiencia.getDescricao());
            item.put("inicio", experiencia.getInicio());
            item.put("termino", experiencia.getTermino());
            experienciasProfissionais.add(item);
        }

        return experienciasProfissionais;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_experiencia_profissional, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

/*        Map<String, Object> map = experienciasProfissionais.get(position);
        String destino = (String) map.get("destino");
        String mensagem = "Viagem selecionada: "+ destino;
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_cadastrar) {
            startActivity(new Intent(this, CadastrarExperienciaProfissionalActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Aplicaçao Restartada", "Aplicaçao Restartada");
        this.atualizaListaExperiencias();
    }

    @Override
    public void onClick(DialogInterface dialog, int item) {

    }
}
