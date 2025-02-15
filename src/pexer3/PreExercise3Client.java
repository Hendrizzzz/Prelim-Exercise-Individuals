package pexer3;

import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class PreExercise3Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            File xmlFile = new File("res/exer3.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList expressions = doc.getElementsByTagName("expression");
            for (int i = 0; i < expressions.getLength(); i++) {
                Element expr = (Element) expressions.item(i);
                String operand1 = expr.getElementsByTagName("operand1").item(0).getTextContent();
                String operator = expr.getElementsByTagName("operator").item(0).getTextContent();
                String operand2 = expr.getElementsByTagName("operand2").item(0).getTextContent();

                String message = operand1 + " " + operator + " " + operand2;
                out.println(message);

                String response = in.readLine();
                System.out.println(response);
            }

            out.println("bye"); // if it has no longer expressions to read, then say bye to the server ;>
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}