package br.com.gslevi.resource;


import br.com.gslevi.dto.RelatorioRequestDTO;
import br.com.gslevi.dto.RelatorioResponseDTO;
import br.com.gslevi.service.RelatorioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
                return Response.status(Response.Status.CONFLICT)
                        .entity("RESPONSE 409 - Relatorio já existente...")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("RESPONSE 500 - Ocorreu um erro ao tentar registrar o relatorio...")
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateReport(@PathParam("id") int id, RelatorioRequestDTO relatorio) throws SQLException {
        int result = relatorioService.update(id, relatorio);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Relatório atualizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Relatório não encontrado para ser atualizado.")
                    .build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelatorioResponseDTO> listReports() throws SQLException {
        return relatorioService.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportById(@PathParam("id") int id) throws SQLException {
        RelatorioResponseDTO relatorio = relatorioService.buscarPorId(id);

        if (relatorio == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Relatorio com ID " + id + " não encontrado")
                    .build();
        } else {
            return Response.ok(relatorio).build();
        }
    }



    @DELETE
    @Path("/{id}")
    public Response deleteReportById(@PathParam("id") int id) {
        int result = relatorioService.deletar(id);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Relatorio deletado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Relatorio não encontrado!")
                    .build();
        }
    }

}
