package br.com.NexusCLoud.model;
import java.util.Objects;

//modela uma aplicação e sua necessidade de RAM em GB. RA: 25017806-2
public class Container {
    private final String id;
    private final String nomeApp;
    private final double ramNecessaria;

    //mantém os dados fixos após a criação, evitando alterar a RAM de uma aplicação já alocada. RA: 25017806-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = Objects.requireNonNull(id);
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    //expõe os dados sem permitir modificação externa. RA: 25017806-2
    public String getId() { return id; }
    public String getNomeApp() { return nomeApp; }
    public double getRamNecessaria() { return ramNecessaria; }

    //a comparação lógica considera exclusivamente o ID do container. RA: 25017806-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Container)) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    //gera o hash com o mesmo ID utilizado na comparação. RA: 25017806-2
    @Override
    public int hashCode() { return Objects.hash(id); }
}
