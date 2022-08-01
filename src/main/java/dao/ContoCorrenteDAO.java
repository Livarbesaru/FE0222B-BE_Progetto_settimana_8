package dao;

import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import data.ContoCorrente;

public interface ContoCorrenteDAO {

	Response insertConto(ContoCorrente conto);

	Response deleteConto(long iban);

	ContoCorrente getContoByIban(long iban);

	List<ContoCorrente> getUserByCognome(String cognome);

	List<ContoCorrente> getAll();

	Response versamentoConto(long iban, long valore);

	Response prelievoConto(long iban, long valore);

	List<String> getMovimenti(long iban);

	Response editConto(long iban, String nome, Long saldo, String cognome, String codiceFiscale);

}