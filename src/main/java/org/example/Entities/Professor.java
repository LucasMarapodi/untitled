package org.example.Entities;

public class Professor {
    private String nome;
    private String cpf;
    private double salario;
    private int idProfessor;  // Novo campo para ID do professor

    public Professor(String nome, String cpf, double salario, int idProfessor) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalario() {
        return salario;
    }

    public int getId() {
        return idProfessor;
    }
}
