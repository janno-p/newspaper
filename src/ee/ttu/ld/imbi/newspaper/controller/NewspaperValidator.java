package ee.ttu.ld.imbi.newspaper.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class NewspaperValidator {
    private static final Logger logger = Logger.getLogger(NewspaperValidator.class);

    private final HashMap<String, String> errors = new HashMap<String, String>();

    public NewspaperValidator(NewspaperForm newspaperForm) {
        try {
            Integer.parseInt(newspaperForm.getId());
        } catch (NumberFormatException exception) {
            errors.put("id", "Ajalehe id ei ole korrektne.");
            logger.warn("NewspaperValidator(): ajalehe id `" + newspaperForm.getId() + "` ei ole korrektne.");
        }

        if (newspaperForm.getName() == null || newspaperForm.getName().length() < 1) {
            errors.put("name", "Ajalehe nimi on kohustuslik märkida.");
            logger.warn("NewspaperValidator(): ajalehe nimi on kohustuslik.");
        } else if (newspaperForm.getName().length() > 50) {
            errors.put("name", "Ajalehe nimi võib olla maksimaalselt 50 sümbolit pikk.");
            logger.warn("NewspaperValidator(): ajalehe nimi `" + newspaperForm.getName() + "` on liiga pikk.");
        }

        String dateFormat = "dd.MM.yyyy";
        if (newspaperForm.getFoundedAt() == null || newspaperForm.getFoundedAt().length() < 1) {
            errors.put("foundedAt", "Ajalehe esimese väljaande kuupäev on kohustuslik märkida.");
            logger.warn("NewspaperValidator(): ajalehe esimese väljaande kuupäev on kohustuslik.");
        } else if (newspaperForm.getFoundedAt().length() != dateFormat.length()) {
            addWrongFormatError(newspaperForm.getFoundedAt());
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date parsedDate = format.parse(newspaperForm.getFoundedAt());
                if (!format.format(parsedDate).equals(newspaperForm.getFoundedAt())) {
                    addWrongFormatError(newspaperForm.getFoundedAt());
                }
            } catch (ParseException exception) {
                addWrongFormatError(newspaperForm.getFoundedAt());
            }
        }
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    private void addWrongFormatError(String foundedAt) {
        errors.put("foundedAt", "Ajalehe esimese väljaande kuupäev on vigasel kujul.");
        logger.warn("NewspaperValidator(): ajalehe esimese väljaande kuupäev `" + foundedAt + "` on vigane.");
    }
}
