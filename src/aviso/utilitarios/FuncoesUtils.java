/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aviso.utilitarios;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import org.joda.time.DateTime;

/**
 *
 * @author wanderlei
 */
public class FuncoesUtils {

    public static String limpaCaracteres(String palavra) {

        String aux = Normalizer.normalize(palavra, java.text.Normalizer.Form.NFD);
        aux = aux.replaceAll("[^\\p{ASCII}]", "");
        aux = aux.replaceAll("[/]", "");

        return aux;
    }

    public static boolean dataMaior(String data_inicial, String data_final) {
        Boolean retorno = false;
        int valida_data_inicial = Integer.parseInt(FuncoesUtils.limpaCaracteres(data_inicial));
        int valida_data_final = Integer.parseInt(FuncoesUtils.limpaCaracteres(data_final));

        if (valida_data_inicial >= valida_data_final) {
            retorno = true;
        }
        return retorno;
    }

    public static String format(String pattern, Object value) {
        try {
            MaskFormatter mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validarData(String data) {

        Boolean retorno;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            df.parse(data);
            // data válida
            retorno = true;
        } catch (ParseException ex) {
            // data inválida
            retorno = false;
        }
        return retorno;
    }

    public static DateTime dataSeparada(String data) {

        DateTime data_formato = null;

        if (data.equals("")) {

            Date data_hoje = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data = dateFormat.format(data_hoje);
            if (validarData(data)) {
                //formatar a data

            }
        }
        return data_formato;
    }

    /**
     *
     * @param tamanho
     * @param caracteres
     * @return
     */
    public static JTextField DefinirTiposCaracteresETamanho(int tamanho, String caracteres) {
        try {
            //defino a variável que vai guardar a quantidade de caracteres
            String quantidade = "";

            //defino um método de repetição para repetir o numero de
            //vezes  do tamanho
            for (int i = 0; i < tamanho; i++) {
                // defino asterisco para aceitar qualquer coisa e crio a máscara
                quantidade = quantidade + "*";
            }
            //  **********...   de acordo com o tamanho informado
            // defino que a mascara possui essa
            //quantidade de elementos que foi informado em tamanho e
            // foi colocada com * dentro de quantidade
            javax.swing.text.MaskFormatter nome = new javax.swing.text.MaskFormatter(quantidade);

            //defino que o parâmetro caracter recebido pelo
            //método contém os caracteres válidos 
            nome.setValidCharacters(caracteres);

            //retorno a mascara que foi criada  
            return new javax.swing.JFormattedTextField(nome);
        }// fim do try
        catch (ParseException e) {
            //mensagem se acontecer erro
            JOptionPane.showMessageDialog(null, "Ocorreu um erro");
            //retorno um campo de texto comum  
            return new JTextField();
        }//fim do catch
    }//fim do método
}
