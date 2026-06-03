package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Projeto {
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private StatusProjeto status;
    private List<Equipe> equipesVinculadas = new ArrayList<>();

    public Projeto(String nome, LocalDate dataInicio, LocalDate dataTerminoPrevista) {
        if (dataTerminoPrevista.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de término não pode ser anterior à início.");
        }
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = StatusProjeto.PLANEJADO;
    }

    public void vincularEquipe(Equipe equipe) {
        if (equipe != null && !this.equipesVinculadas.contains(equipe)) {
            this.equipesVinculadas.add(equipe);
            equipe.adicionarProjeto(this);
        }
    }

    public String getNome() { 
        return nome; 
    }

    public StatusProjeto getStatus() { 
        return status; 
    }

    public List<Equipe> getEquipesVinculadas() { 
        return Collections.unmodifiableList(equipesVinculadas); 
    }
}