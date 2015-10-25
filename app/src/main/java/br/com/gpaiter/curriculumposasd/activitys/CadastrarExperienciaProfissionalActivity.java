package br.com.gpaiter.curriculumposasd.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import br.com.gpaiter.curriculumposasd.R;

public class CadastrarExperienciaProfissionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_experiencia_profissional);
    }


    public void salvarExperienciaProfissional(View view){



        String mensagem = "Experiência Profissional salvo com sucesso";
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
        finish();

    }
}
