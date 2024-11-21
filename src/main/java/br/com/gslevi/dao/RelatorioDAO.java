package br.com.gslevi.dao;

import br.com.gslevi.model.Relatorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    private Connection connection;

    public int registrarRelatorio(Relatorio relatorio) throws SQLException {
        connection = ConnectionFactory.getConnection();
        int rowsAffected = 0;
        PreparedStatement SQLCommand = null;
        ResultSet result = null;

        try {
            String sql = "SELECT id_relatorio FROM relatorio WHERE id_relatorio = ?";
            SQLCommand = connection.prepareStatement(sql);
            SQLCommand.setInt(1, relatorio.getId());
            result = SQLCommand.executeQuery();

            if (result.next()) {
                System.out.println("Relatorio já existente no banco de dados.");
                return 0;
            }

            sql = "INSERT INTO relatorio (CONSUMO_MENSAL, CONTA_LUZ, AREA_DESEJADA, QTD_PAINEIS, POTENCIA_TOTAL, CUSTO_INSTAL, ECONOMIA_MENSAL, PAYBACK, ENERGIA_MES, ID_REG, ID_USU) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            SQLCommand = connection.prepareStatement(sql);
            SQLCommand.setFloat(1, relatorio.getConsumoMensal());
            SQLCommand.setFloat(2, relatorio.getContaLuz());
            SQLCommand.setFloat(3, relatorio.getAreaDesejada());
            SQLCommand.setFloat(4, relatorio.getQtdPaineis());
            SQLCommand.setFloat(5, relatorio.getPotenciaTotal());
            SQLCommand.setFloat(6, relatorio.getCustoInstalacao());
            SQLCommand.setFloat(7, relatorio.getEconomiaMensal());
            SQLCommand.setFloat(8, relatorio.getPayback());
            SQLCommand.setFloat(9, relatorio.getEnergiaMes());
            SQLCommand.setFloat(10, relatorio.getIdRegiao());
            SQLCommand.setFloat(11, relatorio.getIdUsuario());

            rowsAffected = SQLCommand.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Relatorio registrado com sucesso!");
            }

        } catch (SQLException error) {
            System.out.println("Falha ao registrar relatorio.");
            error.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (SQLCommand != null) SQLCommand.close();
            if (connection != null) connection.close();
        }

        return rowsAffected;
    }


    public Relatorio buscarPorId(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        Relatorio relatorio = null;

        try {
            String sql = "select * from relatorio where id_relatorio = ?";
            comandoSql = connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            ResultSet result = comandoSql.executeQuery();

            if (result.next()) {
                relatorio = new Relatorio();
                relatorio.setId(result.getInt(1));
                relatorio.setConsumoMensal(result.getFloat(2));
                relatorio.setContaLuz(result.getFloat(3));
                relatorio.setAreaDesejada(result.getFloat(4));
                relatorio.setQtdPaineis(result.getInt(5));
                relatorio.setPotenciaTotal(result.getFloat(6));
                relatorio.setCustoInstalacao(result.getFloat(7));
                relatorio.setEconomiaMensal(result.getFloat(8));
                relatorio.setPayback(result.getFloat(9));
                relatorio.setEnergiaMes(result.getFloat(10));
                relatorio.setIdUsuario(result.getInt(11));
                relatorio.setIdRegiao(result.getInt(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (comandoSql != null) {
                comandoSql.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return relatorio;
    }

    public List<Relatorio> listarRelatoriosPorUserId(int userId) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        List<Relatorio> relatorios = new ArrayList<>();

        try {
            String sql = "SELECT * FROM relatorio WHERE id_usu = ? ORDER BY id_relatorio";
            comandoSql = connection.prepareStatement(sql);
            comandoSql.setInt(1, userId);
            ResultSet result = comandoSql.executeQuery();

            while (result.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId(result.getInt(1));
                relatorio.setConsumoMensal(result.getFloat(2));
                relatorio.setContaLuz(result.getFloat(3));
                relatorio.setAreaDesejada(result.getFloat(4));
                relatorio.setQtdPaineis(result.getInt(5));
                relatorio.setPotenciaTotal(result.getFloat(6));
                relatorio.setCustoInstalacao(result.getFloat(7));
                relatorio.setEconomiaMensal(result.getFloat(8));
                relatorio.setPayback(result.getFloat(9));
                relatorio.setEnergiaMes(result.getFloat(10));
                relatorio.setIdUsuario(result.getInt(11));
                relatorio.setIdRegiao(result.getInt(12));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (comandoSql != null) {
                comandoSql.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return relatorios;
    }



    public List<Relatorio> listarRelatorios(){
        List<Relatorio> relatorios = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        try{
            comandoSql = connection.prepareStatement("select * from relatorio order by id_relatorio");
            ResultSet result = comandoSql.executeQuery();
            while (result.next()){
                Relatorio relatorio = new Relatorio();
                relatorio.setId(result.getInt(1));
                relatorio.setConsumoMensal(result.getFloat(2));
                relatorio.setContaLuz(result.getFloat(3));
                relatorio.setAreaDesejada(result.getFloat(4));
                relatorio.setQtdPaineis(result.getInt(5));
                relatorio.setPotenciaTotal(result.getFloat(6));
                relatorio.setCustoInstalacao(result.getFloat(7));
                relatorio.setEconomiaMensal(result.getFloat(8));
                relatorio.setPayback(result.getFloat(9));
                relatorio.setEnergiaMes(result.getFloat(10));
                relatorio.setIdUsuario(result.getInt(11));
                relatorio.setIdRegiao(result.getInt(12));
                relatorios.add(relatorio);
            }
            comandoSql.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return relatorios;
    }

    public int alterarRelatorio(int id, Relatorio relatorio) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement SQLCommand = null;
        int rowsAffected = 0;
        try{
            String sql = "update relatorio set CONSUMO_MENSAL = ?, CONTA_LUZ = ?, " +
                    "AREA_DESEJADA = ?, QTD_PAINEIS = ?, POTENCIA_TOTAL = ?, CUSTO_INSTAL = ?, ECONOMIA_MENSAL = ?, " +
                    "PAYBACK = ?, ENERGIA_MES = ?, ID_REG = ? where id_relatorio = ?";
            SQLCommand =  connection.prepareStatement(sql);
            SQLCommand.setFloat(1, relatorio.getConsumoMensal());
            SQLCommand.setFloat(2, relatorio.getContaLuz());
            SQLCommand.setFloat(3, relatorio.getAreaDesejada());
            SQLCommand.setFloat(4, relatorio.getQtdPaineis());
            SQLCommand.setFloat(5, relatorio.getPotenciaTotal());
            SQLCommand.setFloat(6, relatorio.getCustoInstalacao());
            SQLCommand.setFloat(7, relatorio.getEconomiaMensal());
            SQLCommand.setFloat(8, relatorio.getPayback());
            SQLCommand.setFloat(9, relatorio.getEnergiaMes());
            SQLCommand.setFloat(10, relatorio.getIdRegiao());
            SQLCommand.setInt(11, id);
            rowsAffected = SQLCommand.executeUpdate();
            System.out.println("Relatorio alterado com sucesso!");

        }catch (SQLException e){
            System.out.println("Falha ao alterar relatorio...");
            e.printStackTrace();
        } finally {
            SQLCommand.close();
            connection.close();
        }

        return rowsAffected;
    }

    public int deletarRelatorio(int id){
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        int result = 0;
        try{
            String sql = "delete from relatorio where id_relatorio = ?";
            comandoSql =  connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            int rowsAffected = comandoSql.executeUpdate();
            if (rowsAffected > 0) {
                result = 1;
            } else {System.out.println("Falha ao deletar relatorio...");}
            comandoSql.close();
            connection.close();

        }catch (SQLException e){

            e.printStackTrace();
        }

        return result;
    }

    public int deletarRelatorioPorUserId(int id){
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        int result = 0;
        try{
            String sql = "delete from relatorio where id_usu = ?";
            comandoSql =  connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            int rowsAffected = comandoSql.executeUpdate();
            if (rowsAffected > 0) {
                result = 1;
            } else {System.out.println("Falha ao deletar relatorios...");}
            comandoSql.close();
            connection.close();

        }catch (SQLException e){

            e.printStackTrace();
        }

        return result;
    }

}