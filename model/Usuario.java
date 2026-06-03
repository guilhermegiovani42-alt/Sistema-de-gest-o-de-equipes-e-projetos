package model;

public class Usuario {
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;

    public Usuario(String nomeCompleto, String cpf, String email, String cargo) {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNomeCompleto() { return nomeCompleto; }
    public String getCpf() { return cpf; }
    
    @Override
    public String toString() {
        return nomeCompleto + " (" + cargo + ")";
    }
}