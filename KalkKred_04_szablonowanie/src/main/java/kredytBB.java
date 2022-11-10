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
	private Integer kwota;
	private Integer lata;
	private Double oprocentowanie;
	private Double rata;

	@Inject
	FacesContext ctx;

	public Integer getKwota() {
		return kwota;
	}

	public void setKwota(Integer kwota) {
		this.kwota = kwota;
	}

	public Integer getLata() {
		return lata;
	}

	public void setLata(Integer lata) {
		this.lata = lata;
	}

	public Double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public Double getRata() {
		return rata;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}

	public boolean liczRate() {
		try {			
			double procent = (oprocentowanie / 100) * kwota;
			double splata = kwota + procent;
			
			rata = splata / (lata * 12);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana prawidłowo", null));
			
			return true;
		} catch (Exception e) {			
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
