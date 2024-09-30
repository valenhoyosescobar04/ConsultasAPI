package cue.edu.co.consumirapis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/consumeApi")
public class ApiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String trmResponse = "";
        String weatherResponse = "";
        String rickMortyResponse = "";
        String errorMessage = "";

        try {
            // Llamada a la API de TRM
            trmResponse = callAPI("https://trm-colombia.vercel.app/?date=2023-12-31");
            System.out.println("TRM API Response: " + trmResponse); // Depuración

            // Llamada a la API del Clima
            weatherResponse = callAPI("https://api.weatherstack.com/current?access_key=YOUR_ACCESS_KEY&query=Bogota");
            System.out.println("Weather API Response: " + weatherResponse); // Depuración

            // Llamada a la API de Rick and Morty
            rickMortyResponse = callAPI("https://rickandmortyapi.com/api/character");
            System.out.println("Rick and Morty API Response: " + rickMortyResponse); // Depuración
        } catch (Exception e) {
            errorMessage = "Error al consumir las APIs: " + e.getMessage();
            e.printStackTrace();
        }

        // Pasar las respuestas al JSP
        request.setAttribute("trmData", trmResponse);
        request.setAttribute("weatherData", weatherResponse);
        request.setAttribute("rickMortyData", rickMortyResponse);
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private String callAPI(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}