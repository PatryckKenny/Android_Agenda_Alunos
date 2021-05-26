package com.alura.agenda;

import android.app.Application;

import com.alura.agenda.model.Aluno;
import com.alura.agenda.ui.activity.AlunoDAO;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunoDeTeste();
    }

    private void criarAlunoDeTeste(){
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno(4, "Alex", "111222333", "alex@gmail.com"));
        dao.salva(new Aluno(5, "joao", "333333333", "joao@gmail.com"));

    }
}
