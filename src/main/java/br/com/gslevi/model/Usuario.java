package br.com.gslevi.model;
import java.util.Random;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String username;

    public Usuario() {
        Random random = new Random();
        this.id = random.nextInt(99999);
    }

    public Usuario(String email, String senha, String username) {
        Random random = new Random();
        this.id = random.nextInt(99999);
        this.email = email;
        this.senha = senha;
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername();
    }


}