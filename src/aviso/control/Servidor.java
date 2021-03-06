/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.control;

import aviso.model.Empresa_Model;
import aviso.model.Servidor_Model;
import aviso.utilitarios.Mensagens;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author wanderlei
 */
public class Servidor {

    private String matricula;
    private String nome;
    private String cnpf;
    private String nome_cargo;
    private String valor_recibo;
    private String mes_recibo;
    private String ano_recibo;
    private String data_aquisicao_inicial;
    private String data_aquisicao_final;
    private String data_gozo_inicial;
    private String data_gozo_final;
    private String data_retorno;
    private String data_emisao;

    private String faltas;
    private Empresa_Model empresa = new Empresa_Model();

    public String teste;

    public Servidor() {
    }

    public Servidor(String matricula) {
        this.matricula = matricula;
    }

    public void lerDadosSercidor() {

        System.out.println("Matricula " + getMatricula());
        System.out.println("Nome " + getNome());
        System.out.println("Cpf " + getCnpf());
        System.out.println("Cargo " + getNome_cargo());
        System.out.println("Valor " + getValor_recibo());
        System.out.println("Mes " + getMes_recibo());
        System.out.println("Ano " + getAno_recibo());
        System.out.println("dataInicial " + getData_aquisicao_inicial());
        System.out.println("DataFinal " + getData_aquisicao_final());
        System.out.println("Faltas " + getFaltas());

        /* System.out.println(get);*/
    }

    public void buscarNome() {
        try {
            Servidor_Model model = new Servidor_Model();
            model.setMatricula(getMatricula());
            setNome(model.servidorBuscarNome());
        } catch (Exception e) {
            Mensagens.mensagem_tela("Erro ao consutar Nome!", "Não foi possível fazer a consulta!\n " + e.getMessage(), "Erro");
        }

    }

    public void faltasPeriodo() {
        try {
            String mes_inicio = data_aquisicao_inicial.substring(3, 5);
            String data_ano_inicio = data_aquisicao_inicial.substring(6, 10);

            String data_ano_fim = data_aquisicao_final.substring(6, 10);
            String mes_fim = data_aquisicao_final.substring(3, 5);

            Servidor_Model model = new Servidor_Model();
            model.setMatricula(getMatricula());
            this.setFaltas(model.servidorBuscarFaltas(mes_inicio, data_ano_inicio, mes_fim, data_ano_fim));
        } catch (Exception e) {
            Mensagens.mensagem_tela("Erro ao consutar Faltas!", "Não foi possível fazer a consulta!\n " + e.getMessage(), "Erro");

        }
    }

    public Boolean buscarValorRecibo() {

        Boolean lancado = true;
        Servidor_Model model = new Servidor_Model();
        model.setMatricula(getMatricula());
        setValor_recibo(model.servidorValorFerias(getMes_recibo(), getAno_recibo()));

        if (Float.parseFloat(getValor_recibo()) <= 0.0f) {
            lancado = false;
            Mensagens.mensagem_tela("Erro nos Lançamentos", "Não existe Lançamentos de Férias neste mês!"
                    + "\nFavor Configurar as remunerações para prosseguir!", "Erro");
            return lancado;
        }
        return lancado;
    }

    public void buscarDados() {
        Servidor_Model model = new Servidor_Model();
        model.setMatricula(getMatricula());
        model.servidorBuscarDados();

        this.setCnpf(model.getCnpf());
        this.setNome_cargo(model.getNome_cargo());
    }

    public void gerarRelatorio() throws SQLException, IOException {
        Gerar_Relatorio recibo = new Gerar_Relatorio();
        empresa.empresaDados();

        recibo.setNome(getNome());
        recibo.setMatricula(getMatricula());
        recibo.setFaltas(getFaltas());
        recibo.setCnpf(getCnpf());
        recibo.setNome_cargo(getNome_cargo());
        recibo.setAquisicao_inicial(getData_aquisicao_inicial());
        recibo.setAquisicao_final(getData_aquisicao_final());
        recibo.setGozo_inicial(getData_gozo_inicial());
        recibo.setGozo_final(getData_gozo_final());
        recibo.setRetorno(getData_retorno());
        recibo.setValor_recibo(getValor_recibo());
        recibo.setNome_empresa(empresa.getNome_empresa());
        recibo.setCnpj_empresa(empresa.getCnpj_empresa());
        recibo.setCidade(empresa.getCidade());
        recibo.setUf(empresa.getUf());
        recibo.setMes(getMes_recibo());
        recibo.setAno(getAno_recibo());
        recibo.setData_emissao(getData_emisao());

        recibo.gerar_arquivo_manual();
        try {
            recibo.gerar_aviso(getMatricula());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * @return the data_aquisicao_inicial
     */
    public String getData_aquisicao_inicial() {
        return data_aquisicao_inicial;
    }

    /**
     * @param data_aquisicao_inicial the data_aquisicao_inicial to set
     */
    public void setData_aquisicao_inicial(String data_aquisicao_inicial) {
        this.data_aquisicao_inicial = data_aquisicao_inicial;
    }

    /**
     * @return the data_aquisicao_final
     */
    public String getData_aquisicao_final() {
        return data_aquisicao_final;
    }

    /**
     * @param data_aquisicao_final the data_aquisicao_final to set
     */
    public void setData_aquisicao_final(String data_aquisicao_final) {
        this.data_aquisicao_final = data_aquisicao_final;
    }

    /**
     * @return the faltas
     */
    public String getFaltas() {
        return faltas;
    }

    /**
     * @param faltas the faltas to set
     */
    public void setFaltas(String faltas) {
        this.faltas = faltas;
    }

    /**
     * @return the data_gozo_inicial
     */
    public String getData_gozo_inicial() {
        return data_gozo_inicial;
    }

    /**
     * @param data_gozo_inicial the data_gozo_inicial to set
     */
    public void setData_gozo_inicial(String data_gozo_inicial) {
        this.data_gozo_inicial = data_gozo_inicial;
    }

    /**
     * @return the data_gozo_final
     */
    public String getData_gozo_final() {
        return data_gozo_final;
    }

    /**
     * @param data_gozo_final the data_gozo_final to set
     */
    public void setData_gozo_final(String data_gozo_final) {
        this.data_gozo_final = data_gozo_final;
    }

    /**
     * @return the data_retorno
     */
    public String getData_retorno() {
        return data_retorno;
    }

    /**
     * @param data_retorno the data_retorno to set
     */
    public void setData_retorno(String data_retorno) {
        this.data_retorno = data_retorno;
    }

    /**
     * @return the data_emisao
     */
    public String getData_emisao() {
        return data_emisao;
    }

    /**
     * @param data_emisao the data_emisao to set
     */
    public void setData_emisao(String data_emisao) {
        this.data_emisao = data_emisao;
    }
}
