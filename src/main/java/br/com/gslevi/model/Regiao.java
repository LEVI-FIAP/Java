package br.com.gslevi.model;

public class Regiao {

    private int id;
    private String nome;
    private float horasSol;

    public Regiao() {
    }

    public Regiao(int id, String nome, float taxaIrradiacao) {
        this.id = id;
        this.nome = nome;
        this.horasSol = taxaIrradiacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getHorasSol() {
        return horasSol;
    }

    public void setHorasSol(float horasSol) {
        this.horasSol = horasSol;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() +"\nIrradiação diária: " + getHorasSol();
    }
}
