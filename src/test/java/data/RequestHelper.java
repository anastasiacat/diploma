package data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.SneakyThrows;

public class RequestHelper {
    private static String url = System.getProperty("app.url");

    @SneakyThrows
    public static HttpResponse<String> postRequestPayment(String number, String year, String month, String holder, String cvc) {
        String requestBody = "{\"number\":\"" + number + "\",\"year\":" + year + ",\"month\":" + month + ",\"holder\":\"" + holder + "\",\"cvc\":" + cvc + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/v1/pay"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }

    @SneakyThrows
    public static HttpResponse<String> postRequestCredit(String number, String year, String month, String holder, String cvc) {
        String requestBody = "{\"number\":\"" + number + "\",\"year\":" + year + ",\"month\":" + month + ",\"holder\":\"" + holder + "\",\"cvc\":" + cvc + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "/api/v1/credit"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
