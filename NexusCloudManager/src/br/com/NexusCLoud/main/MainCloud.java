package br.com.NexusCLoud.main;
import java.util.Locale;

import br.com.NexusCLoud.exception.CapacidadeExcedidaException;
import br.com.NexusCLoud.model.Cliente;
import br.com.NexusCLoud.model.CloudManager;
import br.com.NexusCLoud.model.Container;
import br.com.NexusCLoud.model.Servidor;

//executa o roteiro da avaliação após comentar o teste unchecked, conforme o passo 7. RA: 25017806-2
public class MainCloud {
    //a exceção checked da primeira alocação também precisa ser tratada pelo compilador. RA: 25017806-2
    public static void main(String[] args) {
        CloudManager manager = new CloudManager();
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);
        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        //cria aplicações caso a soma ultrapassa os 16 GB disponíveis. RA: 25017806-2
        Container container1 = new Container("C-01", "Aplicação Web", 10.0);
        Container container2 = new Container("C-02", "Banco de Dados", 8.0);

        //passos 6 e 7: teste unchecked já foi comentado e executa o TesteServidorOffline para observá-lo sem captura. RA: 25017806-2
        // servidor.alocarContainer(container1); // RA: 25017806-2
        servidor.ligar();

        // Passo 8: alocou 10 GB no servidor ligado e trata uma falha checked. RA: 25017806-2
        try {
            servidor.alocarContainer(container1);
            System.out.println("Container C-01 alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Passo 9: tenta atingir 18 GB e falha antes de modificar a lista. RA: 25017806-2
        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        // Passo 10: o único servidor ligado custa 5.50 por hora. RA: 25017806-2
        System.out.printf(Locale.US, "Fatura ativa: R$ %.2f%n", manager.calcularFaturaAtiva(cliente));
    }
}
