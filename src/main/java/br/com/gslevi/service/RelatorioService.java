package br.com.gslevi.service;

import br.com.gslevi.dao.RelatorioDAO;
import br.com.gslevi.dto.RelatorioRequestDTO;
import br.com.gslevi.dto.RelatorioResponseDTO;
import br.com.gslevi.dto.UsuarioRequestDTO;
import br.com.gslevi.dto.UsuarioResponseDTO;
import br.com.gslevi.model.Relatorio;
import br.com.gslevi.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RelatorioService {

    private final RelatorioDAO relatorioDAO = new RelatorioDAO();

    public int registrar(RelatorioRequestDTO relatorioDTO) throws SQLException {
        Relatorio relatorio = relatorioDTO.convertToModel(relatorioDTO);
        relatorio.setQtdPaineis();
        relatorio.setPotenciaTotal();
        relatorio.setCustoInstalacao();
        relatorio.setEnergiaMes();
        relatorio.setEconomiaMensal();
        relatorio.setPayback();
        return relatorioDAO.registrarRelatorio(relatorio);
    }

    public int update(int id, RelatorioRequestDTO relatorioDTO) throws SQLException {
        Relatorio relatorio = relatorioDTO.convertToModel(relatorioDTO);
        relatorio.setQtdPaineis();
        relatorio.setPotenciaTotal();
        relatorio.setCustoInstalacao();
        relatorio.setEnergiaMes();
        relatorio.setEconomiaMensal();
        relatorio.setPayback();
        return relatorioDAO.alterarRelatorio(id, relatorio);
    }

    public List<RelatorioResponseDTO> listar() throws SQLException {
        List<Relatorio> relatorios = relatorioDAO.listarRelatorios();
        return relatorios.stream()
                .map(relatorio -> {
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
                })
                .collect(Collectors.toList());
    }

    public RelatorioResponseDTO buscarPorId(int id) throws SQLException {
        Relatorio relatorio = relatorioDAO.buscarPorId(id);

        if (relatorio == null) {
            return null;
        }

        RelatorioResponseDTO relatorioDTO = new RelatorioResponseDTO();
        return relatorioDTO.convertToDto(relatorio);
    }

    public List<RelatorioResponseDTO> listarPorUserId(int id) throws SQLException {
        List<Relatorio> relatorios = relatorioDAO.listarRelatoriosPorUserId(id);
        return relatorios.stream()
                .map(relatorio -> {
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
                })
                .collect(Collectors.toList());
    }




    public int deletar(int id){
        return relatorioDAO.deletarRelatorio(id);
    }


}
