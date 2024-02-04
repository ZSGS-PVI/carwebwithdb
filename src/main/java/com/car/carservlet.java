package com.car;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class carservlet
 */
@WebServlet("/carservlet")
public class carservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		"SELECT * FROM user WHERE userId=? AND password=?"
	    PrintWriter out = response.getWriter();
	    String name = request.getParameter("fullName");
	    String Email = request.getParameter("email");
	    String number = request.getParameter("phoneNumber");
	    String address = request.getParameter("address");
	    String carmodel = request.getParameter("carModel");
	    String color = request.getParameter("preferredColor");
	    String budget = request.getParameter("budget");
	    

	    String url = "jdbc:mysql://localhost:3306/demoApp";
	    String username = "kalpana";
	    String password = "Dev@2277";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection con = DriverManager.getConnection(url, username, password);
	             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO orderscar(fullName,email,phoneNumber,address,carModel,preferredColor,budget) VALUES(?,?,?,?,?,?,?)")) {

	            preparedStatement.setString(1,name );
	            preparedStatement.setString(2, Email);
	            preparedStatement.setString(3, number);
	            preparedStatement.setString(4, address);
	            preparedStatement.setString(5, carmodel);
	            preparedStatement.setString(6, color);
	            preparedStatement.setString(7, budget);
	           
	            
	            
//	            to   login checking

//	            try (ResultSet rs = preparedStatement.executeQuery()) {
//	                if (rs.next()) {
//	                    out.print("<h1>" + userId + ": welcome to the home page</h1><br>");
//	                    out.print("<h1>Login successfully done.</h1>");
//	                } else {
//	                    out.print("<h1>" + userId + ": please give correct userid and password</h1><br>");
//	                    out.print("<h1>Login Failed!</h1>");
//	                }
//	            }
	            int rowsAffected=preparedStatement.executeUpdate();
	            
	            if(rowsAffected>0) {
	            	out.print("<h1>"+name+":inserted successfully</h1>");
	            }
	            else {
	            	out.print("<h1>"+name+":Not properly inserted</h1>");
	            }
	        } catch (SQLException e) {
	            out.print("<h1>Login Failed! because of server exception</h1>");
	            // Log the exception instead of printing the stack trace
	            e.printStackTrace();
	        }

	    } catch (ClassNotFoundException e) {
	        out.print("<h1>Login Failed! because of server exception</h1>");
	        // Log the exception instead of printing the stack trace
	        e.printStackTrace();
	    }
	}
}
