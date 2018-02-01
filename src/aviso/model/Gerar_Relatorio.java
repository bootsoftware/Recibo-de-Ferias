package aviso.model;

import aviso.utilitarios.FuncoesUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

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
    /* public void gerar_arquivo_manual() {
        String total_falta = "";
        String matricula = "";
        String nome = "";
        String cnpf = "";
        String nome_cargo = "";
        String nome_empresa = "";
        String cnpj_empresa = "";
        String cidade = "";
        String uf = "";
        String valor_normal_extenso = "";
        String valor_recibo_extenso = "";
        String aquisicao_inicial = "";
        String aquisicao_final = "";
        String gozo_inicial = "";
        String gozo_final = "";
        String retorno = "";
        String valor_recibo = "";
        Date d_emissao = new Date();
        DateFormat formato_normal = new SimpleDateFormat("dd/MM/YYYY");
        DateFormat formato_full = DateFormat.getDateInstance(1);
        d_emissao = txt_data_emissao.getDate();
        String emissao = formato_full.format(d_emissao);
        emissao = FuncoesUtils.limpaCaracteres(emissao.toUpperCase());
        aquisicao_inicial = data_aquisicao_inicial.getText();
        aquisicao_final = data_aquisicao_final.getText();
        gozo_inicial = data_gozo_inicial.getText();
        gozo_final = data_gozo_final.getText();
        retorno = txtRetorno.getText();
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar = (new StringBuilder()).append("select\nf.codigo as matricula,\nf.nome,\nf.cnpf,\nc.nome as nome_cargo\nfrom funcionario f\njoin cargo c on c.codigo = f.codigo_cargo\nwhere f.codigo = ").append(txt_matricula.getText()).append("").toString();
            for (ResultSet rs = stmt.executeQuery(sql_pesquisar); rs.next();) {
                nome = rs.getString("nome");
                matricula = rs.getString("matricula");
                cnpf = rs.getString("cnpf");
                nome_cargo = rs.getString("nome_cargo");
            }
            cnpf = FuncoesUtils.format("###.###.###-##", cnpf);
            String sql_pesquisar_empresa = " select e.nome, e.cnpj, e.cidade, e.uf from empresa  e";
            for (ResultSet rs_empresa = stmt.executeQuery(sql_pesquisar_empresa); rs_empresa.next();) {
                nome_empresa = rs_empresa.getString("nome");
                cnpj_empresa = rs_empresa.getString("cnpj");
                cidade = rs_empresa.getString("cidade");
                uf = rs_empresa.getString("uf");
            }

            cnpj_empresa = FuncoesUtils.format("##.###.###/####-##", cnpj_empresa);
            String sql_pesquisar_valor_recibo = (new StringBuilder()).append("select\nv.valor\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere ((v.provento = 18 or v.provento = 47) and v.ano = ").append(txt_ano.getText()).append(" and v.mes = ").append(txt_mes.getText()).append(" and v.matricula = ").append(txt_matricula.getText()).append(")").toString();
            for (ResultSet rs_recibo = stmt.executeQuery(sql_pesquisar_valor_recibo);
                    rs_recibo.next();) {
                valor_recibo = rs_recibo.getString("valor");
            }
            if (!(valor_recibo.isEmpty())) {

                Extenso entenso_valor_recibo = new Extenso(Double.parseDouble(valor_recibo));
                valor_recibo_extenso = entenso_valor_recibo.toString();
                Element aviso = new Element("Aviso_Ferias");
                Document documento = new Document(aviso);
                Element servidor = new Element("Servidor");
                Element nome_servidor = new Element("nome_servidor");
                nome_servidor.setText(nome);
                Element matricula_servidor = new Element("matricula");
                matricula_servidor.setText(matricula);
                Element total_faltas = new Element("total_faltas");
                if (txt_Faltas.getText().equals("")) {
                    total_faltas.setText("0.00");
                } else {
                    total_faltas.setText(txt_Faltas.getText());
                }
                Element cpf_servidor = new Element("cnpf");
                cpf_servidor.setText(cnpf);
                Element cargo = new Element("nome_cargo");
                cargo.setText(nome_cargo);
                Element data_emissao_xml = new Element("data_emissao");
                data_emissao_xml.setText(emissao);
                Element data_aquisicao_inicial_xml = new Element("aquisicao_inicial");
                data_aquisicao_inicial_xml.setText(aquisicao_inicial);
                Element data_aquisicao_final_xml = new Element("aquisicao_final");
                data_aquisicao_final_xml.setText(aquisicao_final);
                Element data_gozo_inicial_xml = new Element("gozo_inicial");
                data_gozo_inicial_xml.setText(gozo_inicial);
                Element data_gozo_final_xml = new Element("gozo_final");
                data_gozo_final_xml.setText(gozo_final);
                Element data_retorno_xml = new Element("data_retorno");
                data_retorno_xml.setText(retorno);
                Element valor_recibo_extenso_xml = new Element("valor_recibo_extenso");
                valor_recibo_extenso_xml.setText(valor_recibo_extenso.toUpperCase());
                Element valor_recibo_xml = new Element("valor_recibo");
                valor_recibo_xml.setText(valor_recibo);
                Element nome_empresa_xml = new Element("nome_empresa");
                nome_empresa_xml.setText("PREFEITURA MUNICIPAL DE AUGUSTINOPOLIS");
                Element cnpj_empresa_xml = new Element("cnpj_empresa");
                cnpj_empresa_xml.setText(cnpj_empresa);
                Element cidade_xml = new Element("cidade");
                cidade_xml.setText("AUGUSTINOPOLIS");
                Element uf_xml = new Element("uf");
                uf_xml.setText(uf);
                String sql_tabela = (new StringBuilder()).append("select\nv.matricula,\nv.valor,\nIIF(v.referencia is null, '-',v.referencia) as referencia,\np.codigo as cod_provento,\np.nome as nome_provento\n\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere v.ano = ").append(txt_ano.getText()).append(" and v.mes = ").append(txt_mes.getText()).append(" and v.matricula = ").append(matricula).append(" and v.sequencia <> 13 order by p.codigo").toString();
                ResultSet rs_tabela = stmt.executeQuery(sql_tabela);
                Element proventos = new Element("Proventos");
                int cod_prov = 0;
                double des = 0.0D;
                double pro = 0.0D;
                double liq = 0.0D;
                Element provento;
                for (; rs_tabela.next(); proventos.addContent(provento)) {
                    provento = new Element("provento");
                    cod_prov = Integer.parseInt(rs_tabela.getString("cod_provento"));
                    provento.setAttribute("codigo", String.valueOf(cod_prov));
                    provento.setAttribute("nome", rs_tabela.getString("nome_provento"));
                    provento.setAttribute("ref", rs_tabela.getString("referencia"));
                    if (cod_prov > 500) {
                        des += Double.parseDouble(rs_tabela.getString("valor"));
                        provento.setAttribute("descontos", rs_tabela.getString("valor"));
                    } else {
                        pro += Double.parseDouble(rs_tabela.getString("valor"));
                        provento.setAttribute("redimento", rs_tabela.getString("valor"));
                    }
                }

                liq = pro - des;
                Element total_descontos_xml = new Element("total_descontos");
                total_descontos_xml.setText(String.valueOf(des));
                Element total_provento_xml = new Element("total_porvento");
                total_provento_xml.setText(String.valueOf(pro));
                Element total_liquido_xml = new Element("total_liquido");
                total_liquido_xml.setText(String.valueOf(liq));
                stmt.close();
                Conexao.fecharConexao();
                servidor.addContent(nome_servidor);
                servidor.addContent(matricula_servidor);
                servidor.addContent(cpf_servidor);
                servidor.addContent(cargo);
                servidor.addContent(data_emissao_xml);
                servidor.addContent(data_aquisicao_inicial_xml);
                servidor.addContent(data_aquisicao_final_xml);
                servidor.addContent(data_gozo_inicial_xml);
                servidor.addContent(data_gozo_final_xml);
                servidor.addContent(data_retorno_xml);
                servidor.addContent(valor_recibo_extenso_xml);
                servidor.addContent(valor_recibo_xml);
                servidor.addContent(nome_empresa_xml);
                servidor.addContent(cnpj_empresa_xml);
                servidor.addContent(cidade_xml);
                servidor.addContent(uf_xml);
                servidor.addContent(total_provento_xml);
                servidor.addContent(total_descontos_xml);
                servidor.addContent(total_liquido_xml);
                servidor.addContent(total_faltas);
                servidor.addContent(proventos);
                aviso.addContent(servidor);
                XMLOutputter xout = new XMLOutputter();
                FileWriter arquivo = new FileWriter(new File((new StringBuilder()).append(System.getProperty("user.dir") + "\\aviso-").append(matricula).append(".xml").toString()));
                xout.output(documento, arquivo);
            } else {
                JOptionPane.showMessageDialog(null, "Erro!\nConfigure as remunerações de férias!");
                return;
            }

        } catch (SQLException ex) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Gerar_Relatorio.gerar_aviso(txt_matricula.getText());
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(aviso / tela.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(rootPane, ex);
        } catch (JRException ex) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        }
*/
}
