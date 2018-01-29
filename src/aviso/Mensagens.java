package aviso;

import javax.swing.JOptionPane;

public class Mensagens {

    public Mensagens() {
    }

    public static void mensagem_tela(String titulo, String msg, String tipo) {
        if (tipo.equalsIgnoreCase("erro")) {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("").append(msg).append("").toString(), (new StringBuilder()).append("Datta System Informa - ").append(titulo).append("").toString(), 0);
        }
        if (tipo.equalsIgnoreCase("aviso")) {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("").append(msg).append("").toString(), (new StringBuilder()).append("Datta System Informa - ").append(titulo).append("").toString(), 2);
        }
        if (tipo.equalsIgnoreCase("informa\347\343o")) {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("").append(msg).append("").toString(), (new StringBuilder()).append("Datta System Informa").append(titulo).append("").toString(), 1);
        }
    }

    public static void mensagem_sair_sistema() {
        int Confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema?", "Datta System - Pergunta", 0, 3);
        if (Confirmacao == 0) {
            System.exit(0);
        }
    }
}
