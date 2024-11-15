package br.com.gslevi.service;

import br.com.gslevi.dao.RegiaoDAO;
import br.com.gslevi.dto.RegiaoDTO;
import br.com.gslevi.model.Regiao;
import org.jvnet.hk2.annotations.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class RegiaoService {

    private final RegiaoDAO regiaoDAO = new RegiaoDAO();

    public List<RegiaoDTO> listar() throws SQLException {
        List<Regiao> regioes = regiaoDAO.listarRegioes();
        return regioes.stream()
                .map(regiao -> {
                    RegiaoDTO regiaoDTO = new RegiaoDTO();
                    regiaoDTO.setId(regiao.getId());
                    regiaoDTO.setNome(regiao.getNome());
                    regiaoDTO.setHorasSol(regiao.getHorasSol());
                    return regiaoDTO;
                })
                .collect(Collectors.toList());
    }

    public RegiaoDTO buscarPorId(int id) throws SQLException {
        Regiao regiao = regiaoDAO.buscarPorId(id);

        if (regiao == null) {
            return null;
        }

        RegiaoDTO regiaoDTO = new RegiaoDTO();
        return regiaoDTO.convertToDto(regiao);
    }

}
