package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;
import domain.OrderType;
import domain.StepOption;
import domain.User;

/**
 * Servlet implementation class ListStepOptionsServlet
 */
@WebServlet(description = "servlet to list step options", urlPatterns = { "/ListStepOptionsServlet" })
public class ListStepOptionsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStepOptionsServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			RequestDispatcher rd;
			
			if(user != null){
				// perform list user operations
				Integer typeId = Integer.valueOf(request.getParameter("typeId"));
				Integer stepId = Integer.valueOf(request.getParameter("stepId"));
				Integer priceMode = Integer.valueOf(request.getParameter("priceMode"));
				String info = (String)request.getAttribute("info")!=null?(String)request.getAttribute("info"):"";
				String error = (String)request.getAttribute("error")!=null?(String)request.getAttribute("error"):"";
				@SuppressWarnings("unchecked")
				ArrayList<StepOption> list = (ArrayList<StepOption>)CommandExecutor.getInstance().executeDatabaseCommand(new command.ListStepOptions(stepId));
				OrderType typeInfo = (OrderType)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectOrderType(typeId));

				request.setAttribute("options", list);
				request.setAttribute("typeInfo", typeInfo);
				request.setAttribute("stepId", stepId);
				request.setAttribute("priceMode", priceMode);
				request.setAttribute("info", info);
				request.setAttribute("error", error);
				
				if(priceMode == 1){
					rd = getServletContext().getRequestDispatcher("/admin/stepOptions.jsp");
					rd.forward(request, response);
					
				} else if(priceMode == 2){					
					rd = getServletContext().getRequestDispatcher("/admin/cupcakeStepOptions.jsp");
					rd.forward(request, response);
					
				} else if(priceMode == 3){							
					rd = getServletContext().getRequestDispatcher("/admin/cakeStepOptions.jsp");
					rd.forward(request, response);
					
				}

			} else {
				rd = getServletContext().getRequestDispatcher("/admin/index.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
