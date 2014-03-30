package ee.ttu.ld.imbi.newspaper.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ee.ttu.ld.imbi.newspaper.db.NewspaperDao;
import ee.ttu.ld.imbi.newspaper.model.Newspaper;
import ee.ttu.ld.imbi.newspaper.util.StringUtil;

@WebServlet("/newspaperservice")
public class NewspaperService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(NewspaperService.class);

    private final NewspaperDao newspaperDao = new NewspaperDao();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("NewspaperService.doGet(): " + request.getRequestURI() + "?" + request.getQueryString());

        int newspaperId = StringUtil.parseId(request.getParameter("id"));
        Newspaper newspaper = newspaperDao.findById(newspaperId);

        response.setContentType("text/plain; charset=utf-8");
        byte[] utf8JsonString = gson.toJson(newspaper).getBytes("UTF8");
        response.getOutputStream().write(utf8JsonString, 0, utf8JsonString.length);
    }
}
