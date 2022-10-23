import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class kredytBB {
	private String kwota;
	private String lata;
	private String oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;
	
	public String getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(String oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getLata() {
		return lata;
	}

	public void setLata(String lata) {
		this.lata = lata;
	}

	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}

	public boolean liczRate() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double lata = Double.parseDouble(this.lata);
			double oprocentowanie = Double.parseDouble(this.oprocentowanie);
			
			double procent = (oprocentowanie / 100) * kwota;
			double splata = kwota + procent;
			
			rata = splata / (lata * 12);
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana prawidłowo", null));
			
			return true;
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			
			return false;
		}
	}
	
	public String licz() {
		if(liczRate()) {
			return "showresult";
		}
		return null;
	}

	
	// Put result in messages on AJAX call
	public String licz_AJAX() {
		if (liczRate()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rata miesięczna: " + rata, null));
		}
		return null;
	}

//	public String info() {
//		return "info";
//	}
}
