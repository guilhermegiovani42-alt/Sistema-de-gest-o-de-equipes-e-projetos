package controller;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetoController {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Equipe> equipes = new ArrayList<>();
    private List<Projeto> projetos = new ArrayList<>();

    public void cadastrarUsuario(String nome, String cpf, String email, String cargo, Perfil perfil) {
        usuarios.add(new Usuario(nome, cpf, email, cargo)); // Adaptado para a entidade
    }

    public void cadastrarEquipe(String nome) {
        equipes.add(new Equipe(nome));
    }

    public void cadastrarProjeto(String nome, LocalDate inicio, LocalDate termino) {
        projetos.add(new Projeto(nome, inicio, termino));
    }

    public boolean vincularMembroAEquipe(int equipeIdx, int usuarioIdx) {
        if (equipeIdx >= 0 && equipeIdx < equipes.size() && usuarioIdx >= 0 && usuarioIdx < usuarios.size()) {
            equipes.get(equipeIdx).adicionarMembro(usuarios.get(usuarioIdx));
            return true;
        }
        return false;
    }

    public boolean alocarEquipeEmProjeto(int projetoIdx, int equipeIdx) {
        if (projetoIdx >= 0 && projetoIdx < projetos.size() && equipeIdx >= 0 && equipeIdx < equipes.size()) {
            projetos.get(projetoIdx).vincularEquipe(equipes.get(equipeIdx));
            return true;
        }
        return false;
    }

    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Equipe> getEquipes() { return equipes; }
    public List<Projeto> getProjetos() { return projetos; }
}