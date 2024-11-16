package br.com.gslevi.resource;


import br.com.gslevi.dto.UsuarioRequestDTO;
import br.com.gslevi.dto.UsuarioResponseDTO;
import br.com.gslevi.service.UsuarioService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/users")
public class UsuarioResource {

    private UsuarioService userService = new UsuarioService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UsuarioRequestDTO usuario) {
        try {
            int result = userService.registrar(usuario);
            if (result == 1) {
                return Response.status(Response.Status.CREATED)
                        .entity("RESPONSE 201 - Usuario registrado com sucesso!")
                        .build();
            } else {
                return Response.status(Response.Status.CONFLICT)
                        .entity("RESPONSE 409 - Usuario já existente...")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("RESPONSE 500 - Ocorreu um erro ao tentar registrar o usuario...")
                    .build();
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, UsuarioRequestDTO usuario) throws SQLException {
        int result = userService.update(id, usuario);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Usuario atualizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Usuario não encontrado para ser atualizado.")
                    .build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioResponseDTO> listUsers() throws SQLException {
        return userService.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) throws SQLException {
        UsuarioResponseDTO usuario = userService.buscarPorId(id);

        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Usuario com ID " + id + " não encontrado")
                    .build();
        } else {
            return Response.ok(usuario).build();
        }
    }



    @DELETE
    @Path("/{id}")
    public Response deleteUserById(@PathParam("id") int id) {
        int result = userService.deletar(id);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Usuario deletado com sucesso!").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RESPONSE 404 - Usuario não encontrado!")
                    .build();
        }
    }

}
