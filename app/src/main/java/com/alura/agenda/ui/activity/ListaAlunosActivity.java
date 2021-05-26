package com.alura.agenda.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alura.agenda.R;
import com.alura.agenda.model.Aluno;
import com.alura.agenda.ui.adapter.ListaAlunosAdapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;


public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final AlunoDAO dao = new AlunoDAO();;
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        setTitle(TITULO_APPBAR);

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
        CharSequence tituloDoMenu = item.getTitle();
        if (itemId == R.id.activity_lista_aluno_remover) {
            confirmaRemocao(item);


        }
        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(this)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que gostaria de remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo meuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(meuInfo.position);
                        remove(alunoEscolhido);

                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
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

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaDeAlunos.setAdapter(adapter);
    }
}
