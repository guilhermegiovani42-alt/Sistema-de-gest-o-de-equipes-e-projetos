package view;

import controller.ProjetoController;
import model.Perfil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private ProjetoController controller;
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ConsoleView(ProjetoController controller) {
        this.controller = controller;
    }

    public void iniciarMenu() {
        int opcao;
        do {
            System.out.println("\n--- GESTAO DE PROJETOS ---");
            System.out.println("1. Cadastrar Usuario");
            System.out.println("2. Cadastrar Equipe");
            System.out.println("3. Vincular Membro a Equipe");
            System.out.println("4. Cadastrar Projeto");
            System.out.println("5. Alocar Equipe em Projeto");
            System.out.println("6. Listar Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = lerInteiro();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1 -> menuCadastrarUsuario();
                case 2 -> menuCadastrarEquipe();
                case 3 -> menuVincularMembro();
                case 4 -> menuCadastrarProjeto();
                case 5 -> menuAlocarEquipe();
                case 6 -> menuListarGeral();
                case 0 -> System.out.println("Fechando sistema...");
                default -> System.out.println("Opcao invalida!");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void menuCadastrarUsuario() {
        System.out.print("Nome Completo: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        
        System.out.println("Selecione o Perfil de Acesso:");
        System.out.println("1 - Administrador | 2 - Gerente | 3 - Colaborador");
        int pOpt = lerInteiro();
        Perfil perfil = switch(pOpt) {
            case 1 -> Perfil.ADMINISTRADOR;
            case 2 -> Perfil.GERENTE;
            default -> Perfil.COLABORADOR;
        };

        controller.cadastrarUsuario(nome, cpf, email, cargo, perfil);
        System.out.println("Usuario cadastrado com perfil " + perfil + "!");
    }

    private void menuCadastrarEquipe() {
        System.out.print("Nome da Equipe: ");
        String nome = scanner.nextLine();
        controller.cadastrarEquipe(nome);
        System.out.println("Equipe cadastrada!");
    }

    private void menuVincularMembro() {
        if (controller.getEquipes().isEmpty() || controller.getUsuarios().isEmpty()) {
            System.out.println("Cadastre equipes e usuarios primeiro.");
            return;
        }
        System.out.println("Selecione a Equipe:");
        for (int i = 0; i < controller.getEquipes().size(); i++) {
            System.out.println(i + " - " + controller.getEquipes().get(i).getNome());
        }
        int eq = lerInteiro();

        System.out.println("Selecione o Usuario:");
        for (int i = 0; i < controller.getUsuarios().size(); i++) {
            System.out.println(i + " - " + controller.getUsuarios().get(i).getNomeCompleto());
        }
        int us = lerInteiro();

        if (controller.vincularMembroAEquipe(eq, us)) {
            System.out.println("Vinculado com sucesso!");
        }
    }

    private void menuCadastrarProjeto() {
        System.out.print("Nome do Projeto: ");
        String nome = scanner.nextLine();
        System.out.print("Data de Inicio (DD/MM/AAAA): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Data de Termino (DD/MM/AAAA): ");
        LocalDate termino = LocalDate.parse(scanner.nextLine(), formatter);

        controller.cadastrarProjeto(nome, inicio, termino);
        System.out.println("Projeto criado!");
    }

    private void menuAlocarEquipe() {
        if (controller.getProjetos().isEmpty() || controller.getEquipes().isEmpty()) return;
        
        System.out.println("Selecione o Projeto:");
        for (int i = 0; i < controller.getProjetos().size(); i++) {
            System.out.println(i + " - " + controller.getProjetos().get(i).getNome());
        }
        int pr = lerInteiro();

        System.out.println("Selecione a Equipe:");
        for (int i = 0; i < controller.getEquipes().size(); i++) {
            System.out.println(i + " - " + controller.getEquipes().get(i).getNome());
        }
        int eq = lerInteiro();

        if (controller.alocarEquipeEmProjeto(pr, eq)) {
            System.out.println("Alocacao registrada!");
        }
    }

    private void menuListarGeral() {
        System.out.println("\n--- DADOS ATUAIS ---");
        System.out.println("Projetos:");
        controller.getProjetos().forEach(p -> System.out.println("  - " + p.getNome() + " [" + p.getStatus() + "]"));
        System.out.println("Equipes:");
        controller.getEquipes().forEach(e -> System.out.println("  - " + e.getNome() + " | Membros: " + e.getMembros().size()));
    }

    private int lerInteiro() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.print("Digite um numero inteiro: ");
                scanner.nextLine();
            }
        }
    }
}