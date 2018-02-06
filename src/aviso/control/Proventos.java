/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.control;

/**
 *
 * @author wanderlei
 */
public class Proventos {


    private String cod_prov;
    private String nome_prov;
    private String valor;
    private String referencia;

    public Proventos() {
    }

    /**
     * @return the cod_prov
     */
    public String getCod_prov() {
        return cod_prov;
    }

    /**
     * @param cod_prov the cod_prov to set
     */
    public void setCod_prov(String cod_prov) {
        this.cod_prov = cod_prov;
    }

    /**
     * @return the nome_prov
     */
    public String getNome_prov() {
        return nome_prov;
    }

    /**
     * @param nome_prov the nome_prov to set
     */
    public void setNome_prov(String nome_prov) {
        this.nome_prov = nome_prov;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

}
