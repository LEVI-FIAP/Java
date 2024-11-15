package br.com.gslevi.dto;

import br.com.gslevi.model.Regiao;

public class RegiaoDTO {
    private int id;
    private String nome;
    private float horasSol;

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

    public RegiaoDTO convertToDto(Regiao regiao) {
        RegiaoDTO regiaoDTO = new RegiaoDTO();
        regiaoDTO.setId(regiao.getId());
        regiaoDTO.setNome(regiao.getNome());
        regiaoDTO.setHorasSol(regiao.getHorasSol());
        return regiaoDTO;
    }

    public Regiao convertToModel(RegiaoDTO regiaoDTO){
        Regiao regiao = new Regiao();
        regiao.setId(regiaoDTO.getId());
        regiao.setNome(regiaoDTO.getNome());
        regiao.setHorasSol(regiaoDTO.getHorasSol());
        return regiao;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() +"\nIrradiação diária: " + getHorasSol();
    }
}
