package clienteweb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.model.Cliente;
import br.com.fabricadeprogramador.service.ClienteService;

@WebServlet(urlPatterns = { "/cliente", "/clienteServlet", "/clienteController" })
public class ClienteServlet extends HttpServlet {

	ClienteService clienteService;

	public ClienteServlet() {
		System.out.println("Construindo o servlet...");
	}

	@Override
	public void init() throws ServletException {
		clienteService = new ClienteService();
		System.out.println("Iniciando o Servlet");
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Chamou o service...");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String i = req.getParameter("i");
		if (i!= null && i!= "") {
			clienteService.excluir(Integer.parseInt(i));
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");

		req.setAttribute("lista", clienteService.getTodosClientes());

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Recebendo email
		String email = req.getParameter("email");

		// colocando email em um objeto cliente
		Cliente cli = new Cliente();
		cli.setEmail(email);

		// adicionano o objeto cliente na lista de cliente

		clienteService.cadastrar(cli);

		// System.out.println("Chamou pelo m�todo Post!!");
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("msg", "Cadastrado com sucesso!");
		req.setAttribute("lista", clienteService.getTodosClientes());
		dispatcher.forward(req, resp);

		// resp.sendRedirect("cliente");
		// resp.setCharacterEncoding("UTF-8");
		// resp.getWriter().print("Chamou pelo m�todo Post enviando e-mail:" +
		// email + "!");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/// ???????
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ????
	}

	@Override
	public void destroy() {
		System.out.println("Servlet est� sendo destruido");
		super.destroy();
	}
}
