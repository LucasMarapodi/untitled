package org.example;

import org.example.Entities.Aluno;
import org.example.Entities.Professor;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileConnection fileConnection = new FileConnection();
        Starter starter = new Starter();

        // Inicializa o arquivo de professores, se necessário
        fileConnection.initializeProfessores();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Inserir Aluno");
            System.out.println("2. Consultar Alunos");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Listar Professores");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> starter.createBogusData(fileConnection); // Chama o método para criar e inserir aluno
                case 2 -> {
                    List<Aluno> alunos = fileConnection.readFromFile("EscolaDB.json");
                    System.out.println("Alunos no arquivo:");
                    for (Aluno aluno : alunos) {
                        System.out.println("ID: " + aluno.getIdAluno() + ", Nome: " + aluno.getNome() + ", CPF: " + aluno.getCpf());
                    }
                }
                case 3 -> {
                    System.out.print("Informe o CPF do aluno que deseja excluir: ");
                    String cpf = scanner.nextLine();
                    fileConnection.deleteFromFile(cpf);
                }
                case 4 -> {
                    // Listando professores
                    List<Professor> professores = fileConnection.readProfessoresFromFile();
                    System.out.println("Professores no arquivo:");
                    for (Professor professor : professores) {
                        System.out.println("ID: " + professor.getId() + ", Nome: " + professor.getNome() + ", Salário: " + professor.getSalario());
                    }
                }
                case 5 -> {
                    System.out.println("Saindo do programa.");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
