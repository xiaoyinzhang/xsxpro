package shu.nova.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Transaction;

import shu.nova.model.DuplicateData;
import shu.nova.model.DuplicateDataDAO;
import shu.nova.model.LongLatData;
import shu.nova.model.LongLatDataDAO;
import shu.nova.model.TaskFileData;
import shu.nova.model.TaskFileDataDAO;


public class RefreshData extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RefreshData() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String uuidStr=request.getParameter("uuidStr");
			String txtPath=request.getParameter("txtPath");
			String txtDownloadPath=request.getParameter("txtDownloadPath");
			String xlsPath=request.getParameter("xlsPath");
			String xlsDownloadPath=request.getParameter("xlsDownloadPath");
			String back=request.getParameter("back");
			
		System.out.println("-------------------------------------------");
			try{
					
				request.setAttribute("uuidStr", uuidStr);
		  		request.setAttribute("txtPath", txtPath);
		  		request.setAttribute("txtDownloadPath", txtDownloadPath);
		  		request.setAttribute("xlsPath", xlsPath);
		  		request.setAttribute("xlsDownloadPath", xlsDownloadPath);
		  		request.setAttribute("back", back);
		  		request.setAttribute("flag", "refresh");	
		  		request.getRequestDispatcher("index.jsp").forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
			
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
