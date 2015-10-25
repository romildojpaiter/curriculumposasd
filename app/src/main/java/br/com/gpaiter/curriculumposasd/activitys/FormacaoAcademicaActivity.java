package br.com.gpaiter.curriculumposasd.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class FormacaoAcademicaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> formacoesAcademicas;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcacao_academica);

        listView= (ListView) findViewById(R.id.formacaoAcademicaListView);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.tipoFormacao, R.id.descricao, R.id.inicio, R.id.termino};
        SimpleAdapter adapter = new SimpleAdapter(this, listarFormacaoAcademia(), R.layout.experiencia_profissional_listview, de, para);
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

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.negocios);
        item.put("destino", "São Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("total", "Gasto total R$ 314,98");
        formacoesAcademicas.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Maceió");
        item.put("data","14/05/2012 a 22/05/2012");
        item.put("total", "Gasto total R$ 25834,67");
        formacoesAcademicas.add(item);

        return formacoesAcademicas;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

}