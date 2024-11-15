package br.com.gslevi.dao;

import br.com.gslevi.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public int registrarUsuario(Usuario usuario) throws SQLException {
        connection = ConnectionFactory.getConnection();
        int rowsAffected = 0;
        PreparedStatement SQLCommand = null;
        ResultSet result = null;

        try {
            String sql = "SELECT id_usu FROM usuario WHERE id_usu = ?";
            SQLCommand = connection.prepareStatement(sql);
            SQLCommand.setInt(1, usuario.getId());
            result = SQLCommand.executeQuery();

            if (result.next()) {
                System.out.println("Usuario já existente no banco de dados.");
                return 0;
            }

            sql = "INSERT INTO usuario (email, senha, nome_usuario) VALUES (?, ?, ?)";
            SQLCommand = connection.prepareStatement(sql);
            SQLCommand.setString(1, usuario.getEmail());
            SQLCommand.setString(2, usuario.getSenha());
            SQLCommand.setString(3, usuario.getUsername());

            rowsAffected = SQLCommand.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario registrado com sucesso!");
            }

        } catch (SQLException error) {
            System.out.println("Falha ao registrar usuario.");
            error.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (SQLCommand != null) SQLCommand.close();
            if (connection != null) connection.close();
        }

        return rowsAffected;
    }


    public Usuario buscarPorId(int id) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        Usuario usuario = null;

        try {
            String sql = "select * from usuario where id_usu = ?";
            comandoSql = connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            ResultSet result = comandoSql.executeQuery();

            if (result.next()) {
                usuario = new Usuario();
                usuario.setId(result.getInt(1));
                usuario.setEmail(result.getString(2));
                usuario.setSenha(result.getString(3));
                usuario.setUsername(result.getString(4));
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

        return usuario;
    }


    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        try{
            comandoSql = connection.prepareStatement("select * from usuario order by id_usu");
            ResultSet result = comandoSql.executeQuery();
            while (result.next()){
                Usuario usuario = new Usuario();
                usuario.setId(result.getInt(1));
                usuario.setEmail(result.getString(2));
                usuario.setSenha(result.getString(3));
                usuario.setUsername(result.getString(4));
                usuarios.add(usuario);
            }
            comandoSql.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
            return usuarios;
    }

    public int alterarUsuario(int id, Usuario usuario) throws SQLException {
        connection = ConnectionFactory.getConnection();
        PreparedStatement SQLCommand = null;
        int rowsAffected = 0;
        try{
            String sql = "update usuario set email = ?, senha = ?, " +
                    "nome_usuario = ? where id_usu = ?";
            SQLCommand =  connection.prepareStatement(sql);
            SQLCommand.setString(1, usuario.getEmail());
            SQLCommand.setString(2, usuario.getSenha());
            SQLCommand.setString(3, usuario.getUsername());
            SQLCommand.setInt(4, id);
            rowsAffected = SQLCommand.executeUpdate();
            System.out.println("Usuario alterado com sucesso!");

        }catch (SQLException e){
            System.out.println("Falha ao alterar usuario...");
            e.printStackTrace();
        } finally {
            SQLCommand.close();
            connection.close();
        }

        return rowsAffected;
    }

    public int deletarUsuario(int id){
        connection = ConnectionFactory.getConnection();
        PreparedStatement comandoSql = null;
        int result = 0;
        try{
            String sql = "delete from usuario where id_usu = ?";
            comandoSql =  connection.prepareStatement(sql);
            comandoSql.setInt(1, id);
            int rowsAffected = comandoSql.executeUpdate();
            result = (rowsAffected > 0 ) ? 1 : 0;
            comandoSql.close();
            connection.close();
            System.out.println("Usuário deletado com sucesso! ");
        }catch (SQLException e){
            System.out.println("Falha ao deletar usuario...");
            e.printStackTrace();
        }

        return result;
    }

}