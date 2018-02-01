package aviso.model;

import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Gerar_Relatorio {

    public Gerar_Relatorio() {
    }

    public static void gerar_aviso(String matricula)
            throws FileNotFoundException, JRException {
        try {
            net.sf.jasperreports.engine.JasperReport jasperReport = null;
            String recordPath = "/Aviso_Ferias/Servidor";
            String xmlFileName = (new StringBuilder()).append(System.getProperty("user.dir")+"\\aviso-").append(matricula).append(".xml").toString();
            String path = System.getProperty("user.dir");
            net.sf.jasperreports.engine.JasperPrint jasperPrint = null;
            String templateName = (new StringBuilder()).append(path).append("\\avisoferias.jrxml").toString();
            jasperReport = JasperCompileManager.compileReport(templateName);
            HashMap parameters = new HashMap();
            JRXmlDataSource jrxmlds = new JRXmlDataSource(xmlFileName, recordPath);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrxmlds);
            JasperViewer jrv = new JasperViewer(jasperPrint, false);
            jrv.setVisible(true);
            jrv.setLocationRelativeTo(null);
            jrv.setTitle("Datta System Tecnologia - Imprimir Aviso de F\351rias");
            jrv.setIconImage(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            
            
        }
    }
}
