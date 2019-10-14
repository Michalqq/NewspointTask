import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestUser {


    @Test
    public void testAgeCounter() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(10);
        User user = new User("Adam", "Kowalski", dateOfBirth, 500150150);
        Assert.assertTrue(user.getAge() == 10);
    }
}
