package br.com.gslevi.resource;

import br.com.gslevi.dto.RegiaoDTO;
import br.com.gslevi.exception.InternalServerErrorExceptionZ;
import br.com.gslevi.exception.NotFoundExceptionZ;
import br.com.gslevi.service.RegiaoService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/regions")
public class RegiaoResource {

    private final RegiaoService regionService = new RegiaoService();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegiaoDTO> listRegions() {
        try {
            return regionService.listar();
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar listar as regiões...");
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegionById(@PathParam("id") int id) {
        try {
            RegiaoDTO regiaoDTO = regionService.buscarPorId(id);

            if (regiaoDTO == null) {
                throw new NotFoundExceptionZ("RESPONSE 404 - Regiao com ID " + id + " não encontrada.");
            } else {
                return Response.ok(regiaoDTO).build();
            }
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar buscar a região...");
        }
    }





}
