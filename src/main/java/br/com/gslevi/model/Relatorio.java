package br.com.gslevi.model;

import java.util.Random;

public class Relatorio {
    int id;
    // Quanto ele consome de energia por mês, em média
    float consumoMensal;
    // Qual o preço da conta de luz, em média
    float contaLuz;
    // Área desejada disponível para instalação de painéis, de acordo com o Usuario
    float areaDesejada;
    // Quantidade de painés que serão instalados na área disponível (1,6m² por painél)
    int qtdPaineis;
    // Output máximo total de energia (qtd_paineis * potencia por painel)
    float potenciaTotal;
    // Custo total da instalação do sistema ( qtd_paineis * preco individual do painel )
    float custoInstalacao;
    //  É o valor em reais que você deixa de pagar na sua conta de luz por cada kW de potência instalado, graças à energia gerada pelo sistema solar -> Conta de luz antiga - conta de luz nova
    float economiaMensal;
    // Tempo até o investimento retornar para o Usuario
    float payback;
    // Quantidade de energia gerada pelos painéis solares por mês (depende da irradiação)
    float energiaMes;
    // ------------- Nesse caso, estamos definindo as foreign keys como Id, pois é o único atributo que vamos pegar do objeto, e será recebi em front-end.
    // Região a qual será extraída a taxa de radiação
    int idRegiao;
    // Id do usuário o qual o relatório pertence
    int idUsuario;

    public Relatorio() {
        Random random = new Random();
        this.id = random.nextInt(99999);
    }

    public Relatorio(float consumoMensal, float conta_luz, float areaDesejada, int qtdPaineis, float potenciaTotal, float custoInstalacao, float economiaMensal, float payback, float energiaMes, int id_regiao, int id_usuario) {
        Random random = new Random();
        this.id = random.nextInt(99999);
        this.consumoMensal = consumoMensal;
        this.contaLuz = conta_luz;
        this.areaDesejada = areaDesejada;
        this.qtdPaineis = qtdPaineis;
        this.potenciaTotal = potenciaTotal;
        this.custoInstalacao = custoInstalacao;
        this.economiaMensal = economiaMensal;
        this.payback = payback;
        this.energiaMes = energiaMes;
        this.idRegiao = id_regiao;
        this.idUsuario = id_usuario;
    }

    public Relatorio(float consumoMensal, float conta_luz, float areaDesejada, int idRegiao, int idUsuario) {
        Random random = new Random();
        this.id = random.nextInt(99999);
        this.consumoMensal = consumoMensal;
        this.contaLuz = conta_luz;
        this.areaDesejada = areaDesejada;
        this.idRegiao = idRegiao;
        this.idUsuario = idUsuario;
        this.setQtdPaineis();
        this.setPotenciaTotal();
        this.setCustoInstalacao();
        this.setEnergiaMes();
        this.setEconomiaMensal();
        this.setPayback();


    }

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

    public void setQtdPaineis() {
        this.qtdPaineis =  (int) Math.ceil(this.areaDesejada / 1.65);
    }
    public void setQtdPaineis(int qtdPaineis) {
        this.qtdPaineis =  qtdPaineis;
    }

    public float getPotenciaTotal() {
        return potenciaTotal;
    }

    public void setPotenciaTotal(float potenciaTotal) {
        this.potenciaTotal = potenciaTotal;
    }
    public void setPotenciaTotal() {
        this.potenciaTotal = (float) (this.qtdPaineis * 0.3);
    }

    public float getCustoInstalacao() {
        return custoInstalacao;
    }

    public void setCustoInstalacao(float custoInstalacao) {
        this.custoInstalacao = custoInstalacao;
    }
    public void setCustoInstalacao() {
        this.custoInstalacao = 22000 + this.potenciaTotal * 5250;
    }

    public float getEconomiaMensal() {
        return economiaMensal;
    }

    public void setEconomiaMensal(float economiaMensal) {
        this.economiaMensal = economiaMensal;
    }
    public void setEconomiaMensal() {
        float result = (float) (this.energiaMes * 0.83);
        if (result > this.contaLuz) {result = this.contaLuz;};

        this.economiaMensal = result;
    }

    public float getPayback() {
        return payback;
    }

    public void setPayback(float payback) {
        this.payback = payback;
    }
    public void setPayback() {
        this.payback = this.custoInstalacao / this.economiaMensal;
    }

    public float getEnergiaMes() {
        return energiaMes;
    }

    public void setEnergiaMes(float energiaMes) {
        this.energiaMes = energiaMes;
    }
    public void setEnergiaMes() {
        float horas_sol;
        switch (this.idRegiao){
            case 1:
                horas_sol = (float) 4.95;
                break;
            case 2:
                horas_sol = (float) 6.0;
                break;
            case 3:
                horas_sol = (float) 5.5;
                break;
            case 4:
                horas_sol = (float) 5.0;
                break;
            case 5:
                horas_sol = (float) 4.25;
                break;
            default:
                horas_sol = (float) 6.14;
        }
        this.energiaMes = this.potenciaTotal * 30 * horas_sol;
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

    @Override
    public String toString()
    {
        return "Quantidade de painéis: " + this.qtdPaineis + "\nCusto da instalação: " + this.custoInstalacao + "\nEconomia mensal: " + this.economiaMensal + "\nEnergia gerada pelos painéis: " + this.energiaMes + "\nPayBack(meses): " + this.payback;
    }
}
