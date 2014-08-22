package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.CommandExecutor;

import domain.StepOption;
import domain.User;

/**
 * Servlet implementation class EditOrderStepServlet
 */
@WebServlet(description = "servlet to edit step option", urlPatterns = { "/EditStepOptionServlet" })
public class EditStepOptionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditStepOptionServlet() {
        super();
    }
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			RequestDispatcher rd;
			   
			if(user != null){
				Long stepId = Long.valueOf(request.getParameter("stepId"));
				Long typeId = Long.valueOf(request.getParameter("typeId"));
				Long optionId = Long.valueOf(request.getParameter("optionId"));
				Integer priceMode = Integer.valueOf(request.getParameter("priceMode"));
				StepOption optionInfo = (StepOption)CommandExecutor.getInstance().executeDatabaseCommand(new command.SelectStepOption(optionId));
				
				request.setAttribute("optionInfo", optionInfo);
				
				request.setAttribute("stepId", stepId);
				request.setAttribute("typeId", typeId);
				request.setAttribute("optionId", optionId);
				request.setAttribute("priceMode", priceMode);
				
				if(priceMode == 1){
					rd = getServletContext().getRequestDispatcher("/admin/editStepOption.jsp");	
					rd.forward(request, response);
					
				} else if(priceMode == 2){					
					rd = getServletContext().getRequestDispatcher("/admin/editCupcakeStepOption.jsp");
					rd.forward(request, response);
					
				} else if(priceMode == 3){							
					rd = getServletContext().getRequestDispatcher("/admin/editCakeStepOption.jsp");
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
		
		RequestDispatcher rd;
		Long typeId = Long.valueOf(request.getParameter("txtTypeId"));
		Long stepId = Long.valueOf(request.getParameter("txtStepId"));
		Integer priceMode = Integer.valueOf(request.getParameter("txtPriceMode"));
		
		try{
			Long optionId = Long.valueOf(request.getParameter("txtOptionId"));
			String name = request.getParameter("txtName");
			String description = request.getParameter("txtDescription");
			Integer oldPosition = Integer.valueOf(request.getParameter("txtCurrentPosition"));
			Integer position = Integer.valueOf(request.getParameter("txtPosition"));
			Double price = Double.valueOf(request.getParameter("txtPrice"));
			Double priceTwo = 0.0;
			Double priceThree = 0.0;
			Double priceFour = 0.0;
			
			int isUnavailable = 0;
			
			if (request.getParameter("txtIsUnavailable") != null)
				isUnavailable = 1;		
			
			if (request.getParameter("txtPrice2")!= null)
				priceTwo = Double.valueOf(request.getParameter("txtPrice2"));
			
			if (request.getParameter("txtPrice3") != null)
				priceThree = Double.valueOf(request.getParameter("txtPrice3"));
			
			if (request.getParameter("txtPrice4") != null)
				priceFour = Double.valueOf(request.getParameter("txtPrice4"));
						
			StepOption option = new StepOption();
			option.setId(optionId);
			option.setOrderStepId(stepId);
			option.setName(name);
			option.setDescription(description);
			option.setPrice(price);
			option.setPriceTwo(priceTwo);
			option.setPriceThree(priceThree);
			option.setPriceFour(priceFour);
			option.setPosition(position);
			option.setOldPosition(oldPosition);
			option.setUnavailable(isUnavailable);			
			
			Integer rowsUpdated = (Integer) CommandExecutor.getInstance().executeDatabaseCommand(new command.EditStepOption(option));
			
			if(rowsUpdated == 1){
				request.setAttribute("info", "La opción fue editada exitosamente.");
				request.setAttribute("error", "");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);	
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);				

				rd.forward(request, response);
			} else {
				request.setAttribute("info", "");
				request.setAttribute("error", "Ocurrió un error durante la edición de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
				//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);	
				rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);							

				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			request.setAttribute("info", "");
			request.setAttribute("error", "Ocurrió un error durante la edición de la opción. Por favor intente de nuevo y si el error persiste contacte a su administrador.");
			//rd = getServletContext().getRequestDispatcher("/servlet/servlet.ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);		
			rd = getServletContext().getRequestDispatcher("/ListStepOptionsServlet?typeId="+typeId+"&stepId="+stepId+"&priceMode="+priceMode);					

			rd.forward(request, response);
		}
	}
}
