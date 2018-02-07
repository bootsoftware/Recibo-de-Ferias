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
    private String cnpf;
    private String nome_cargo;

    public Servidor_Model() {

    }

    @SuppressWarnings("empty-statement")
    public String servidorBuscarNome() {
        String nome = "";
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar = (new StringBuilder()).append("select F.nome from FUNCIONARIO F where F.CODIGO = ")
                    .append(getMatricula())
                    .append("").toString();
            for (ResultSet rs = stmt.executeQuery(sql_pesquisar); rs.next();
                    nome = rs.getString("nome"));
            Conexao.fecharConexao();

        } catch (SQLException exception) {
            Conexao.fecharConexao();

        }
        return nome;
    }

    public String servidorBuscarFaltas(String mes_inicio, String data_ano_inicio, String mes_fim, String data_ano_fim) {
        Conexao.abrirConexao();
        Statement stmt;
        String faltas = "0.00";
        try {
            stmt = Conexao.con.createStatement();
            String sql_pesquisar_falta = (new StringBuilder())
                    .append("select SUM(V.referencia)as falta FROM variavel V WHERE V.provento = 501 AND ((ANO >= ")
                    .append(data_ano_inicio)
                    .append(" AND MES >=")
                    .append(mes_inicio)
                    .append(") AND (ANO <= ")
                    .append(data_ano_fim)
                    .append(" AND MES <=")
                    .append(mes_fim)
                    .append(")) AND  v.matricula =")
                    .append(this.getMatricula()).toString();
            for (ResultSet rs_falta = stmt.executeQuery(sql_pesquisar_falta);
                    rs_falta.next();) {
                faltas = rs_falta.getString("falta");

            }
        } catch (SQLException ex) {
            Conexao.fecharConexao();

        }
        return faltas;
    }

    public void servidorBuscarDados() {
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar = (new StringBuilder()).append("select\nf.cnpf,\nc.nome as nome_cargo\nfrom funcionario f\njoin cargo c on c.codigo = f.codigo_cargo\nwhere f.codigo = ")
                    .append(this.getMatricula()).append("").toString();
            for (ResultSet rs = stmt.executeQuery(sql_pesquisar);
                    rs.next();) {
                setCnpf(rs.getString("cnpf"));
                setNome_cargo(rs.getString("nome_cargo"));
                Conexao.fecharConexao();
            }
        } catch (SQLException exception) {

        }
    }

    public String servidorValorFerias(String mes_recibo, String ano_recibo) {
        String valor_recibo = "0";
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar_valor_recibo = (new StringBuilder()).append("select\nv.valor\nfrom variavel v\njoin provento p on p.codigo = v.provento\nwhere ((v.provento = 18 or v.provento = 47) and v.ano = ")
                    .append(ano_recibo)
                    .append(" and v.mes = ")
                    .append(mes_recibo)
                    .append(" and v.matricula = ")
                    .append(getMatricula())
                    .append(")").toString();
            for (ResultSet rs_recibo = stmt.executeQuery(sql_pesquisar_valor_recibo);
                    rs_recibo.next();) {
                valor_recibo = rs_recibo.getString("valor");
            }
        } catch (SQLException exception) {
            Conexao.fecharConexao();
            return valor_recibo;
        }
        Conexao.fecharConexao();
        return valor_recibo;
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

}
