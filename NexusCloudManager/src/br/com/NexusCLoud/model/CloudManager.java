package br.com.NexusCLoud.model;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//associa cada cliente a um conjunto de servidores sem duplicação ou ordem garantida. RA: SEU_RA
public class CloudManager {
    private final Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    //o mapa é inicializado diretamente no atributo. RA: SEU_RA
    public CloudManager() { }

    //registra sem apagar os servidores caso o CPF já esteja cadastrado. RA: SEU_RA
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    //exige um cliente previamente registrado e usa o HashSet para evitar IPs repetidos. RA: SEU_RA
    public void provisionarServidor(Cliente c, Servidor s) {
        Set<Servidor> servidores = bancoDeDados.get(c);
        if (servidores == null) {
            throw new IllegalArgumentException("Cliente não cadastrado.");
        }
        servidores.add(s);
    }

    //considera apenas servidores ligados; um cliente ausente possui fatura zero. RA: SEU_RA
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, Collections.emptySet())
            .stream()
            .filter(Servidor::isStatusLigado)
            .mapToDouble(Servidor::getPrecoPorHora)
            .sum();
    }
}
