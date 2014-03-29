package ee.ttu.ld.imbi.newspaper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ee.ttu.ld.imbi.newspaper.db.NewspaperDao;
import ee.ttu.ld.imbi.newspaper.model.Newspaper;

@WebServlet("/s")
public class NewspaperServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final NewspaperDao newspaperDao = new NewspaperDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Newspaper[] newspapers = newspaperDao.findAll();
        request.setAttribute("newspapers", newspapers);
        getServletConfig().getServletContext().getRequestDispatcher("/Newspaper.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
