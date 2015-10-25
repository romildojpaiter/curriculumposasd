package br.com.gpaiter.curriculumposasd.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.gpaiter.curriculumposasd.R;

public class ExperienciaProfisionalListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> experienciasProfissionais;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiencia_profisional_list);


        listView= (ListView) findViewById(R.id.experienciaProfissionalListView);

        // String[] values = new String[] { "Ubuntu", "Android", "iPhone", "Windows", "Ubuntu", "Android", "iPhone", "Windows" };
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        // viewContainer = findViewById(R.id.undobar);
        // listView.setAdapter(adapter);

        String[] de = {"imagem", "destino", "data", "total"};
        int[] para = {R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor};
        SimpleAdapter adapter = new SimpleAdapter(this, listarExperienciaProfissional(), R.layout.experiencia_profissional_listview, de, para);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    private List<? extends Map<String, Object>> listarExperienciaProfissional() {
        experienciasProfissionais = new ArrayList<Map<String,Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.negocios);
        item.put("destino", "São Paulo");
        item.put("data","02/02/2012 a 04/02/2012");
        item.put("total", "Gasto total R$ 314,98");
        experienciasProfissionais.add(item);

        item = new HashMap<String, Object>();
        item.put("imagem", R.drawable.lazer);
        item.put("destino", "Maceió");
        item.put("data","14/05/2012 a 22/05/2012");
        item.put("total", "Gasto total R$ 25834,67");
        experienciasProfissionais.add(item);

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

        Map<String, Object> map = experienciasProfissionais.get(position);
        String destino = (String) map.get("destino");
        String mensagem = "Viagem selecionada: "+ destino;
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

    }
}
