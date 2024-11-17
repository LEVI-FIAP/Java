package br.com.gslevi.resource;

import br.com.gslevi.dto.RegiaoDTO;
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
    public List<RegiaoDTO> listRegions() throws SQLException {
        return regionService.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRegionById(@PathParam("id") int id) throws SQLException {
        RegiaoDTO regiaoDTO = regionService.buscarPorId(id);

        if (regiaoDTO == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Regiao com ID " + id + " não encontrada.")
                    .build();
        } else {
            return Response.ok(regiaoDTO).build();
        }
    }





}
