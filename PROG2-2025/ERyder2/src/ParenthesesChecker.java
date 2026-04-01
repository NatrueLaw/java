import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
public class ParenthesesChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a parentheses string: ");
        String input = sc.nextLine();
        sc.close();

        boolean result = isBalanced(input);
        System.out.println("Parentheses balanced: " + result);
    }

    public static boolean isBalanced(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!isPair(top, c)) return false;
            }
        }
        return stack.isEmpty();
    }
    private static boolean isPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
}