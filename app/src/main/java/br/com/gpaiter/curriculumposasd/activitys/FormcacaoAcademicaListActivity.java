package br.com.gpaiter.curriculumposasd.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gpaiter.curriculumposasd.R;

public class FormcacaoAcademicaListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> formacoesAcademicas;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcacao_academica_list);



        listView= (ListView) findViewById(R.id.formacaoAcademicaListView);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor};
        SimpleAdapter adapter = new SimpleAdapter(this, listarFormacaoAcademia(), R.layout.experiencia_profissional_listview, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


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
}
