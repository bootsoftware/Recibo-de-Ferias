package aviso.control;

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

    private String total_falta;
    private String matricula;
    private String nome;
    private String cnpf;
    private String nome_cargo;
    private String nome_empresa;
    private String cnpj_empresa;
    private String cidade;
    private String emisao;
    private String uf;
    private String valor_normal_extenso;
    private String valor_recibo_extenso;
    private String ano;
    private String mes;
    private String aquisicao_inicial;
    private String aquisicao_final;
    private String gozo_inicial;
    private String gozo_final;
    private String Faltas;
    private String retorno;
    private String valor_recibo;
    private String emissao;

    public Gerar_Relatorio() {
    }

    public static void gerar_aviso(String matricula)
            throws FileNotFoundException, JRException {
        try {
            net.sf.jasperreports.engine.JasperReport jasperReport = null;
            String recordPath = "/Aviso_Ferias/Servidor";
            String xmlFileName = (new StringBuilder()).append(System.getProperty("user.dir") + "\\aviso-")
                    .append(matricula).append(".xml").toString();
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

    public void gerar_arquivo_manual() {

        //  Date d_emissao = new Date();
        // DateFormat formato_normal = new SimpleDateFormat("dd/MM/YYYY");
        // DateFormat formato_full = DateFormat.getDateInstance(1);
        //  d_emissao = txt_data_emissao.getDate();
        //   setEmissao(formato_full.format(d_emissao));
        //  setEmissao(FuncoesUtils.limpaCaracteres(getEmissao().toUpperCase()));
        // aquisicao_inicial = data_aquisicao_inicial.getText();
        // aquisicao_final = data_aquisicao_final.getText();
        //  gozo_inicial = data_gozo_inicial.getText();
        //  gozo_final = data_gozo_final.getText();
        //  retorno = txtRetorno.getText();
        
        setEmisao("Definir como vou fazer!");
        try {

            Extenso entenso_valor_recibo = new Extenso(Double.parseDouble(getValor_recibo()));
            setValor_recibo_extenso(entenso_valor_recibo.toString());
            Element aviso = new Element("Aviso_Ferias");
            Document documento = new Document(aviso);
            Element servidor = new Element("Servidor");
            Element nome_servidor = new Element(getNome());
            nome_servidor.setText(getNome());
            Element matricula_servidor = new Element(getMatricula());
            matricula_servidor.setText(getMatricula());
            Element total_faltas = new Element("total_faltas");
            total_faltas.setText(getFaltas());
            Element cpf_servidor = new Element("cnpf");
            cpf_servidor.setText(getCnpf());
            Element cargo = new Element("nome_cargo");
            cargo.setText(getNome_cargo());
            Element data_emissao_xml = new Element("data_emissao");
            data_emissao_xml.setText(getEmissao());
            Element data_aquisicao_inicial_xml = new Element("aquisicao_inicial");
            data_aquisicao_inicial_xml.setText(getAquisicao_inicial());
            Element data_aquisicao_final_xml = new Element("aquisicao_final");
            data_aquisicao_final_xml.setText(getAquisicao_final());
            Element data_gozo_inicial_xml = new Element("gozo_inicial");
            data_gozo_inicial_xml.setText(getGozo_inicial());
            Element data_gozo_final_xml = new Element("gozo_final");
            data_gozo_final_xml.setText(getGozo_final());
            Element data_retorno_xml = new Element("data_retorno");
            data_retorno_xml.setText(getRetorno());
            Element valor_recibo_extenso_xml = new Element("valor_recibo_extenso");
            valor_recibo_extenso_xml.setText(getValor_recibo_extenso().toUpperCase());
            Element valor_recibo_xml = new Element("valor_recibo");
            valor_recibo_xml.setText(getValor_recibo());
            Element nome_empresa_xml = new Element("nome_empresa");
            nome_empresa_xml.setText(getNome_empresa());
            Element cnpj_empresa_xml = new Element("cnpj_empresa");
            cnpj_empresa_xml.setText(getCnpj_empresa());
            Element cidade_xml = new Element("cidade");
            cidade_xml.setText(getCidade());
            Element uf_xml = new Element("uf");
            uf_xml.setText(getUf());

            /*  String sql_tabela = (new StringBuilder()).append("select\nv.matricula,\nv.valor,\nIIF(v.referencia is null, '-',v.referencia) as referencia,\np.codigo as cod_provento,\np.nome as nome_provento\n\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere v.ano = ")
                    .append(getAno()).append(" and v.mes = ")
                    .append(getMes()).append(" and v.matricula = ")
                    .append(getMatricula())
                    .append(" and v.sequencia <> 13 order by p.codigo").toString();

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
                provento.setAttribute("codigo", St   provento = new Element("provento");
                cod_prov = Integer.parseInt(rs_tabela.getStriring.valueOf(cod_prov));
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
             */
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
            FileWriter arquivo = new FileWriter(new File((new StringBuilder()).append(System.getProperty("user.dir") + "\\aviso-").append(getMatricula()).append(".xml").toString()));
            xout.output(documento, arquivo);
        }else {
                JOptionPane.showMessageDialog(null, "Erro!\nConfigure as remunerações de férias!");
                return;
            }

    }
    catch (SQLException ex

    
    
    ) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex

    
    

    
        ) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Gerar_Relatorio.gerar_aviso(getMatricula());
    }
    catch (FileNotFoundException ex

    
    
    ) {
            //Logger.getLogger(aviso / tela.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(rootPane, ex);
        } catch (JRException ex

    
    

    ) {
            //  Logger.getLogger(avisoferias / tela.getName()).log(Level.SEVERE, null, ex);
        }

    /**
     * @return the total_falta
     */
    public String getTotal_falta() {
        return total_falta;
    }

    /**
     * @param total_falta the total_falta to set
     */
    public void setTotal_falta(String total_falta) {
        this.total_falta = total_falta;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cnpf
     */
    public String getCnpf() {
        return cnpf;
    }

    /**
     * @param cnpf the cnpf to set
     */
    public void setCnpf(String cnpf) {
        this.cnpf = cnpf;
    }

    /**
     * @return the nome_cargo
     */
    public String getNome_cargo() {
        return nome_cargo;
    }

    /**
     * @param nome_cargo the nome_cargo to set
     */
    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }

    /**
     * @return the nome_empresa
     */
    public String getNome_empresa() {
        return nome_empresa;
    }

    /**
     * @param nome_empresa the nome_empresa to set
     */
    public void setNome_empresa(String nome_empresa) {
        this.nome_empresa = nome_empresa;
    }

    /**
     * @return the cnpj_empresa
     */
    public String getCnpj_empresa() {
        return cnpj_empresa;
    }

    /**
     * @param cnpj_empresa the cnpj_empresa to set
     */
    public void setCnpj_empresa(String cnpj_empresa) {
        this.cnpj_empresa = cnpj_empresa;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the valor_normal_extenso
     */
    public String getValor_normal_extenso() {
        return valor_normal_extenso;
    }

    /**
     * @param valor_normal_extenso the valor_normal_extenso to set
     */
    public void setValor_normal_extenso(String valor_normal_extenso) {
        this.valor_normal_extenso = valor_normal_extenso;
    }

    /**
     * @return the valor_recibo_extenso
     */
    public String getValor_recibo_extenso() {
        return valor_recibo_extenso;
    }

    /**
     * @param valor_recibo_extenso the valor_recibo_extenso to set
     */
    public void setValor_recibo_extenso(String valor_recibo_extenso) {
        this.valor_recibo_extenso = valor_recibo_extenso;
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the aquisicao_inicial
     */
    public String getAquisicao_inicial() {
        return aquisicao_inicial;
    }

    /**
     * @param aquisicao_inicial the aquisicao_inicial to set
     */
    public void setAquisicao_inicial(String aquisicao_inicial) {
        this.aquisicao_inicial = aquisicao_inicial;
    }

    /**
     * @return the aquisicao_final
     */
    public String getAquisicao_final() {
        return aquisicao_final;
    }

    /**
     * @param aquisicao_final the aquisicao_final to set
     */
    public void setAquisicao_final(String aquisicao_final) {
        this.aquisicao_final = aquisicao_final;
    }

    /**
     * @return the gozo_inicial
     */
    public String getGozo_inicial() {
        return gozo_inicial;
    }

    /**
     * @param gozo_inicial the gozo_inicial to set
     */
    public void setGozo_inicial(String gozo_inicial) {
        this.gozo_inicial = gozo_inicial;
    }

    /**
     * @return the gozo_final
     */
    public String getGozo_final() {
        return gozo_final;
    }

    /**
     * @param gozo_final the gozo_final to set
     */
    public void setGozo_final(String gozo_final) {
        this.gozo_final = gozo_final;
    }

    /**
     * @return the Faltas
     */
    public String getFaltas() {
        return Faltas;
    }

    /**
     * @param Faltas the Faltas to set
     */
    public void setFaltas(String Faltas) {
        this.Faltas = Faltas;
    }

    /**
     * @return the retorno
     */
    public String getRetorno() {
        return retorno;
    }

    /**
     * @param retorno the retorno to set
     */
    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    /**
     * @return the valor_recibo
     */
    public String getValor_recibo() {
        return valor_recibo;
    }

    /**
     * @param valor_recibo the valor_recibo to set
     */
    public void setValor_recibo(String valor_recibo) {
        this.valor_recibo = valor_recibo;
    }

    /**
     * @return the emissao
     */
    public String getEmissao() {
        return emissao;
    }

    /**
     * @return the emisao
     */
    public String getEmisao() {
        return emisao;
    }

    /**
     * @param emisao the emisao to set
     */
    public void setEmisao(String emisao) {
        this.emisao = emisao;
    }

}
