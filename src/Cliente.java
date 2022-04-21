
import janela.JanelaGui;
import receptor.RecebeMensagemServidor;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente extends JanelaGui {

    private Socket socket;

    public static void main(String[] args) {
        new Cliente();
    }

    @Override
    protected boolean conectar() {
        System.out.println("Conectando no servidor...");
        try{
            this.socket = new Socket("127.0.0.1", 3333);
            RecebeMensagemServidor recebeMensagemServidor = new RecebeMensagemServidor(this.socket, this);
            new Thread(recebeMensagemServidor).start();

        }catch (Exception e){
            e.printStackTrace();
            //return false;
        }
        return true;
    }

    @Override
    protected void sendMessage(String mensagem) {
        System.out.println("Enviando mensagem via socket para o servidor - " + mensagem);

        try{
            OutputStream saida = this.socket.getOutputStream();
            DataOutput dataOutput = new DataOutputStream(saida);
            dataOutput.writeUTF(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

