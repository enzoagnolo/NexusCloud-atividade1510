package br.com.NexusCLoud.model;
import java.util.Objects;

//representa o cliente; o CPF define sua identidade no mapa. RA: SEU_RA
public class Cliente {
    private final String cpf;
    private String nome;

    //a identidade permanece fixa para preservar o hash usado pelo HashMap. RA: SEU_RA
    public Cliente(String cpf, String nome) {
        this.cpf = Objects.requireNonNull(cpf);
        this.nome = nome;
    }

    //permite consultar os dados e atualizar apenas o nome. RA: SEU_RA
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    //clientes com o mesmo CPF são considerados iguais, mesmo sendo instâncias distintas. RA: SEU_RA
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente)) return false;
        Cliente outro = (Cliente) obj;
        return Objects.equals(cpf, outro.cpf);
    }

    //usam mesmo atributo de equals para manter o contrato de identidade. RA: SEU_RA
    @Override
    public int hashCode() { return Objects.hash(cpf); }
}
