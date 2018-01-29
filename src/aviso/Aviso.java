/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso;

/**
 *
 * @author wanderlei
 */
public class Aviso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Conexao.local_servidor = "localhost";
        Conexao.local_porta = "3052";
        //Conexao.local_banco = System.getProperty("user.dir")+"BDSTFOLHA.GDB";
        Conexao.local_banco = "C:\\DattaSystem\\STFolha\\BDSTFOLHA.GDB";

        TelaInicial telaic = new TelaInicial();
        //telaic.setLocationRelativeTo(null);
        telaic.setVisible(true);
    }

    // Conexao.abrirConexao();
}

