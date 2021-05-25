package com.alura.agenda.ui.activity;

import com.alura.agenda.model.Aluno;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salva(Aluno aluno){

        aluno.setId(contadorId);
        alunos.add(aluno);

        aumentaId();
    }

    private void aumentaId() {
        contadorId++;
    }

    public void editar(Aluno aluno){

        Aluno alunoEncontrado = buscarAlunoPeloId(aluno);

        if(alunoEncontrado != null){
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscarAlunoPeloId(Aluno aluno) {
        for (Aluno a: alunos){
            if (a.getId() == aluno.getId()){
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos(){
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoRemovido = buscarAlunoPeloId(aluno);

        if (alunoRemovido != null){
            alunos.remove(alunoRemovido);
        }
    }
}
