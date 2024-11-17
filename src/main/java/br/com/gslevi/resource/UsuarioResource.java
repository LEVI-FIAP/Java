package br.com.gslevi.resource;


import br.com.gslevi.dto.UsuarioRequestDTO;
import br.com.gslevi.dto.UsuarioResponseDTO;
import br.com.gslevi.exception.ConflictExceptionZ;
import br.com.gslevi.exception.InternalServerErrorExceptionZ;
import br.com.gslevi.exception.NotFoundExceptionZ;
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
                throw new ConflictExceptionZ("RESPONSE 409 - Usuario já existente...");
            }
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar registrar o usuario...");
        }
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, UsuarioRequestDTO usuario) {
        try {
            int result = userService.update(id, usuario);

            if (result > 0) {
                return Response.ok("RESPONSE 200 - Usuario atualizado com sucesso!").build();
            } else {
                throw new NotFoundExceptionZ("RESPONSE 404 - Usuario não encontrado para ser atualizado.");
            }
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar atualizar o usuario...");
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioResponseDTO> listUsers() {
        try {
            return userService.listar();
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar listar os usuarios...");
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        try {
            UsuarioResponseDTO usuario = userService.buscarPorId(id);

            if (usuario == null) {
                throw new NotFoundExceptionZ("RESPONSE 404 - Usuario com ID " + id + " não encontrado");
            } else {
                return Response.ok(usuario).build();
            }
        } catch (SQLException e) {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar buscar o usuario...");
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUserById(@PathParam("id") int id) {
        int result = userService.deletar(id);

        if (result > 0) {
            return Response.ok("RESPONSE 200 - Usuario deletado com sucesso!").build();
        } else if (result == 0) {
            throw new NotFoundExceptionZ("RESPONSE 404 - Usuario não encontrado para ser deletado.");
        } else {
            throw new InternalServerErrorExceptionZ("RESPONSE 500 - Ocorreu um erro ao tentar deletar o usuario...");
        }
    }

}
