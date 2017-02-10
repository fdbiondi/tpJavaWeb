package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Personaje;
import negocio.ControladorPersonaje;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPersonaje ctrlPj = new ControladorPersonaje();
		
		ArrayList<Personaje> pjs = ctrlPj.traerTodos();
		request.setAttribute("pjs", pjs);
		//request.setAttribute("message", "hello");
	    RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/index.jsp");
	    view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorPersonaje ctrlPj = new ControladorPersonaje();
		
		Personaje p1 = ctrlPj.traerPor(Integer.parseInt(request.getParameter("player1")));
		Personaje p2 = ctrlPj.traerPor(Integer.parseInt(request.getParameter("player2")));
		
		request.getSession().setAttribute("P1", p1);
		request.getSession().setAttribute("P2", p2);
		
		request.getRequestDispatcher("WEB-INF/partida.jsp").forward(request, response);
	}
}
