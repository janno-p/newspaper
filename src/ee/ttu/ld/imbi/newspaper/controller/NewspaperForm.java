package ee.ttu.ld.imbi.newspaper.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ee.ttu.ld.imbi.newspaper.model.Newspaper;

public class NewspaperForm {
    private static Logger logger = Logger.getLogger(NewspaperForm.class);

    private final String id;
    private final String name;
    private final String foundedAt;
    private final String description;

    public NewspaperForm(HttpServletRequest request) {
        this.id = request.getParameter("id");
        this.name = request.getParameter("name");
        this.foundedAt = request.getParameter("foundedAt");
        this.description = request.getParameter("description");
    }

    public NewspaperForm(Newspaper newspaper) {
        id = "" + newspaper.getId();
        name = newspaper.getName();
        foundedAt = new SimpleDateFormat("dd.MM.yyyy").format(newspaper.getFoundedAt());
        description = newspaper.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFoundedAt() {
        return foundedAt;
    }

    public String getDescription() {
        return description;
    }

    public Newspaper toNewspaper() {
        Newspaper newspaper = new Newspaper();
        try {
            newspaper.setId(Integer.parseInt(id));
        } catch (NumberFormatException exception) {
            logger.error("NewspaperForm.toNewspaper() - id ei ole number.", exception);
        }

        newspaper.setName(name);

        try {
            newspaper.setFoundedAt(new SimpleDateFormat("dd.MM.yyyy").parse(foundedAt));
        } catch (ParseException exception) {
            logger.error("NewspaperForm.toNewspaper() - esmaväljaande kuupäeva formaat on vigane.", exception);
        }

        newspaper.setDescription(description);

        return newspaper;
    }
}
