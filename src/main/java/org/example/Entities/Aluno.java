package org.example.Entities;

import org.example.Boletim;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
        public int idAluno;
        public List<Boletim> boletins;

        public int getIdAluno() {
                return idAluno;
        }

        public Aluno(String nome, String cpf, int idAluno) {
                super(nome, cpf); // Chama o construtor de Pessoa para inicializar nome e cpf
                this.idAluno = idAluno;
                this.boletins = new ArrayList<>();
        }

        public static boolean cpfExists(List<Aluno> alunos, String cpf) {
                for (Aluno aluno : alunos) {
                        if (aluno.getCpf().equals(cpf)) {
                                return true;
                        }
                }
                return false;
        }
}
