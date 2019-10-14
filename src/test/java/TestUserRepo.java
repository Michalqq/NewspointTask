import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Collections;

public class TestUserRepo {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    UserRepo userRepo = new UserRepo();
    User user1;
    User user2;

    @Before
    public void inputRepo() {
        String inputText = "Marcin, Nowak, 2000-10-10, 515-123-456";
        userRepo.fillUserData(inputText);
        inputText = "Agnieszka, Abc, 1990-01-05";
        userRepo.fillUserData(inputText);
        inputText = "Anna, Polska, 1994-01-05, 700-150-150";
        userRepo.fillUserData(inputText);
    }

    @Before
    public void sampleUsers() {
        user1 = new User("Agnieszka", "Abc", LocalDate.of(1990, 01, 05), 0);
        user2 = new User("Anna", "Polska", LocalDate.of(1994, 01, 05), 700150150);
    }

    @Test
    public void fillUserDataTest() {
        Assert.assertTrue(userRepo.getTheOldestUser().equals(user1));
        Assert.assertTrue(userRepo.getTheOldestUserWherePhoneNumberExist().equals(user2));
    }

    @Test
    public void phoneNumberCorrect() {
        Collections.sort(userRepo.getUsers(), Collections.reverseOrder(User.UserAgeComparator));
        Assert.assertEquals(userRepo.getUsers().get(0).getPhoneNumber(), 0);
        Assert.assertEquals(userRepo.getUsers().get(1).getPhoneNumber(), 700150150);
    }

    @Test
    public void duplicateUserTest() {
        int userAmount = userRepo.getUsers().size();
        userRepo.addNewUserToArr("Anna", "Polska", LocalDate.of(1994, 01, 05), 700150150);
        Assert.assertEquals(userRepo.getUsers().size(), userAmount);

        userAmount++;
        userRepo.addNewUserToArr("Anna", "Polska", LocalDate.of(2005, 05, 25), 515890890);
        Assert.assertEquals(userRepo.getUsers().size(), userAmount);
    }

    @Test
    public void checkBirthdayCorrecntess() {
        int userAmount = userRepo.getUsers().size();
        userRepo.addNewUserToArr("Anna", "Polska", LocalDate.of(3205, 05, 25), 515890890);
        Assert.assertEquals(userRepo.getUsers().size(), userAmount);
    }

    @Test
    public void checkPhoneCorrect() {
        userRepo.addNewUserToArr("Magda", "Zduńska", LocalDate.of(2005, 05, 10), 158158);
        Assert.assertEquals(0,userRepo.getUsers().get(userRepo.getUsers().size()-1).getPhoneNumber());
    }

}
