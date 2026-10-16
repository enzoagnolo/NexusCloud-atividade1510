package br.com.NexusCLoud.exception;
//tenta alocar com o servidor desligado. RA: 25017806-2
public class ServidorOfflineException extends RuntimeException {
    //guarda a mensagem no console. RA: 25017806-2
    public ServidorOfflineException(String msg) { super(msg); }
}
