package user.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user_management.Korisnik;
import user_management.KorisnikDAO;



@WebServlet("/")
public class KorisnikServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private KorisnikDAO korisnikDAO;

   

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertKorisnik(request, response);
                    break;
                case "/delete":
                    deleteKorisnik(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateKorisnik(request, response);
                    break;
                default:
                    listKorisnik(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

  

	private void listKorisnik(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Korisnik > listKorisnik = KorisnikDAO.selectAllKorisnici();
        request.setAttribute("listKorisnik",listKorisnik);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListaKorisnika.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("KorisnikForma.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Korisnik postojeciKorisnik = KorisnikDAO.selectKorisnik(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("KorisnikForma.jsp");
        request.setAttribute("korisnik", postojeciKorisnik);
        dispatcher.forward(request, response);

    }

    private void insertKorisnik(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String ime = request.getParameter("ime");
        String email = request.getParameter("email");
        String zemlja = request.getParameter("zemlja");
        Korisnik noviKorisnik = new Korisnik(ime, email,zemlja);
        KorisnikDAO.insertKorisnik(noviKorisnik);
        response.sendRedirect("list");
    }

    private void updateKorisnik(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ime = request.getParameter("ime");
        String email = request.getParameter("email");
        String zemlja = request.getParameter("zemlja");

        Korisnik book = new Korisnik(id, ime, email, zemlja);
        KorisnikDAO.updateKorisnik(book);
        response.sendRedirect("list");
    }

    private void deleteKorisnik(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        KorisnikDAO.deleteKorisnik(id);
        response.sendRedirect("list");

    }

	public KorisnikDAO getKorisnikDAO() {
		return korisnikDAO;
	}

	public void setKorisnikDAO(KorisnikDAO korisnikDAO) {
		this.korisnikDAO = korisnikDAO;
	}
	
	public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
}
}