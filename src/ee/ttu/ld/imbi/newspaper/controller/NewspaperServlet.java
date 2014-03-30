package ee.ttu.ld.imbi.newspaper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ee.ttu.ld.imbi.newspaper.db.NewspaperDao;
import ee.ttu.ld.imbi.newspaper.model.Newspaper;
import ee.ttu.ld.imbi.newspaper.util.StringUtil;

@WebServlet("/s")
public class NewspaperServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(NewspaperServlet.class);

    private final NewspaperDao newspaperDao = new NewspaperDao();

    @Override
    public void init() {
        logger.info("NewspaperServlet.init(): servlet loodud.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("NewspaperServlet.doGet(): " + request.getRequestURI() + "?" + request.getQueryString());

        if (!request.getParameterMap().containsKey("id")) {
            Newspaper[] newspapers = newspaperDao.findAll();
            request.setAttribute("newspapers", newspapers);
            render("/newspapers.jsp", request, response);
            return;
        }

        int id = StringUtil.parseId(request.getParameter("id"));
        Newspaper newspaper = newspaperDao.findById(id);

        if (newspaper == null) {
            logger.warn("NewspaperServlet.doGet(): ei leitud sobivat ajalehe kirjet id-le `" + request.getParameter("id") + "`.");
            render("/error.jsp", request, response);
            return;
        }

        request.setAttribute("newspaper", new NewspaperForm(newspaper));
        render("/newspaper.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        logger.info("NewspaperServlet.doPost(): " + request.getRequestURI() + "?" + request.getQueryString());

        NewspaperForm form = new NewspaperForm(request);
        NewspaperValidator validator = new NewspaperValidator(form);

        if (validator.getErrors().containsKey("id") || newspaperDao.findById(StringUtil.parseId(form.getId())) == null) {
            logger.warn("NewspaperServlet.doPost(): ei leitud sobivat ajalehe kirjet id-le `" + request.getParameter("id") + "`.");
            render("/error.jsp", request, response);
            return;
        }

        if (validator.getErrors().size() < 1) {
            Newspaper newspaper = form.toNewspaper();
            newspaperDao.update(newspaper);
            request.setAttribute("success", true);
        }

        request.setAttribute("errors", validator.getErrors());
        request.setAttribute("newspaper", form);
        render("/newspaper.jsp", request, response);
    }

    private void render(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
