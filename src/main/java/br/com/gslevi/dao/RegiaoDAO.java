package br.com.gslevi.dao;

import br.com.gslevi.model.Regiao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegiaoDAO {
    private Connection connection;

    public Regiao buscarPorId(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        Regiao regiao = null;

        try {
            String sql = "select * from regiao where id_reg = ?";
            comandoSql = connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            ResultSet result = comandoSql.executeQuery();

            if (result.next()) {
                regiao = new Regiao();
                regiao.setId(result.getInt(1));
                regiao.setNome(result.getString(2));
                regiao.setHorasSol(result.getFloat(3));
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

        return regiao;
    }


    public List<Regiao> listarRegioes(){
        List<Regiao> regioes = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        try{
            comandoSql = connection.prepareStatement("select * from regiao order by id_reg");
            ResultSet result = comandoSql.executeQuery();
            while (result.next()){
                Regiao regiao = new Regiao();
                regiao.setId(result.getInt(1));
                regiao.setNome(result.getString(2));
                regiao.setHorasSol(result.getFloat(3));
                regioes.add(regiao);
            }
            comandoSql.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return regioes;
    }

}