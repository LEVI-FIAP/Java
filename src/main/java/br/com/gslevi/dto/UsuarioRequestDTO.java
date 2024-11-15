package br.com.gslevi.dto;

import br.com.gslevi.model.Usuario;

import java.util.Random;

public class UsuarioRequestDTO {
    private String email;
    private String username;
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioRequestDTO convertToDto(Usuario usuario) {
        UsuarioRequestDTO usuarioDTO = new UsuarioRequestDTO();
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setSenha(usuario.getSenha());
        return usuarioDTO;
    }

    public Usuario convertToModel(UsuarioRequestDTO usuarioDTO){
        Random random = new Random();
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setUsername(usuarioDTO.getUsername());
        return usuario;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername();
    }


}
