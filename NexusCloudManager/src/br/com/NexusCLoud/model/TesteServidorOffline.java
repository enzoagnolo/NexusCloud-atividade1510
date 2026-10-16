package br.com.NexusCLoud.model;

import br.com.NexusCLoud.exception.CapacidadeExcedidaException;

//pra mostrar a falha na mainCloud RA: 25017806-2

public class TesteServidorOffline {
    //faz a checagem pelo try-catch. RA: 25017806-2
    public static void main(String[] args) throws CapacidadeExcedidaException {
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);
        Container container = new Container("C-01", "Aplicação Web", 10.0);
        servidor.alocarContainer(container);
    }
}
