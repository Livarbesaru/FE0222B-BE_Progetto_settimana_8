package dao;
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
import data.ContoCorrente;

public class ContoCorrenteDAOImpl implements ContoCorrenteDAO {
	
	DummyContextManager em;
	
	public ContoCorrenteDAOImpl() {
		em=new DummyContextManager();
	}
	
	@Override
	public Response insertConto(ContoCorrente conto) {
		return em.insert(conto);
	}

	
	@Override
	public Response deleteConto(long iban) {
		return em.remove(iban);
	}
	
	
	@Override
	public ContoCorrente getContoByIban(long iban) {
		return em.findByIban(iban);
	}
	
	
	@Override
	public List<ContoCorrente> getUserByCognome(String cognome) {
		return em.findByCognome(cognome);
	}

	
	@Override
	public List<ContoCorrente> getAll() {
		return em.getAll();
	}

	
	@Override
	public Response versamentoConto(long iban,long valore) {
		return em.versamentoConto(iban, valore);
	}

	
	@Override
	public Response prelievoConto(long iban,long valore) {
		return em.prelievoConto(iban, valore);
	}
	
	@Override
	public List<String> getMovimenti(long iban) {
		return em.getMovimenti(iban);
	}
	
	@Override
	public Response editConto(long iban, String nome, Long saldo, String cognome, String codiceFiscale) {
		return em.merge(iban,nome,saldo,cognome,codiceFiscale);
	}
}
