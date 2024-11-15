package br.com.gslevi.dto;

import br.com.gslevi.model.Relatorio;

public class RelatorioResponseDTO {
    int id;
    float consumoMensal;
    float contaLuz;
    float areaDesejada;
    int qtdPaineis;
    float potenciaTotal;
    float custoInstalacao;
    float economiaMensal;
    float payback;
    float energiaMes;
    int idRegiao;
    int idUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getQtdPaineis() {
        return qtdPaineis;
    }

    public void setQtdPaineis(int qtdPaineis) {
        this.qtdPaineis = qtdPaineis;
    }

    public float getPotenciaTotal() {
        return potenciaTotal;
    }

    public void setPotenciaTotal(float potenciaTotal) {
        this.potenciaTotal = potenciaTotal;
    }

    public float getCustoInstalacao() {
        return custoInstalacao;
    }

    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }

    public float getEconomiaMensal() {
        return economiaMensal;
    }

    public void setEconomiaMensal(float economiaMensal) {
        this.economiaMensal = economiaMensal;
    }

    public float getPayback() {
        return payback;
    }

    public void setPayback(float payback) {
        this.payback = payback;
    }

    public float getEnergiaMes() {
        return energiaMes;
    }

    public void setEnergiaMes(float energiaMes) {
        this.energiaMes = energiaMes;
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

    public RelatorioResponseDTO convertToDto(Relatorio relatorio) {
        RelatorioResponseDTO relatorioDTO = new RelatorioResponseDTO();
        relatorioDTO.setId(relatorio.getId());
        relatorioDTO.setConsumoMensal(relatorio.getConsumoMensal());
        relatorioDTO.setContaLuz(relatorio.getContaLuz());
        relatorioDTO.setAreaDesejada(relatorio.getAreaDesejada());
        relatorioDTO.setQtdPaineis(relatorio.getQtdPaineis());
        relatorioDTO.setPotenciaTotal(relatorio.getPotenciaTotal());
        relatorioDTO.setCustoInstalacao(relatorio.getCustoInstalacao());
        relatorioDTO.setEconomiaMensal(relatorio.getEconomiaMensal());
        relatorioDTO.setPayback(relatorio.getPayback());
        relatorioDTO.setEnergiaMes(relatorio.getEnergiaMes());
        relatorioDTO.setIdRegiao(relatorio.getIdRegiao());
        relatorioDTO.setIdUsuario(relatorio.getIdUsuario());
        return relatorioDTO;
    }

    public Relatorio convertToModel(RelatorioResponseDTO relatorioDTO){
        Relatorio relatorio = new Relatorio();
        relatorio.setId(relatorioDTO.getId());
        relatorio.setConsumoMensal(relatorioDTO.getConsumoMensal());
        relatorio.setContaLuz(relatorioDTO.getContaLuz());
        relatorio.setAreaDesejada(relatorioDTO.getAreaDesejada());
        relatorio.setQtdPaineis(relatorioDTO.getQtdPaineis());
        relatorio.setPotenciaTotal(relatorioDTO.getPotenciaTotal());
        relatorio.setCustoInstalacao(relatorioDTO.getCustoInstalacao());
        relatorio.setEconomiaMensal(relatorioDTO.getEconomiaMensal());
        relatorio.setPayback(relatorioDTO.getPayback());
        relatorio.setEnergiaMes(relatorioDTO.getEnergiaMes());
        relatorio.setIdRegiao(relatorioDTO.getIdRegiao());
        relatorio.setIdUsuario(relatorioDTO.getIdUsuario());
        return relatorio;
    }


    @Override
    public String toString()
    {
        return "Quantidade de painéis: " + this.qtdPaineis + "\nCusto da instalação: " + this.custoInstalacao + "\nEconomia mensal: " + this.economiaMensal + "\nEnergia gerada pelos painéis: " + this.energiaMes + "\nPayBack(meses): " + this.payback;
    }

}
