package org.example;

public class Boletim {
    double av1;
    double av2;
    Disciplina disciplina;

    Boletim(double av1, double av2, Disciplina disciplina) {
        this.av1 = av1;
        this.av2 = av2;
        this.disciplina = disciplina;
    }

    double calcularMedia() {
        return (av1 + av2) / 2;
    }
}