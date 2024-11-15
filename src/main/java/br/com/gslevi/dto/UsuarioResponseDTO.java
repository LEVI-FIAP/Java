package br.com.gslevi.dto;

import br.com.gslevi.model.Usuario;

import java.util.Random;

public class UsuarioResponseDTO {
    private int id;
    private String email;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsuarioResponseDTO convertToDto(Usuario usuario) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUsername(usuario.getUsername());
        return usuarioDTO;
    }

    public Usuario convertToModel(UsuarioResponseDTO usuarioDTO){
        Random random = new Random();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(Integer.toString(random.nextInt(999999999)));
        usuario.setUsername(usuarioDTO.getUsername());
        return usuario;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername();
    }


}
