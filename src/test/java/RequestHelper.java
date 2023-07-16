import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestHelper {
    public static HttpResponse<String> PostRequestPayment(String number, String year, String month, String holder, String cvc) throws IOException, InterruptedException {
        String requestBody = "{\"number\":\"" + number + "\",\"year\":" + year + ",\"month\":" + month + ",\"holder\":\"" + holder + "\",\"cvc\":" + cvc + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/pay"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public static HttpResponse<String> PostRequestCredit(String number, String year, String month, String holder, String cvc) throws IOException, InterruptedException {
        String requestBody = "{\"number\":\"" + number + "\",\"year\":" + year + ",\"month\":" + month + ",\"holder\":\"" + holder + "\",\"cvc\":" + cvc + "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/credit"))
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
