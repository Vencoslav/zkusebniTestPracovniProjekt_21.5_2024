import java.math.BigDecimal;
import java.time.LocalDate;

public class Projekt {
    private String nazev;
    private Integer pocetResitelu;
    private BigDecimal naklady;
    private Integer hodnoceni;
    private LocalDate datum;
    private Boolean dokonceno;

    public Projekt(String nazev, Integer pocetResitelu, BigDecimal naklady, Integer hodnoceni, LocalDate datum, Boolean dokoceno){
        this.nazev = nazev;
        this.pocetResitelu = pocetResitelu;
        this.naklady = naklady;
        this.hodnoceni = hodnoceni;
        this.datum = datum;
        this.dokonceno = dokoceno;
    }

    public String getNazev(){
        return nazev;
    }

    public Integer getPocetResitelu() {
        return pocetResitelu;
    }

    public BigDecimal getNaklady() {
        return naklady;
    }

    public Integer getHodnoceni() {
        return hodnoceni;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Boolean getDokonceno() {
        return dokonceno;
    }

    public void setNazev(String nazev){
        this.nazev = nazev;
    }

    public void setPocetResitelu(Integer pocetResitelu) {
        this.pocetResitelu = pocetResitelu;
    }

    public void setNaklady(BigDecimal naklady) {
        this.naklady = naklady;
    }

    public void setHodnoceni(Integer hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setDokonceno(Boolean dokonceno) {
        this.dokonceno = dokonceno;
    }
}
