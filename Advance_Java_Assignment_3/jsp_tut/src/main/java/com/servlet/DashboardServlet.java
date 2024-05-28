package com.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.connection.HibernateUtil;
import com.dao.ProductDAO;
import com.entity.Products;


@WebServlet("/Dashboard")
@MultipartConfig
public class DashboardServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String title = req.getParameter("title");
		String quantity = req.getParameter("quantity");
		String size = req.getParameter("size");
		Part imagePart = req.getPart("image");
		byte[] imageBytes = null;
		if (imagePart != null && imagePart.getSize() > 0) {
			if (imagePart.getSize() <= 1 * 1024 * 1024) {
				imageBytes = imagePart.getInputStream().readAllBytes();
				ProductDAO pdao=new ProductDAO(HibernateUtil.getSessionFactory());
				Products product = new Products(title, size, quantity, imageBytes);
				boolean flag = pdao.addProduct(product);
				if(flag == true) {
					System.out.println("Product Added");
					req.setAttribute("message", "Successfully Added");
				}else {
					System.out.println("Something Went Wrong");
				}
				
			} else {
				req.setAttribute("message", "Image must be less than 1 MB");
			}
		}
		req.getRequestDispatcher("Dashboard.jsp").forward(req, resp);

		
	}	
}