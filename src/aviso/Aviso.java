/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author wanderlei
 */
public class Aviso {

    public static void lerXml() throws JDOMException, IOException {

        File file = null;
        file = new File((new StringBuilder()).append(System.getProperty("user.dir")).append("\\aviso.cfg").toString());

        if (file.exists()) {
            //Aqui você informa o nome do arquivo XML. 
            //File f = new File("c:/mural.xml");
            //Criamos uma classe SAXBuilder que vai processar o XML4  
            SAXBuilder sb = new SAXBuilder();
            //Este documento agora possui toda a estrutura do arquivo.  
            Document d = sb.build(file);
            //Recuperamos o elemento root  
            Element mural = d.getRootElement();
            /*
                System.out.println("Porta:" + mural.getChildText("porta"));
                System.out.println("Servidor:" + mural.getChildText("servidor"));
                System.out.println("Senha:" + mural.getChildText("senha"));
                System.out.println("Banco:" + mural.getChildText("banco"));
             */
            Conexao.local_servidor = mural.getChildText("servidor");
            Conexao.local_porta = mural.getChildText("porta");
            Conexao.local_senha = mural.getChildText("senha");
            Conexao.local_banco = mural.getChildText("banco");
            Conexao.local_user = mural.getChildText("user");

        } else {
            JOptionPane.showMessageDialog(null, "Arquivo 'aviso.cfg' não configurado!\nPor favor configure a conexão!");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaConexao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        try {
            lerXml();
        } catch (JDOMException ex) {
            System.out.println("erro xml");
        } catch (IOException ex) {
            System.out.println("erro arquivo");
        }

        TelaInicial telaic = new TelaInicial();
        telaic.setLocationRelativeTo(null);
        telaic.setVisible(true);
    }
}
