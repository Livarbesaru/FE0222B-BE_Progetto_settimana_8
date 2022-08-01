package data;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Path;

public class ContoCorrente {
	private long iban;
	private float saldo;
	private String data;
	private Intestatario intestatario;
	List<String> movimenti;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public long getIban() {
		return iban;
	}
	public void setIban(long iban) {
		this.iban = iban;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public Intestatario getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(Intestatario intestatario) {
		this.intestatario = intestatario;
	}
	public List<String> getMovimenti() {
		return movimenti;
	}
	public void setMovimenti(List<String> movimenti) {
		this.movimenti = movimenti;
	}
}
