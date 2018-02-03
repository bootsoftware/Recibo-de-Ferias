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
public class Empresa_Model {

    private String Nome_empresa;
    private String Cnpj_empresa;
    private String Cidade;
    private String Uf;

    Empresa_Model() {

    }

    void empresaDados() {
        
        try {
            Conexao.abrirConexao();
            Statement stmt = Conexao.con.createStatement();
            String sql_pesquisar_empresa = " select e.nome, e.cnpj, e.cidade, e.uf from empresa  e";
            for (ResultSet rs_empresa = stmt.executeQuery(sql_pesquisar_empresa); rs_empresa.next();) {
                setNome_empresa(rs_empresa.getString("nome"));
                setCnpj_empresa(rs_empresa.getString("cnpj"));
                setCidade(rs_empresa.getString("cidade"));
                setUf(rs_empresa.getString("uf"));
                Conexao.fecharConexao();
            }
        } catch (SQLException exception) {

        }
    }

    /**
     * @return the Nome_empresa
     */
    public String getNome_empresa() {
        return Nome_empresa;
    }

    /**
     * @param Nome_empresa the Nome_empresa to set
     */
    public void setNome_empresa(String Nome_empresa) {
        this.Nome_empresa = Nome_empresa;
    }

    /**
     * @return the Cnpj_empresa
     */
    public String getCnpj_empresa() {
        return Cnpj_empresa;
    }

    /**
     * @param Cnpj_empresa the Cnpj_empresa to set
     */
    public void setCnpj_empresa(String Cnpj_empresa) {
        this.Cnpj_empresa = Cnpj_empresa;
    }

    /**
     * @return the Cidade
     */
    public String getCidade() {
        return Cidade;
    }

    /**
     * @param Cidade the Cidade to set
     */
    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    /**
     * @return the Uf
     */
    public String getUf() {
        return Uf;
    }

    /**
     * @param Uf the Uf to set
     */
    public void setUf(String Uf) {
        this.Uf = Uf;
    }

}
