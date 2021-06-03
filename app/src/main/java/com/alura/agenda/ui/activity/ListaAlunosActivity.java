package com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.alura.agenda.R;
import com.alura.agenda.model.Aluno;
import com.alura.agenda.ui.ListAlunosView;

import org.jetbrains.annotations.Nullable;

import static com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private ListAlunosView listAlunosView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        listAlunosView = new ListAlunosView(this);
        configuraNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activitylista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(v -> abreFormularioInsereAluno());
    }

    private void abreFormularioInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_aluno_remover) {
            listAlunosView.confirmaRemocao(item);


        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAlunosView.atualizaAlunos();
    }


    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_aluno_listview);
        listAlunosView.configuraAdapter(listaAlunos);
        configuraListenerCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraListenerCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((adapterView, view, position, id) -> {
            Aluno alunosEscolhidos = (Aluno) adapterView.getItemAtPosition(position);
            abreFormularioEditacaoAluno(alunosEscolhidos);
        });
    }

    private void abreFormularioEditacaoAluno(Aluno aluno) {
        Intent abrirForumularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        abrirForumularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(abrirForumularioActivity);
    }
}
