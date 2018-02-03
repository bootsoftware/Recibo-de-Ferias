/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.model;

import aviso.control.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wanderlei Rodrigues
 * @email wanderlei.safira@gmail.com
 * @organization Boot Software
 */
public class Servidor_Model {

    private String matricula;
    private String nome;
    private String cnpf;
    private String nome_cargo;
    private String valor_recibo;
    private String mes_recibo;
    private String ano_recibo;

    public Servidor_Model() {

    }

    @SuppressWarnings("empty-statement")
    public void ServidorBuscarNome(String matricula) {

        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar = (new StringBuilder()).append("select F.nome from FUNCIONARIO F where F.CODIGO = ")
                    .append(matricula)
                    .append("").toString();
            for (ResultSet rs = stmt.executeQuery(sql_pesquisar); rs.next();
                    this.setNome(rs.getString("nome")));
            Conexao.fecharConexao();

        } catch (SQLException exception) {

        }
    }

    void servidorDados() throws SQLException {
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar = (new StringBuilder()).append("select\nf.codigo as matricula,\nf.nome,\nf.cnpf,\nc.nome as nome_cargo\nfrom funcionario f\njoin cargo c on c.codigo = f.codigo_cargo\nwhere f.codigo = ")
                    .append(getMatricula()).append("").toString();
            for (ResultSet rs = stmt.executeQuery(sql_pesquisar);
                    rs.next();) {
                setNome(rs.getString("nome"));
                setMatricula(rs.getString("matricula"));
                setCnpf(rs.getString("cnpf"));
                setNome_cargo(rs.getString("nome_cargo"));
                Conexao.fecharConexao();
            }
        } catch (SQLException exception) {

        }
    }

    void servidorValorFerias() {
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar_valor_recibo = (new StringBuilder()).append("select\nv.valor\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere ((v.provento = 18 or v.provento = 47) and v.ano = ")
                    .append(getAno_recibo())
                    .append(" and v.mes = ")
                    .append(getMes_recibo())
                    .append(" and v.matricula = ")
                    .append(getMatricula())
                    .append(")").toString();
            for (ResultSet rs_recibo = stmt.executeQuery(sql_pesquisar_valor_recibo);
                    rs_recibo.next();) {
                setValor_recibo(rs_recibo.getString("valor"));
                Conexao.fecharConexao();
            }
        } catch (SQLException exception) {

        }
    }

   /* void servidorBuscaVariavel() {
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_tabela = (new StringBuilder()).append("select\nv.matricula,\nv.valor,\nIIF(v.referencia is null, '-',v.referencia) as referencia,\np.codigo as cod_provento,\np.nome as nome_provento\n\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere v.ano = ")
                    .append(getAno_recibo())
                    .append(" and v.mes = ")
                    .append(getMes_recibo())
                    .append(" and v.matricula = ")
                    .append(matricula)
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
        } catch (SQLException exception) {

        }
    }
    */

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
     * @return the mes_recibo
     */
    public String getMes_recibo() {
        return mes_recibo;
    }

    /**
     * @param mes_recibo the mes_recibo to set
     */
    public void setMes_recibo(String mes_recibo) {
        this.mes_recibo = mes_recibo;
    }

    /**
     * @return the ano_recibo
     */
    public String getAno_recibo() {
        return ano_recibo;
    }

    /**
     * @param ano_recibo the ano_recibo to set
     */
    public void setAno_recibo(String ano_recibo) {
        this.ano_recibo = ano_recibo;
    }

}
