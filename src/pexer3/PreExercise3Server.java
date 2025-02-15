package pexer3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PreExercise3Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket socket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equals("bye"))
                    break; // the server will thn end

                String response = evaluateExpression(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(String expression) {
        try {
            String[] parts = expression.split(" ");
            if (parts.length != 3) return expression + " = Invalid expression";

            double operand1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double operand2 = Double.parseDouble(parts[2]);

            double result;
            switch (operator) {
                case "^" -> result = Math.pow(operand1, operand2);
                case "*" -> result = operand1 * operand2;
                case "/" -> result = operand1 / operand2;
                case "%" -> result = operand1 % operand2;
                case "+" -> result = operand1 + operand2;
                case "-" -> result = operand1 - operand2;
                default -> { return expression + " = Invalid expression"; }
            }

            return expression + " = " + result;
        } catch (Exception e) {
            return expression + " = Invalid expression";
        }
    }
}

