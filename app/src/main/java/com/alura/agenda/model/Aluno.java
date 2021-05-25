package com.alura.agenda.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {

    private int id =0;
    private String nome;
    private String telefone;
    private String email;

    public Aluno(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Aluno() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public boolean temIdValido() {
        if(id > 0)
        {
            return true;
        }
        else {
            return false;
        }

    }
}
