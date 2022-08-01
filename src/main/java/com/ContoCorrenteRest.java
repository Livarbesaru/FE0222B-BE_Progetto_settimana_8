package com;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import contextManager.DummyContextManager;
import dao.ContoCorrenteDAO;
import dao.ContoCorrenteDAOImpl;
import data.ContoCorrente;

@Path("/Conto")
public class ContoCorrenteRest {
	ContoCorrenteDAO dao;

	public ContoCorrenteRest() {
		dao=new ContoCorrenteDAOImpl();
	}

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertConto(ContoCorrente conto) {
		return dao.insertConto(conto);
	}

	@PUT
	@Path("/edit")
	@Produces(MediaType.APPLICATION_JSON)
	public Response editConto(
			@QueryParam("iban") long iban,
			@QueryParam("nome")String nome,
			@QueryParam("saldo")Long saldo,
			@QueryParam("cognome")String cognome,
			@QueryParam("codiceFiscale")String codiceFiscale) {
		return dao.editConto(iban,nome,saldo,cognome,codiceFiscale);
	}

	@DELETE
	@Path("/deleteByIban")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteConto(@QueryParam("iban") long iban) {
		return dao.deleteConto(iban);
	}
	
	@GET
	@Path("/getContoByIban")
	@Produces(MediaType.APPLICATION_JSON)
	public ContoCorrente getContoByIban(@QueryParam("iban") long iban) {
		return dao.getContoByIban(iban);
	}
	
	@GET
	@Path("/getContiByCognome")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getUserByCognome(@QueryParam("cognome") String cognome) {
		return dao.getUserByCognome(cognome);
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContoCorrente> getAll() {
		return dao.getAll();
	}
	
	@PUT
	@Path("/versamento")
	@Produces(MediaType.APPLICATION_JSON)
	public Response versamentoConto(@QueryParam("iban") long iban,@QueryParam("valore") long valore) {
		return dao.versamentoConto(iban, valore);
	}
	@PUT
	@Path("/prelievo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prelievoConto(@QueryParam("iban") long iban,@QueryParam("valore") long valore) {
		return dao.prelievoConto(iban, valore);
	}
	@GET
	@Path("/getMovimenti")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getMovimenti(@QueryParam("iban") long iban) {
		return dao.getMovimenti(iban);
	}
}
