package ee.ttu.ld.imbi.newspaper.test;

import static org.junit.Assert.assertNotEquals;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ee.ttu.ld.imbi.newspaper.db.NewspaperDao;
import ee.ttu.ld.imbi.newspaper.model.Newspaper;

@RunWith(JUnit4.class)
public class NewspaperDaoTest extends TestCase {
    private NewspaperDao newspaperDao;

    @Override
    @Before
    public void setUp() {
        newspaperDao = new NewspaperDao();
    }

    @Test
    public void testUpdate() {
        Newspaper newspaper = newspaperDao.findById(1);
        assertNotNull(newspaper);

        String description = newspaper.getDescription();
        String newDescription = getNewDescription(newspaper);
        assertNotEquals(description, newDescription);

        newspaper.setDescription(newDescription);
        newspaperDao.update(newspaper);

        Newspaper newspaperAfterUpdate = newspaperDao.findById(1);
        assertNotSame(newspaper, newspaperAfterUpdate);
        assertEquals(newspaper.getId(), newspaperAfterUpdate.getId());
        assertEquals(newspaper.getName(), newspaperAfterUpdate.getName());
        assertEquals(newspaper.getFoundedAt(), newspaperAfterUpdate.getFoundedAt());
        assertEquals(newDescription, newspaperAfterUpdate.getDescription());
    }

    @Test
    public void testFindById() {
        Newspaper newspaper = newspaperDao.findById(1);
        assertNotNull(newspaper);
        assertEquals(1, newspaper.getId());
        assertNotNull(newspaper.getName());
        assertNotNull(newspaper.getFoundedAt());
    }

    @Test
    public void testFindAll() {
        Newspaper[] newspapers = newspaperDao.findAll();
        assertNotNull(newspapers);
        assertTrue(newspapers.length >= 3);
        for (Newspaper newspaper : newspapers) {
            assertNotNull(newspaper);
            assertTrue(newspaper.getId() > 0);
            assertNotNull(newspaper.getName());
            assertNotNull(newspaper.getFoundedAt());
        }
    }

    private static String getNewDescription(Newspaper newspaper) {
        String description = newspaper.getDescription();
        if (description == null || description.length() > 30) {
            return "test";
        }
        return description + " test";
    }
}
