package br.com.gslevi.dto;

import br.com.gslevi.model.Usuario;

import java.util.Random;

public class UsuarioLoginDTO {
    private int id;
    private String email;
    private String senha;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioLoginDTO convertToDto(Usuario usuario) {
        UsuarioLoginDTO usuarioDTO = new UsuarioLoginDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        return usuarioDTO;
    }

    public Usuario convertToModel(UsuarioLoginDTO usuarioDTO){
        Random random = new Random();
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        return usuario;
    }

    @Override
    public String toString() {
        return "Email: " + getEmail();
    }

}
