package contextManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import data.ContoCorrente;

public class DummyContextManager {
	
	private static List<ContoCorrente> conti = new ArrayList<ContoCorrente>();
	
	public Response insert(ContoCorrente conto) {
		conto.setData(LocalDate.now().toString());
		conto.setMovimenti(new ArrayList<String>());
		if(conti.size()>0) {
			conto.setIban((conti.get(conti.size()-1).getIban())+1);
			conto.getIntestatario()
			.setMatricola((conti.get(conti.size()-1).getIntestatario().getMatricola())+1);
		}
		String response = "Conto aggiunto correttamente";
		conti.add(conto);
		return Response.status(201).entity(response).build();
	}

	
	public Response merge(long iban,String nome,Long saldo,String cognome,String codiceFiscale) {
		String response = "Conto non trovato";
		for(ContoCorrente conto : conti) {
			if(conto.getIban()==iban) {
				if(nome!=null && nome.replaceAll(" ", "")!="") {
					conto.getIntestatario().setNome(nome);
				}
				if(cognome!=null && cognome.replaceAll(" ", "")!="") {
					conto.getIntestatario().setCognome(cognome);
				}
				if(codiceFiscale!=null && codiceFiscale.replaceAll(" ", "")!="") {
					conto.getIntestatario().setCodiceFiscale(codiceFiscale);
				}
				if(saldo!=null) {
					conto.setSaldo(saldo);;
				}
				response = "Conto trovato e modificato dove possibile";
			}
		}

		return Response.status(201).entity(response).build();
	}

	
	public Response remove(long iban) {
		String response = "Conto non trovato";
		int index =-1;
		for (ContoCorrente conto : conti) {
			if (conto.getIban()==iban) {
				index = conti.indexOf(conto);
				response = "Conto eliminato";
			}
		}
		conti.remove(index);
		return Response.status(201).entity(response).build();
	}
	
	
	public ContoCorrente findByIban(long iban) {
		ContoCorrente c=null;
		for (ContoCorrente conto: conti) {
			if (conto.getIban()==iban) {
				c=new ContoCorrente();
				c.setData(conto.getData());
				c.setMovimenti(conto.getMovimenti());
				c.setIban(conto.getIban());
				c.setSaldo(conto.getSaldo());
				c.setIntestatario(conto.getIntestatario());
			}
		}
		return c;
	}
	
	
	public List<ContoCorrente> findByCognome(String cognome) {
		List<ContoCorrente> c=new ArrayList<ContoCorrente>();
		for (ContoCorrente conto : conti) {
			if (conto.getIntestatario().getCognome().equals(cognome)) {
				c.add(conto);
			}
		}
		return c;
	}

	
	public List<ContoCorrente> getAll() {
		return conti.stream().collect(Collectors.toList());
	}

	
	public Response versamentoConto(long iban,long valore) {
		String response = "Conto non trovato";
		for (ContoCorrente conto : conti) {
			if (conto.getIban()==iban) {
				conto.setSaldo(conto.getSaldo()+valore);
				conto.getMovimenti().add("Versamento di: "+valore);
				response = "Versamento effettuato";
			}
		}
		return Response.status(201).entity(response).build();
	}

	
	public Response prelievoConto(long iban,long valore) {
		String response = "Conto non trovato";
		for (ContoCorrente conto : conti) {
			if (conto.getIban()==iban) {
				if(valore <= conto.getSaldo()) {
					conto.setSaldo(conto.getSaldo()-valore);
					conto.getMovimenti().add("Prelievo di: "+valore);
					response = "Prelievo effettuato";
				}
				else {
					response="Credito insufficente";
				}
			}
		}
		return Response.status(201).entity(response).build();
	}
	
	public List<String> getMovimenti(long iban) {
		ContoCorrente c=null;
		for (ContoCorrente conto : conti) {
			if (conto.getIban()==iban) {
				c=conto;
			}
		}
		return c.getMovimenti();
	}

}
