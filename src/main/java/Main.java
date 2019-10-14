import java.io.*;

public class Main {

    public static void main(String[] args) {
        UserRepo userRepo = new UserRepo();
        try {
            userRepo.setUserRepoFromFile(new FileReader().readFile("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
            System.exit(1);
        }

        System.out.println("Liczba użytkowników: " + userRepo.getUsers().size());
        if (userRepo.getUsers().size() > 0) {
            System.out.println("Najstarszy użytkownik: " + userRepo.getTheOldestUser());
            System.out.println("Najstarszy użytkownik z wypełnionym polem telefon: " + userRepo.getTheOldestUserWherePhoneNumberExist());
        }
    }
}
