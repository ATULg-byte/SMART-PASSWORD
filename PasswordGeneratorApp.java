import java.util.Random;
import java.util.Scanner;

public class PasswordGeneratorApp {

    
    public static String generatePassword(String name, String pan, String dob) {

        String namePart = name.substring(0, Math.min(3, name.length())).toUpperCase();
        String panPart = pan.substring(Math.max(0, pan.length() - 4)).toUpperCase();

        String dobPart = dob.replace("/", "").replace("-", "");
        if (dobPart.length() >= 4) {
            dobPart = dobPart.substring(dobPart.length() - 4);
        }

        String specialChars = "@#$%&!";
        Random random = new Random();

        char special = specialChars.charAt(random.nextInt(specialChars.length()));
        int randomNum = 100 + random.nextInt(900);

        return namePart + panPart + dobPart + special + randomNum;
    }

    
    public static String checkStrength(String password) {

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch))
                hasUpper = true;
            else if (Character.isLowerCase(ch))
                hasLower = true;
            else if (Character.isDigit(ch))
                hasDigit = true;
            else
                hasSpecial = true;
        }

        int score = 0;

        if (password.length() >= 8)
            score++;
        if (hasUpper)
            score++;
        if (hasLower)
            score++;
        if (hasDigit)
            score++;
        if (hasSpecial)
            score++;

        if (score <= 2)
            return "Weak";
        else if (score <= 4)
            return "Medium";
        else
            return "Strong";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of users: ");
        int users = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= users; i++) {

            System.out.println("\n--- User " + i + " ---");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter PAN: ");
            String pan = sc.nextLine();

            System.out.print("Enter Date of Birth (DD-MM-YYYY): ");
            String dob = sc.nextLine();

            String password = generatePassword(name, pan, dob);

            System.out.println("Generated Password: " + password);
            System.out.println("Password Strength: " + checkStrength(password));
        }

        sc.close();
    }
}