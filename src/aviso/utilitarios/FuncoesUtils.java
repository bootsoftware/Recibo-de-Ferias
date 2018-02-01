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
import javax.swing.text.MaskFormatter;

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
}
