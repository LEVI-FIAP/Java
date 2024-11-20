package br.com.gslevi.service;

import br.com.gslevi.dao.RelatorioDAO;
import br.com.gslevi.dao.UsuarioDAO;
import br.com.gslevi.dto.UsuarioLoginDTO;
import br.com.gslevi.dto.UsuarioRequestDTO;
import br.com.gslevi.dto.UsuarioResponseDTO;
import br.com.gslevi.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public int registrar(UsuarioRequestDTO usuarioDTO) throws SQLException {
        Usuario usuario = usuarioDTO.convertToModel(usuarioDTO);
        if (Objects.equals(usuario.getEmail(), usuarioDAO.buscarPorEmail(usuario.getEmail())))
        {
            return 0;
        };
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
        relatorioDAO.deletarRelatorioPorUserId(id);
        return usuarioDAO.deletarUsuario(id);
    }

    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioDTO) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmail(usuarioDTO.getEmail());

        if (usuario == null) {
            return null;
        } else if (usuario.getEmail().equals(usuarioDTO.getEmail()) && usuario.getSenha().equals(usuarioDTO.getSenha())) {
            usuarioDTO.setId(usuario.getId());
            return usuarioDTO;
        } else {
            return null;
        }
    }

}
