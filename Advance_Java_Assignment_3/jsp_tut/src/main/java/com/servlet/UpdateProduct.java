package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.SessionFactory;

import com.connection.HibernateUtil;
import com.dao.ProductDAO;
import com.entity.Products;

@WebServlet("/UpdateProduct")
@MultipartConfig
public class UpdateProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idString = (String) session.getAttribute("id");
		System.out.println("ID is: " + idString);
		Part imagePart = request.getPart("image");
		byte[] imageBytes = null;
		// check if image is uploaded
		if (imagePart != null && imagePart.getSize() > 0) {
			if (imagePart.getSize() <= 1 * 1024 * 1024) {
				imageBytes = imagePart.getInputStream().readAllBytes();
				request.setAttribute("message", "Successfully Updated");
			} else {
				request.setAttribute("message", "Image must be less than 1 MB");
			}
		}

		// Retrieve the existing Products object from the database using its ID
		ProductDAO productDAO = new ProductDAO(HibernateUtil.getSessionFactory());
		int productId = Integer.parseInt(idString);
		Products products = productDAO.getProductById(productId);
		String title = request.getParameter("title");
		String quantity = request.getParameter("quantity");
		String size = request.getParameter("size");
		System.out.println(products.getTitle());
		System.out.println(products.getQuantity());
		System.out.println(products.getSize());
		System.out.println(products.getImage());
		products.setTitle(title);
		products.setQuantity(quantity);
		products.setSize(size);
		// byte[] oldImage = products.getImage();
		// update image only if it is uploaded
		if (imageBytes != null) {
			products.setImage(imageBytes);
		}

		productDAO.updateProduct(products);
		System.out.println(products.getTitle());
		System.out.println(products.getQuantity());
		System.out.println(products.getSize());
		request.setAttribute("message", "Product Updated Succcessfully");
		request.getRequestDispatcher("Dashboard.jsp").forward(request, response);

	}
}