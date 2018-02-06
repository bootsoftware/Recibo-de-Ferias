/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.model;

import aviso.control.Conexao;
import aviso.control.Proventos;
import aviso.utilitarios.Mensagens;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Wanderlei Rodrigues
 * @email wanderlei.safira@gmail.com
 * @organization Boot Software
 */
public class Gerar_Relatorio_Model {

    private String matricula;
    private String ano;
    private String mes;

    public Gerar_Relatorio_Model() {
    }

    public List getProventos() throws SQLException {

        Conexao.abrirConexao();
     
        Statement stmt = Conexao.con.createStatement();
        String sql_tabela = (new StringBuilder()).append("select\nv.matricula,\nv.valor,\nIIF(v.referencia is null, '-',v.referencia) as referencia,\np.codigo as cod_provento,\np.nome as nome_provento\n\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere v.ano = ")
                .append(getAno()).append(" and v.mes = ")
                .append(getMes()).append(" and v.matricula = ")
                .append(getMatricula())
                .append(" and v.sequencia <> 13 order by p.codigo").toString();

        ResultSet rs_tabela = stmt.executeQuery(sql_tabela);
        List proventos = new ArrayList();
        try {
            for (; rs_tabela.next();) {
                Proventos p = new Proventos();
                
                p.setCod_prov(rs_tabela.getString("cod_provento"));
                p.setNome_prov(rs_tabela.getString("nome_provento"));
                p.setValor(rs_tabela.getString("valor"));
                p.setReferencia(rs_tabela.getString("referencia"));
                proventos.add(p);
            }
        } catch (SQLException ex) {
            Mensagens.mensagem_tela("Erro ao Gerar Relatório", "Erro ao obter eventos.\nFavor entrar em contato com o Suporte!\n" + ex.getMessage(), "Erro");
            stmt.close();
            Conexao.fecharConexao();
        }
        stmt.close();
        Conexao.fecharConexao();
        
        return proventos;
    }

    public void salvarXml(Document documento) throws IOException {
        XMLOutputter xout = new XMLOutputter();
        FileWriter arquivo = new FileWriter(new File((new StringBuilder()).append(System.getProperty("user.dir") + "\\aviso-").append(getMatricula()).append(".xml").toString()));
        xout.output(documento, arquivo);
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
}
