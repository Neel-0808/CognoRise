import java.util.*;
public class Number {
    public static void main(String[] args) {
        int number = 25;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        while(number != num){
            if(num > number){
                System.out.println("Too high. Try again.");
            } else {
                System.out.println("Too low. Try again.");
            }
            System.out.print("Enter a number: ");
            num = scanner.nextInt();
        }
        System.out.println("Congratulations! You guessed the correct number." + number);
    }
}
