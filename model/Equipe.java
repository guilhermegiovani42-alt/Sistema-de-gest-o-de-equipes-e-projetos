package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equipe {
    private String nome;
    private List<Usuario> membros = new ArrayList<>();
    private List<Projeto> projetosAtivos = new ArrayList<>();

    public Equipe(String nome) {
        this.nome = nome;
    }

    public void adicionarMembro(Usuario usuario) {
        if (usuario != null && !membros.contains(usuario)) {
            membros.add(usuario);
        }
    }

    protected void adicionarProjeto(Projeto projeto) {
        if (!projetosAtivos.contains(projeto)) {
            projetosAtivos.add(projeto);
        }
    }

    public String getNome() { return nome; }
    public List<Usuario> getMembros() { return Collections.unmodifiableList(membros); }
    public List<Projeto> getProjetosAtivos() { return Collections.unmodifiableList(projetosAtivos); }
}