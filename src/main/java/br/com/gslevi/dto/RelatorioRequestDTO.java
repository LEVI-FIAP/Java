package br.com.gslevi.dto;

import br.com.gslevi.model.Relatorio;

public class RelatorioRequestDTO {
    float consumoMensal;
    float contaLuz;
    float areaDesejada;
    int idRegiao;
    int idUsuario;

    public float getConsumoMensal() {
        return consumoMensal;
    }

    public void setConsumoMensal(float consumoMensal) {
        this.consumoMensal = consumoMensal;
    }

    public float getContaLuz() {
        return contaLuz;
    }

    public void setContaLuz(float contaLuz) {
        this.contaLuz = contaLuz;
    }

    public float getAreaDesejada() {
        return areaDesejada;
    }

    public void setAreaDesejada(float areaDesejada) {
        this.areaDesejada = areaDesejada;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RelatorioRequestDTO convertToDto(Relatorio relatorio) {
        RelatorioRequestDTO relatorioDTO = new RelatorioRequestDTO();
        relatorioDTO.setConsumoMensal(relatorio.getConsumoMensal());
        relatorioDTO.setContaLuz(relatorio.getContaLuz());
        relatorioDTO.setAreaDesejada(relatorio.getAreaDesejada());
        relatorioDTO.setIdRegiao(relatorio.getIdRegiao());
        relatorioDTO.setIdUsuario(relatorio.getIdUsuario());
        return relatorioDTO;
    }

    public Relatorio convertToModel(RelatorioRequestDTO relatorioDTO){
        Relatorio relatorio = new Relatorio();
        relatorio.setConsumoMensal(relatorioDTO.getConsumoMensal());
        relatorio.setContaLuz(relatorioDTO.getContaLuz());
        relatorio.setAreaDesejada(relatorioDTO.getAreaDesejada());
        relatorio.setIdRegiao(relatorioDTO.getIdRegiao());
        relatorio.setIdUsuario(relatorioDTO.getIdUsuario());
        return relatorio;
    }


    @Override
    public String toString()
    {
        return "Consumo de energia mensal: " + this.consumoMensal + "\nValor da conta de luz: " + this.contaLuz + "\nArea desejada: " + this.areaDesejada + "\nID da Regiao: " + this.idRegiao;
    }

}
