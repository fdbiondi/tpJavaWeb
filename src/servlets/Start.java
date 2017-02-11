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
import negocio.ControladorPartida;
import negocio.ControladorPersonaje;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControladorPersonaje ctrlPj;
	private ControladorPartida ctrlPartida;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        ctrlPj = new ControladorPersonaje();
        ctrlPartida = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		boolean error = false; 
		ArrayList<Personaje> pjs = ctrlPj.traerTodos();
		request.setAttribute("pjs", pjs);
		request.setAttribute("error", error);
		request.setAttribute("finish", false);

	    RequestDispatcher view=request.getRequestDispatcher("/WEB-INF/index.jsp");
	    view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		boolean error = false;
		boolean finish = false;
		String goTo = "";
		String message = "";
		
		try {
			if (action == null) {
			    //no button has been selected
				
			} else if (action.equals("start")) {
			    //start button was pressed		
				if(Integer.parseInt(request.getParameter("player1")) == Integer.parseInt(request.getParameter("player2"))) {
					throw new Exception("Los personajes no pueden competir entre ellos mismos. Por favor seleccione diferentes personajes.");
				}
						
				ctrlPartida = new ControladorPartida(
						ctrlPj.traerPor(Integer.parseInt(request.getParameter("player1"))), 
						ctrlPj.traerPor(Integer.parseInt(request.getParameter("player2"))));
				
				request.getSession().setAttribute("P1", ctrlPartida.getPartida().getJugador1());
				request.getSession().setAttribute("P2", ctrlPartida.getPartida().getJugador2());
			
			} else if (action.equals("attack")) {
			    //attack button was pressed
				int attackVal = 0;
				try {
					if(ctrlPartida.getTurno() == ctrlPartida.getPartida().getJugador1().getId()) {
						attackVal =	Integer.parseInt(request.getParameter("points_1"));
					} else {
						attackVal =	Integer.parseInt(request.getParameter("points_2"));
					}
				}
				catch(Exception e) {
					throw new Exception("Debe de ingresar un valor numerico menor a la energia disponible para poder realizar el ataque.");
				}
				
				if(attackVal != 0) {
					if(ctrlPartida.validarEnergia(attackVal)) {
						ctrlPartida.atacar(attackVal);
					} else {
						throw new Exception("Energia insuficiente. ");
					}
				}
			} else if (action.equals("defense")) {
				//defense button was pressed
				ctrlPartida.defender();
			}
		}
		catch(Exception e) {
			error = true;
			message = e.getMessage();
		}

		if(ctrlPartida != null) {
			request.getSession().setAttribute("turno", ctrlPartida.getTurno());
			finish = ctrlPartida.validaFinPartida();
			message += ctrlPartida.getPartida().mensaje;
		}
		else {
			request.getSession().setAttribute("turno", 0);
		}

		request.setAttribute("error", error);
		request.setAttribute("message", message);
		request.setAttribute("finish", finish);
		
		if(finish || (error && action.equals("start")))
			goTo = "index";
		
		if(goTo.equals("index")) {
			ArrayList<Personaje> pjs = ctrlPj.traerTodos();
			request.setAttribute("pjs", pjs);
			
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("WEB-INF/partida.jsp").forward(request, response);
		}
	}
}
