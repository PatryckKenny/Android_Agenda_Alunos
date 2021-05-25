package com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;


import com.alura.agenda.R;
import com.alura.agenda.model.Aluno;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

        configuraNovoAluno();
        configuraLista();

//        dao.salva(new Aluno(4, "Alex", "111222333", "alex@gmail.com"));
//        dao.salva(new Aluno(5, "joao", "333333333", "joao@gmail.com"));
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
        CharSequence tituloDoMenu = item.getTitle();
        if (itemId == R.id.activity_lista_aluno_remover) {
            AdapterView.AdapterContextMenuInfo meuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(meuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_aluno_listview);
        configuraAdapter(listaAlunos);
        configuraListenerCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
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

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
