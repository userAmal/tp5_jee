package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.IReservationDAO;
import dao.ReservationDaoImpl;
import metier.entities.Reservation;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {
	IReservationDAO metier;

	@Override
	public void init() throws ServletException {
		metier = new ReservationDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/index.do")) {
			request.getRequestDispatcher("reservation.jsp").forward(request, response);
		} else if (path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			ReservationModele model = new ReservationModele();
			model.setMotCle(motCle);
			List<Reservation> res = metier.ReservationsParMC(motCle);
			model.setReservations(res);
			request.setAttribute("model", model);
			request.getRequestDispatcher("reservation.jsp").forward(request, response);
		} else if (path.equals("/saisie.do")) {
			request.getRequestDispatcher("saisieReservation.jsp").forward(request, response);
		} else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Reservation p = metier.save(new Reservation(nom, prix));
			request.setAttribute("reservation", p);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		} else if (path.equals("/supprimer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			metier.deleteReservation(id);
			response.sendRedirect("chercher.do?motCle=");
		} else if (path.equals("/editer.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			Reservation r = metier.getReservation(id);
			request.setAttribute("reservation", r);
			request.getRequestDispatcher("editerReservation.jsp").forward(request, response);
		} else if (path.equals("/update.do")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nom");
			double prix = Double.parseDouble(request.getParameter("prix"));
			Reservation r = new Reservation();
			r.setIdReservation(id);
			r.setNomClient(nom);
			r.setPrix(prix);
			metier.updateReservation(r);
			request.setAttribute("reservation", r);
			request.getRequestDispatcher("confirmation.jsp").forward(request, response);
		}

		else {
			response.sendError(Response.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}