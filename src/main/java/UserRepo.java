import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserRepo {
    private List<User> users = new ArrayList<>();

    public UserRepo(List<User> users) {
        this.users = users;
    }

    public UserRepo() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addNew(String name, String surName, LocalDate dateOfBirth, int phoneNumber) {
        User user = new User(name, surName, dateOfBirth, phoneNumber);
        if (!users.contains(user)) {
            users.add(user);
        }
    }


    public User getTheOldestUserWherePhoneNumberExist() {
        Collections.sort(users, Collections.reverseOrder(User.UserAgeComparator));
        for (User user : users) {
            if (user.getPhoneNumber() > 100000000) {
                return user;
            }
        }
        return (new User());
    }

    public User getTheOldestUser() {
        Collections.sort(users, Collections.reverseOrder(User.UserAgeComparator));
        if (users.size() > 0) return users.get(0);
        else return (new User());
    }

    public void setUserRepoFromFile(Scanner sc) {
        List<User> users = new ArrayList<User>();
        while (sc.hasNextLine()) {
            fillUserData(sc.nextLine());

        }
    }

    public void fillUserData(String text) {
        String[] inputValue = text.replaceAll(" ", "").split(",");
        if (inputValue.length > 4) System.out.println("Plik wejściowy ma zbyt dużo danych w 1 linii");
        if (inputValue.length > 2) {
            String name = inputValue[0].replaceAll("\\p{IsDigit}", "");
            String surName = inputValue[1].replaceAll("\\p{IsDigit}", "");
            LocalDate dateOfBirth = LocalDate.parse(inputValue[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Integer phoneNumber = 0;
            if (inputValue.length > 3) {
                phoneNumber = Integer.parseInt(inputValue[3].replaceAll("\\D+", ""));
            }
            addNewUserToArr(name, surName, dateOfBirth, phoneNumber);
        }
    }

    public boolean addNewUserToArr(String name, String surName, LocalDate dateOfBirth, int phoneNumber) {
        if (name.length() < 1 || surName.length() < 1) {
            System.out.println("Input data are incomplete. Name or Surname is empty. This user won't be added");
            return false;
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            System.out.println("Birthdate is after today's day. Data are incorrect. This user won't be added");
            return false;
        }
        if (phoneNumber != 0 && phoneNumber < 100000000) {
            System.out.println("Phone number is incorrect. This user will have empty phone number");
            phoneNumber = 0;
        }
        this.addNew(name, surName, dateOfBirth, phoneNumber);
        return true;
    }
}
