package br.com.gslevi.resource;


import br.com.gslevi.dto.RelatorioRequestDTO;
import br.com.gslevi.dto.RelatorioResponseDTO;
import br.com.gslevi.exception.ConflictExceptionZ;
import br.com.gslevi.exception.InternalServerErrorExceptionZ;
import br.com.gslevi.service.RelatorioService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/reports")
public class RelatorioResource {

    private RelatorioService relatorioService = new RelatorioService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addReport(RelatorioRequestDTO relatorio) {
        try {
            int result = relatorioService.registrar(relatorio);
            if (result == 1) {
                return Response.status(Response.Status.CREATED)
                        .entity("RESPONSE 201 - Relatorio registrado com sucesso!")
                        .build();
            } else {
                throw new ConflictExceptionZ("RESPONSE 409 - Relatorio ja existente...");
            }
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar registrar o relatório...");
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReport(@PathParam("id") int id, RelatorioRequestDTO relatorio) {
        try {
            int result = relatorioService.update(id, relatorio);

            if (result > 0) {
                return Response.ok("RESPONSE 200 - Relatório atualizado com sucesso!").build();
            } else {
                throw new NotFoundException("RESPONSE 404 - Relatório não encontrado para ser atualizado.");
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("RESPONSE 500 - Ocorreu um erro ao tentar atualizar o relatório...");
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelatorioResponseDTO> listReports() {
        try {
            return relatorioService.listar();
        } catch (SQLException e) {
            throw new InternalServerErrorException("RESPONSE 500 - Ocorreu um erro ao tentar listar os relatórios...");
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportById(@PathParam("id") int id) {
        try {
            RelatorioResponseDTO relatorio = relatorioService.buscarPorId(id);

            if (relatorio == null) {
                throw new NotFoundException("RESPONSE 404 - Relatorio com ID " + id + " não encontrado");
            } else {
                return Response.ok(relatorio).build();
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("RESPONSE 500 - Ocorreu um erro ao tentar buscar o relatório...");
        }
    }



    @DELETE
    @Path("/{id}")
    public Response deleteReportById(@PathParam("id") int id) {
        int result = relatorioService.deletar(id);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Relatorio deletado com sucesso!").build();
        }  else if (result == 0) {
            throw new NotFoundException("RESPONSE 404 - Relatorio não encontrado!");
        } else {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar deletar o usuario...");
        }
    }

}
