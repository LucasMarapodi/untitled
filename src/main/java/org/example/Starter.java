package org.example;

import org.example.Entities.Aluno;
import org.example.Disciplina;
import org.example.Entities.Professor;

import java.util.List;
import java.util.Scanner;

public class Starter {
    void createBogusData(FileConnection fileConnection) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o CPF do aluno: ");
        String cpf = scanner.nextLine();

        List<Aluno> alunos = fileConnection.readFromFile("EscolaDB.json");
        if (Aluno.cpfExists(alunos, cpf)) {
            System.out.println("CPF já cadastrado. Aluno não será inserido.");
            return;
        }

        System.out.print("Informe o nome do aluno: ");
        String nome = scanner.nextLine();
        int idAluno = (int) (Math.random() * 1000);

        Aluno aluno = new Aluno(nome, cpf, idAluno);

        System.out.print("Quantas disciplinas deseja inserir para este aluno? ");
        int numDisciplinas = scanner.nextInt();
        scanner.nextLine();

        // Validar lista de professores
        List<Professor> professores = fileConnection.readProfessoresFromFile();

        for (int i = 0; i < numDisciplinas; i++) {
            System.out.print("Informe o nome da disciplina: ");
            String nomeDisciplina = scanner.nextLine();

            System.out.print("Informe o ID do professor: ");
            int professorId = scanner.nextInt();
            scanner.nextLine();

            // Validar se o professor existe com base no ID
            Professor professorValido = null;
            for (Professor professor : professores) {
                if (professor.getId() == professorId) {
                    professorValido = professor;
                    break;
                }
            }

            if (professorValido == null) {
                System.out.println("Professor com ID " + professorId + " não encontrado. Tente novamente.");
                i--;  // Permite tentar novamente a inserção da disciplina
                continue;
            }

            System.out.print("Informe a nota AV1: ");
            double av1 = scanner.nextDouble();
            System.out.print("Informe a nota AV2: ");
            double av2 = scanner.nextDouble();
            scanner.nextLine();

            Disciplina disciplina = new Disciplina(nomeDisciplina, professorValido.getNome());
            Boletim boletim = new Boletim(av1, av2, disciplina);
            aluno.boletins.add(boletim);
            System.out.println("Boletim inserido para disciplina " + nomeDisciplina + " com média " + boletim.calcularMedia());
        }

        fileConnection.saveToFile(aluno);
    }
}
