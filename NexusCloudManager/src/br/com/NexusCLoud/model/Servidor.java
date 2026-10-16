package br.com.NexusCLoud.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import br.com.NexusCLoud.exception.CapacidadeExcedidaException;
import br.com.NexusCLoud.exception.ServidorOfflineException;

//faz o gerenciamento do servidor. RA: 25017806-2
public class Servidor {
    private final String ip;
    private double precoPorHora;
    private final double ramTotal;
    private boolean statusLigado;
    private final List<Container> containers = new ArrayList<>();

    //aqui o servidor começa desligado. RA: 25017806-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = Objects.requireNonNull(ip);
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    //consulta e traz uma visão da lista que não deixa validar a alocação. RA: 25017806-2
    public String getIp() { return ip; }
    public double getPrecoPorHora() { return precoPorHora; }
    public void setPrecoPorHora(double precoPorHora) { this.precoPorHora = precoPorHora; }
    public double getRamTotal() { return ramTotal; }
    public boolean isStatusLigado() { return statusLigado; }
    public List<Container> getContainers() { return Collections.unmodifiableList(containers); }

    //controla as mudanças de estado do servidor. RA: 25017806-2
    public void ligar() { statusLigado = true; }
    public void desligar() { statusLigado = false; }

    //faz a validação e só adiciona se aa validação for aprovada. RA: 25017806-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        if (!statusLigado) {
            throw new ServidorOfflineException(
                "Erro: Não é possível alocar containers em um servidor desligado.");
        }
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                "Falha Crítica: Memória RAM insuficiente no servidor [" + ip + "].");
        }
        containers.add(c);
    }

    //converte os containers em valores double e soma a RAM, sem laços ou if/else. RA: 25017806-2
    public double calcularRamEmUso() {
        return containers.stream().mapToDouble(Container::getRamNecessaria).sum();
    }

    //impede duplicação dentro do HashSet. RA: 25017806-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Servidor)) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    //vai preservar a localização do servidor. RA: 25017806-2
    @Override
    public int hashCode() { return Objects.hash(ip); }
}
