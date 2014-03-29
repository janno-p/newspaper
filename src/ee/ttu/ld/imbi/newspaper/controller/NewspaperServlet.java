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
        if (request.getParameterMap().containsKey("id")) {
            int id = parseId(request.getParameter("id"));

            Newspaper newspaper = null;
            if (id > 0) {
                newspaper = newspaperDao.findById(id);
            }

            if (newspaper == null) {
                redirect("/error.jsp", request, response);
            } else {
                request.setAttribute("newspaper", newspaper);
                redirect("/newspaper.jsp", request, response);
            }
        } else {
            Newspaper[] newspapers = newspaperDao.findAll();
            request.setAttribute("newspapers", newspapers);
            redirect("/newspapers.jsp", request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void redirect(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher(page).forward(request, response);
    }

    private static int parseId(String strId) {
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException exception) {
            return 0;
        }
    }
}
