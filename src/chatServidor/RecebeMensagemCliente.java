package chatServidor;


import java.io.*;
import java.net.Socket;

import server.Servidor;

public class RecebeMensagemCliente implements Runnable {

    private Socket socket;
    private Servidor servidor;

    public RecebeMensagemCliente(Socket socket, Servidor servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Aguardando mensagem do cliente...");

            try {
                DataInput dis = new DataInputStream(this.socket.getInputStream());
                String msgRecebida = dis.readUTF();
                this.servidor.enviarMensagensClientes(msgRecebida);
            } catch (EOFException e) {
                System.out.println("Cliente desconectado");
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enviarMensagem(String mensagem) {
        try {
            OutputStream os = this.socket.getOutputStream();
            DataOutput dos = new DataOutputStream(os);
            dos.writeUTF(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
