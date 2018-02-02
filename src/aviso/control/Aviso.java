/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.control;

import aviso.utilitarios.FuncoesUtils;
import aviso.view.TelaConexao;
import aviso.view.TelaInicial;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author wanderlei
 */
public class Aviso {

    public static void lerXml() throws JDOMException, IOException {

        // File file = null;
        File file = new File((new StringBuilder()).append(System.getProperty("user.dir")).append("\\aviso.cfg").toString());

        if (file.exists()) {
            SAXBuilder sb = new SAXBuilder();
            Document d = sb.build(file);
            Element mural = d.getRootElement();

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
        
        DateTime date = FuncoesUtils.dataSeparada("01/03/2013");
        
        
        System.out.println(" Ano " +date.getYear()+" mes " + date.getMonthOfYear());

    }
}
