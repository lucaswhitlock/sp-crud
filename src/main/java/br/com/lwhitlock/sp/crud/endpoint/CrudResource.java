package br.com.lwhitlock.sp.crud.endpoint;

import br.com.lwhitlock.sp.crud.entity.Pessoa;
import br.com.lwhitlock.sp.crud.service.ApiService;
import br.com.lwhitlock.sp.crud.type.Message;
import br.com.lwhitlock.sp.crud.type.ResponseModel;
import javax.ejb.EJB;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST Web Service
 *
 * @author lwhitlock
 */
@Path("/pessoa")
@RequestScoped
public class CrudResource {

    private Logger log = LoggerFactory.getLogger(CrudResource.class);
    @EJB
    private ApiService apiService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(Pessoa pessoa) {
        ResponseModel responseModel = new ResponseModel();
        try {
            apiService.cadastrarPessoa(pessoa);
            responseModel.getMessages().add(new Message(Response.Status.CREATED.getStatusCode(), "Pessoa cadastrada com sucesso!"));
        } catch (Exception e) {
            log.error(e.getMessage());
            responseModel.getMessages().add(new Message(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao processar dados: " + e.getMessage()));
        }
        return Response.ok(responseModel).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscar(@PathParam(value = "id") Long id) {
        ResponseModel responseModel = new ResponseModel();
        try {
            responseModel.setData(apiService.buscarPessoaPorId(id));
            responseModel.getMessages().add(new Message(Response.Status.OK.getStatusCode(), "Pessoa encontrada com sucesso!"));
        } catch (Exception e) {
            log.error(e.getMessage());
            responseModel.getMessages().add(new Message(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao processar dados: " + e.getMessage()));
        }
        return Response.ok(responseModel).build();
    }
}
