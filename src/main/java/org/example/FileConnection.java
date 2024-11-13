package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.Entities.Professor;
import org.example.Entities.Aluno;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileConnection {
    private static final String ALUNO_FILE_NAME = "EscolaDB.json";
    private static final String PROFESSOR_FILE_NAME = "ProfessoresDB.json";
    private final Gson gson;

    public FileConnection() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Método para salvar um aluno no arquivo
    public void saveToFile(Aluno aluno) {
        List<Aluno> alunos = readFromFile(ALUNO_FILE_NAME);
        alunos.add(aluno);

        try (Writer writer = new FileWriter(ALUNO_FILE_NAME)) {
            gson.toJson(alunos, writer);
            System.out.println("Dados do aluno salvos no arquivo JSON.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo JSON: " + e.getMessage());
        }
    }

    // Método para ler alunos do arquivo
    public List<Aluno> readFromFile(String fileName) {
        try (Reader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<List<Aluno>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo JSON não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler dados do arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para deletar aluno do arquivo
    public void deleteFromFile(String cpf) {
        List<Aluno> alunos = readFromFile(ALUNO_FILE_NAME);
        boolean alunoRemovido = alunos.removeIf(aluno -> aluno.getCpf().equals(cpf));

        try (Writer writer = new FileWriter(ALUNO_FILE_NAME)) {
            gson.toJson(alunos, writer);
            if (alunoRemovido) {
                System.out.println("Aluno com CPF " + cpf + " foi removido.");
            } else {
                System.out.println("Aluno com CPF " + cpf + " não encontrado.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo JSON: " + e.getMessage());
        }
    }

    public void initializeProfessores() {
        File file = new File(PROFESSOR_FILE_NAME);
        if (!file.exists()) {
            List<Professor> professores = new ArrayList<>();

            // Criação dos professores com IDs exclusivos
            int professorId = 1;  // Começar com 1 (ou outro número qualquer)
            professores.add(new Professor("Carlos Souza", "111.222.333-44", 3000.0, professorId++));
            professores.add(new Professor("Ana Oliveira", "222.333.444-55", 3200.0, professorId++));
            professores.add(new Professor("Marcos Santos", "333.444.555-66", 3100.0, professorId++));
            professores.add(new Professor("Julia Ramos", "444.555.666-77", 3500.0, professorId++));
            professores.add(new Professor("Fernanda Lima", "555.666.777-88", 3400.0, professorId++));

            saveProfessoresToFile(professores);
            System.out.println("Arquivo de professores criado com 5 professores iniciais.");
        }
    }

    // Método para salvar lista de professores no arquivo
    private void saveProfessoresToFile(List<Professor> professores) {
        try (Writer writer = new FileWriter(PROFESSOR_FILE_NAME)) {
            gson.toJson(professores, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados dos professores no arquivo JSON: " + e.getMessage());
        }
    }

    // Método para ler a lista de professores do arquivo
    public List<Professor> readProfessoresFromFile() {
        try (Reader reader = new FileReader(PROFESSOR_FILE_NAME)) {
            Type listType = new TypeToken<List<Professor>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de professores não encontrado. Retornando lista vazia.");
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler dados dos professores do arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
