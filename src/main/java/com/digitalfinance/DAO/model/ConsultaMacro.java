/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalfinance.DAO.model;

import com.digitalfinance.DAO.entity.Algoritmo;
import com.digitalfinance.DAO.entity.Dpd;
import com.digitalfinance.DAO.entity.Macro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultaMacro {
    public static ArrayList<Macro> consultaMacroDpdAsc(String agente,String dpd,String algoritmo){
        Connection con;
        PreparedStatement ps;
        try{
            con=ModelConection.getConnection();
            ps=con.prepareStatement("SELECT  \n" +
                "r.AGENTE AS AGENTE\n" +
                ",r.TELEFONO AS TELEFONO\n" +
                ",r.CONTRATO AS CONTRATO\n" +
                ",r.DPD AS DPD\n" +
                ",r.ADEUDO AS ADEUDO\n" +
                ",r.NOMBRE AS NOMBRE\n" +
                ",r.CORREO  AS CORREO\n" +
                " ,r.SPEI  AS SPEI\n" +
                " ,r.OXXO  AS OXXO\n" +
                " ,r.ALGORITMO AS  ALGORITMO\n" +
                " ,r.Enviado AS Enviado\n" +
                " ,mit.Conteo AS Conteo\n" +
                "FROM \n" +
                "(SELECT \n" +
                " a.Agente as AGENTE----interno \n" +
                " ,a.TEL AS TELEFONO--api \n" +
                " ,a.CONTRATO AS CONTRATO \n" +
                " ,Convert(int,a.DPD) AS DPD \n" +
                " ,a.Adeudo AS ADEUDO \n" +
                "  ,a.NOMBRE AS NOMBRE \n" +
                " --,a.NCOMPLETO AS NCOMPLETO \n" +
                " ,ll.correo AS CORREO \n" +
                " ,a.SPEI_CLABE AS SPEI \n" +
                " ,a.OXXOReference AS OXXO \n" +
                " --,a.Client_Type AS TIPOCLIENTE \n" +
                " --,a.PROMESAS AS PROMESAS \n" +
                " --,a.DIAS_CCC AS DIASCCC \n" +
                " --,a.DIAS_MIT AS DIASMIT \n" +
                " --,a.DIAS_MESSB AS DIASMSB \n" +
                " ,a.ALGORITMO AS ALGORITMO \n" +
                " ,w.Enviado\n" +
                "  FROM DC_HIS_ASIG a \n" +
                " LEFT JOIN dm_LLAVES ll \n" +
                " ON ll.contract_id=a.CONTRATO \n" +
                " LEFT JOIN dm_REF_GPT1 AS r \n" +
                " ON r.number=a.CONTRATO \n" +
                " LEFT JOIN dm_PJava AS j \n" +
                " ON a.CONTRATO=j.number \n" +
                " LEFT JOIN dm_PJavaMsgWhatsappp AS w\n" +
                " ON a.CONTRATO=w.Contrato\n" +
                " WHERE j.number IS NULL AND CONVERT(DATE,[Creation_Day])=CONVERT(DATE,GETDATE()) \n" +
                " AND a.Agente='"+agente+"'\n" +
                " and a.DPD ="+dpd+"  and a.ALGORITMO='"+algoritmo+"' \n" +
                " ) AS r\n" +
                " LEFT JOIN (SELECT \n" +
                "	[Cliente]\n" +
                "	,COUNT([Cliente]) as Conteo\n" +
                "	FROM [DC REPORT].[dbo].[Mitrol_int]\n" +
                "	where cast([Fecha_interaccion] as date ) = cast(getdate() as date ) \n" +
                "	AND [Campaña] LIKE '%W%'\n" +
                "	group by [Cliente]) AS mit\n" +
                "ON r.TELEFONO=mit.Cliente\n" +
                "GROUP BY AGENTE,CONTRATO,TELEFONO,DPD,ADEUDO,NOMBRE,CORREO,SPEI,OXXO,ALGORITMO,Enviado,Conteo\n" +
                "ORDER BY CONVERT(int, r.Adeudo) ASC");
            ResultSet rs=ps.executeQuery();
            ArrayList<Macro> lista=new ArrayList();
            while(rs.next()){
                Macro macro=new Macro();
                macro.setAgente(rs.getString("AGENTE"));
                macro.setTel(rs.getString("TELEFONO"));
                macro.setNumber(rs.getString("CONTRATO"));
                macro.setDpd(rs.getString("DPD"));
                macro.setAdeudo(rs.getString("ADEUDO"));
                macro.setNombre(rs.getString("NOMBRE"));
//                macro.setClientFullName(rs.getString("NCOMPLETO"));
                macro.setCorreo(rs.getString("CORREO"));
                macro.setSpeiClave(rs.getString("SPEI"));
                macro.setOxxoReference(rs.getString("OXXO"));
//                macro.setTipoCliente(rs.getString("TIPOCLIENTE"));
//                macro.setPromesas(rs.getString("PROMESAS"));
//                macro.setDiasCCC(rs.getString("DIASCCC"));
//                macro.setDiasMit(rs.getString("DIASMIT"));
//                macro.setDiasMssb(rs.getString("DIASMSB"));
                macro.setAlgoritmo(rs.getString("ALGORITMO"));
                macro.setEnviado(rs.getString("Enviado"));
                macro.setConteoMit(rs.getString("Conteo"));
                lista.add(macro);
            }
            cerrarConexion(con, ps, rs);
            return lista;
        }catch(Exception e){
            return null;
        }        
    }
    public static ArrayList<Macro> consultaMacroDpdDesc(String agente,String dpd,String algoritmo){
        Connection con;
        PreparedStatement ps;
        try{
            con=ModelConection.getConnection();
            ps=con.prepareStatement("SELECT  \n" +
                "r.AGENTE AS AGENTE\n" +
                ",r.TELEFONO AS TELEFONO\n" +
                ",r.CONTRATO AS CONTRATO\n" +
                ",r.DPD AS DPD\n" +
                ",r.ADEUDO AS ADEUDO\n" +
                ",r.NOMBRE AS NOMBRE\n" +
                ",r.CORREO  AS CORREO\n" +
                " ,r.SPEI  AS SPEI\n" +
                " ,r.OXXO  AS OXXO\n" +
                " ,r.ALGORITMO AS  ALGORITMO\n" +
                " ,r.Enviado AS Enviado\n" +
                " ,mit.Conteo AS Conteo\n" +
                "FROM \n" +
                "(SELECT \n" +
                " a.Agente as AGENTE----interno \n" +
                " ,a.TEL AS TELEFONO--api \n" +
                " ,a.CONTRATO AS CONTRATO \n" +
                " ,Convert(int,a.DPD) AS DPD \n" +
                " ,a.Adeudo AS ADEUDO \n" +
                "  ,a.NOMBRE AS NOMBRE \n" +
                " --,a.NCOMPLETO AS NCOMPLETO \n" +
                " ,ll.correo AS CORREO \n" +
                " ,a.SPEI_CLABE AS SPEI \n" +
                " ,a.OXXOReference AS OXXO \n" +
                " --,a.Client_Type AS TIPOCLIENTE \n" +
                " --,a.PROMESAS AS PROMESAS \n" +
                " --,a.DIAS_CCC AS DIASCCC \n" +
                " --,a.DIAS_MIT AS DIASMIT \n" +
                " --,a.DIAS_MESSB AS DIASMSB \n" +
                " ,a.ALGORITMO AS ALGORITMO \n" +
                " ,w.Enviado\n" +
                "  FROM DC_HIS_ASIG a \n" +
                " LEFT JOIN dm_LLAVES ll \n" +
                " ON ll.contract_id=a.CONTRATO \n" +
                " LEFT JOIN dm_REF_GPT1 AS r \n" +
                " ON r.number=a.CONTRATO \n" +
                " LEFT JOIN dm_PJava AS j \n" +
                " ON a.CONTRATO=j.number \n" +
                " LEFT JOIN dm_PJavaMsgWhatsappp AS w\n" +
                " ON a.CONTRATO=w.Contrato\n" +
                " WHERE j.number IS NULL AND CONVERT(DATE,[Creation_Day])=CONVERT(DATE,GETDATE()) \n" +
                " AND a.Agente='"+agente+"'\n" +
                " and a.DPD ="+dpd+"  and a.ALGORITMO='"+algoritmo+"' \n" +
                " ) AS r\n" +
                " LEFT JOIN (SELECT \n" +
                "	[Cliente]\n" +
                "	,COUNT([Cliente]) as Conteo\n" +
                "	FROM [DC REPORT].[dbo].[Mitrol_int]\n" +
                "	where cast([Fecha_interaccion] as date ) = cast(getdate() as date ) \n" +
                "	AND [Campaña] LIKE '%W%'\n" +
                "	group by [Cliente]) AS mit\n" +
                "ON r.TELEFONO=mit.Cliente\n" +
                "GROUP BY AGENTE,CONTRATO,TELEFONO,DPD,ADEUDO,NOMBRE,CORREO,SPEI,OXXO,ALGORITMO,Enviado,Conteo\n" +
                "ORDER BY CONVERT(int, r.Adeudo) DESC");
            ResultSet rs=ps.executeQuery();
            ArrayList<Macro> lista=new ArrayList();
            while(rs.next()){
                Macro macro=new Macro();
                macro.setAgente(rs.getString("AGENTE"));
                macro.setTel(rs.getString("TELEFONO"));
                macro.setNumber(rs.getString("CONTRATO"));
                macro.setDpd(rs.getString("DPD"));
                macro.setAdeudo(rs.getString("ADEUDO"));
                macro.setNombre(rs.getString("NOMBRE"));
//                macro.setClientFullName(rs.getString("NCOMPLETO"));
                macro.setCorreo(rs.getString("CORREO"));
                macro.setSpeiClave(rs.getString("SPEI"));
                macro.setOxxoReference(rs.getString("OXXO"));
//                macro.setTipoCliente(rs.getString("TIPOCLIENTE"));
//                macro.setPromesas(rs.getString("PROMESAS"));
//                macro.setDiasCCC(rs.getString("DIASCCC"));
//                macro.setDiasMit(rs.getString("DIASMIT"));
//                macro.setDiasMssb(rs.getString("DIASMSB"));
                macro.setAlgoritmo(rs.getString("ALGORITMO"));
                macro.setEnviado(rs.getString("Enviado"));
                macro.setConteoMit(rs.getString("Conteo"));
                lista.add(macro);
            }
            cerrarConexion(con, ps, rs);
            return lista;
        }catch(Exception e){
            return null;
        }        
    }

    public static ArrayList<Dpd> consultaDpdsDisponibles(String agente){
        Connection con;
        PreparedStatement ps;
        try{
            con=ModelConection.getConnection();
            ps=con.prepareStatement("SELECT\n" +
                "Convert(int,a.DPD) AS DPD\n" +
                "--,a.ALGORITMO AS ALGORITMO\n" +
                " FROM DC_HIS_ASIG a\n" +
                "LEFT JOIN dm_LLAVES ll\n" +
                "ON ll.contract_id=a.CONTRATO\n" +
                "LEFT JOIN dm_REF_GPT1 AS r \n" +
                "ON r.number=a.CONTRATO\n" +
                "WHERE Agente='"+agente+"' AND CONVERT(DATE,[Creation_Day])=CONVERT(DATE,GETDATE())\n" +
                "GROUP BY a.DPD\n" +
                "ORDER BY CONVERT(int, a.DPD)");
            ResultSet rs=ps.executeQuery();
            ArrayList<Dpd> lista=new ArrayList();
            while(rs.next()){
                Dpd dpd=new Dpd();
                dpd.setDpd(rs.getString("DPD"));
                lista.add(dpd);
            }
            cerrarConexion(con, ps, rs);
            return lista;
        }catch(Exception e){
            return null;
        }
    }
    public static ArrayList<Algoritmo> consultaAlgoritmosDisponibles(String agente){
        Connection con;
        PreparedStatement ps;
        try{
            con=ModelConection.getConnection();
            ps=con.prepareStatement("SELECT\n" +
                "--Convert(int,a.DPD) AS DPD\n" +
                "a.ALGORITMO AS ALGORITMO\n" +
                " FROM DC_HIS_ASIG a\n" +
                "LEFT JOIN dm_LLAVES ll\n" +
                "ON ll.contract_id=a.CONTRATO\n" +
                "LEFT JOIN dm_REF_GPT1 AS r \n" +
                "ON r.number=a.CONTRATO\n" +
                "WHERE Agente='"+agente+"' AND CONVERT(DATE,[Creation_Day])=CONVERT(DATE,GETDATE())\n" +
                "GROUP BY a.ALGORITMO\n" +
                "ORDER BY a.ALGORITMO");
            ResultSet rs=ps.executeQuery();
            ArrayList<Algoritmo> lista=new ArrayList();
            while(rs.next()){
                Algoritmo algortimo=new Algoritmo();
                algortimo.setAlgoritmo(rs.getString("ALGORITMO"));
                lista.add(algortimo);
            }
            cerrarConexion(con, ps, rs);
            return lista;
        }catch(Exception e){
            return null;
        }
    }
    
    public static void cerrarConexion(Connection con,PreparedStatement ps,ResultSet rs) throws SQLException{
        con.close();
        ps.close();
        rs.close();
    }    
}