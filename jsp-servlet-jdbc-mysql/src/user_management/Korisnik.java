package user_management;

public class Korisnik {
    protected int id;
    protected String ime;
    protected String email;
    protected String zemlja;

    public Korisnik() {}

    public Korisnik(String ime, String email, String zemlja) {
        super();
        this.ime = ime;
        this.email = email;
        this.zemlja = zemlja;
    }

    public Korisnik(int id, String ime, String email, String zemlja) {
        super();
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.zemlja = zemlja;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getZemlja() {
        return zemlja;
    }
    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }
}