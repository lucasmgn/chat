package receptor;

import janela.JanelaGui;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

//Uma thread
public class RecebeMensagemServidor implements Runnable {

    private Socket socket;
    private JanelaGui janela;

    public RecebeMensagemServidor(Socket socket, JanelaGui janela) {
        this.socket = socket;
        this.janela = janela;
    }

    @Override
    public void run() {
        while (true) {
            try {
                InputStream entrada = this.socket.getInputStream();
                DataInput dataInput = new DataInputStream(entrada);
                String msgRecebida = dataInput.readUTF();

                janela.adicionaMensagem(msgRecebida);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
