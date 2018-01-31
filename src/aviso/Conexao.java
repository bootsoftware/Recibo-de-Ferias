// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 26/01/2018 12:09:55
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Conexao.java

package aviso;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao
{
    
    public static Connection con = null;
    public static String status = null;
    public static String local_servidor;
    public static String local_banco;
    public static String local_porta;
    public static String local_senha;
    public static String local_user;

    public Conexao()
    {
    }
        public static String abrirConexao()
    {
        status = "Falha na conex\343o.";
        try
        {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            con = DriverManager.getConnection("jdbc:firebirdsql:"+ local_servidor+"/"+ local_porta +":"+ local_banco ,local_user, local_senha);
            status = "Conectado com sucesso.";
        }
        catch(ClassNotFoundException excUm)
        {
            excUm.printStackTrace();
        }
        catch(SQLException excDois)
        {
            JOptionPane.showMessageDialog(null, "Nome ou IP do servidor est\341 errado! \n Por favor corrija o erro e tente novamente.", "Erro Na Conex\343o Com Servidor", 0);
            excDois.printStackTrace();
        }
        return status;
    }

    public static String testarConexao()
    {
        status = "Falha na conex\343o.";
        fecharConexao();
        try
        {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            con = DriverManager.getConnection("jdbc:firebirdsql:"+ local_servidor+"/"+ local_porta +":"+ local_banco ,"sysdba", "masterkey");
             JOptionPane.showMessageDialog(null, "Conectado com Sucesso!", "Teste de Conex\343o Com Servidor", 1);
            status = "Conectado com sucesso.";
        }
        catch(ClassNotFoundException excUm)
        {
            excUm.printStackTrace();
        }
        catch(SQLException excDois)
        {
            JOptionPane.showMessageDialog(null, "Nome ou IP do servidor est\341 errado! \n Por favor corrija o erro e tente novamente.", "Teste de Conex\343o Com Servidor", 0);
            excDois.printStackTrace();
        }
        fecharConexao();
        return status;
    }

    public static String fecharConexao()
    {
        status = "Falha ao finalizar conex\343o.";
        try
        {
            con = null;
            status = "Conex\343o finalizada com sucesso.";
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        return status;
    }

   

}
