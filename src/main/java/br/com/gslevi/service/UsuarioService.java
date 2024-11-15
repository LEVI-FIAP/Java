package br.com.gslevi.service;

import br.com.gslevi.dao.UsuarioDAO;
import br.com.gslevi.dto.UsuarioRequestDTO;
import br.com.gslevi.dto.UsuarioResponseDTO;
import br.com.gslevi.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public int registrar(UsuarioRequestDTO usuarioDTO) throws SQLException {
        Usuario usuario = usuarioDTO.convertToModel(usuarioDTO);
        return usuarioDAO.registrarUsuario(usuario);
    }

    public int update(int id, UsuarioRequestDTO usuarioDTO) throws SQLException {
        Usuario usuario = usuarioDTO.convertToModel(usuarioDTO);
        return usuarioDAO.alterarUsuario(id, usuario);
    }

    public List<UsuarioResponseDTO> listar() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        return usuarios.stream()
                .map(usuario -> {
                    UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setEmail(usuario.getEmail());
                    usuarioDTO.setUsername(usuario.getUsername());
                    return usuarioDTO;
                })
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO buscarPorId(int id) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        return usuarioDTO.convertToDto(usuario);
    }

    public int deletar(int id){
        return usuarioDAO.deletarUsuario(id);
    }

}
