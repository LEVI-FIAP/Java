package br.com.gslevi;

import br.com.gslevi.dao.RelatorioDAO;
import br.com.gslevi.model.Relatorio;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        Relatorio rel1 = new Relatorio(220, 800, 30, 3, 14);
//        System.out.println(rel1.getQtdPaineis());
//        System.out.println(rel1.getPotenciaTotal());
//        System.out.println(rel1.getCustoInstalacao());
//        System.out.println(rel1.getEnergiaMes());
//        System.out.println(rel1.getEconomiaMensal());
//        System.out.println(rel1.getPayback());

        RelatorioDAO relDAO = new RelatorioDAO();
//        relDAO.registrarRelatorio(rel1);
//        relDAO.alterarRelatorio(2, rel1);
        System.out.println(relDAO.listarRelatorios());
        System.out.println(relDAO.buscarPorId(2));
        relDAO.deletarRelatorio(2);








    }
}
