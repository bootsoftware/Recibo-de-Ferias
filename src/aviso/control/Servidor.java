/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.control;

import aviso.utilitarios.FuncoesUtils;
import aviso.utilitarios.Mensagens;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wanderlei
 */
public class Servidor {

    public static String faltas(String faltas, String data_aquisicao_inicial, String data_aquisicao_final,
            String matricula) {
        String total_falta = faltas;

        if (faltas.trim().equals("")) {
            String mes_inicio = "";
            String data_ano_inicio = "";
            String mes_fim = "";
            String data_ano_fim = "";
            String messagem = "";

            
           /* int valida_data_inicial = Integer.parseInt(FuncoesUtils.limpaCaracteres(data_aquisicao_inicial));
            int valida_data_final = Integer.parseInt(FuncoesUtils.limpaCaracteres(data_aquisicao_final));
            System.out.println(valida_data_inicial);
            System.out.println(valida_data_final);*/

          //  if (valida_data_inicial >= valida_data_final) {
          if(FuncoesUtils.dataMaior(data_aquisicao_inicial, data_aquisicao_final)){
                Mensagens.mensagem_tela("Erro Data de Aquisição!", "A data de de aquisição inicial deve ser maior que a data de aquisição final!", "erro");
            } else {

                mes_inicio = data_aquisicao_inicial.substring(3, 5);
                data_ano_inicio = data_aquisicao_inicial.substring(6, 10);
                if (mes_inicio.trim().equals("")) {
                    messagem = "Data inicio de aquisição inválida!";
                }
                data_ano_fim = data_aquisicao_final.substring(6, 10);
                mes_fim = data_aquisicao_final.substring(3, 5);

                if (mes_fim.trim().equals("")) {
                    messagem = messagem + "\nData fim de aquisição inválida!";
                }

                if (!(messagem.trim().equals(""))) {
                    Mensagens.mensagem_tela("Erro data", messagem, "erro");
                    return "";
                }

                //matricula = txt_matricula.getText();
                System.out.println("mes ini: " + mes_inicio);
                System.out.println("ano ini: " + data_ano_inicio);
                System.out.println("mes fim: " + mes_fim);
                System.out.println("ano fim: " + data_ano_fim);

                Conexao.abrirConexao();
                Statement stmt;
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
                            .append(matricula).toString();
                    System.out.println(sql_pesquisar_falta);

                    for (ResultSet rs_falta = stmt.executeQuery(sql_pesquisar_falta);
                            rs_falta.next();) {
                        total_falta = rs_falta.getString("falta");

                    }
                } catch (SQLException ex) {
                    return ex.getMessage();

                }
            }
        }
        return total_falta;
    }
}
