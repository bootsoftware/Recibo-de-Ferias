/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.control;

import aviso.model.Servidor_Model;
import aviso.utilitarios.Mensagens;
import com.sun.swing.internal.plaf.metal.resources.metal;
import org.codehaus.groovy.control.messages.Message;
import org.eclipse.jdt.internal.compiler.util.Messages;

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
    private String faltas;

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
            model.servidorBuscarNome();

            setNome(model.getNome());


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
            model.servidorBuscarFaltas(mes_inicio, data_ano_inicio, mes_fim, data_ano_fim);
            
            this.setFaltas(model.getFaltas());

        } catch (Exception e) {
            Mensagens.mensagem_tela("Erro ao consutar Faltas!", "Não foi possível fazer a consulta!\n " + e.getMessage(), "Erro");

        }
    }

    public void buscarDados() {
        Servidor_Model model = new Servidor_Model();
        model.setMatricula(getMatricula());
        model.servidorBuscarDados();
        
        this.setCnpf(model.getCnpf());
        this.setNome_cargo(model.getNome_cargo());
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
}
