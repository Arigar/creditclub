package org.creditclub.httpclient;

import java.util.Scanner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpClient {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println("1 - send hello");
            System.out.println("2 - send hello-user");
            System.out.println("3 - exit");
            System.out.print("> ");

            String line = scanner.nextLine();
            switch (line) {
                case "1": {
                    try {
                        RestTemplate restTemplate = new RestTemplate();
                        String uri = "http://localhost:8080/api/hello";
                        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
                        System.out.println(response.getBody());
                    } catch (Exception e) {
                        System.out.println(e.getCause());
                    }
                    break;
                }
                case "2": {
                    try {
                        RestTemplate restTemplate = new RestTemplate();
                        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(
                                "http://localhost:8080/uaa/oauth/token?");
                        uriBuilder.queryParam("grant_type", "password");
                        uriBuilder.queryParam("username", "user");
                        uriBuilder.queryParam("password", "1");
                        uriBuilder.queryParam("client_id", "browser");

                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                        headers.add("Authorization", "Basic YnJvd3Nlcjox");

                        HttpEntity request = new HttpEntity<>(new LinkedMultiValueMap<String, Object>(),
                                headers);
                        ResponseEntity<AccessToken> responseToken = restTemplate
                                .postForEntity(uriBuilder.toUriString(), request, AccessToken.class);

                        AccessToken token = responseToken.getBody();
                        System.out.println("Bearer token: " + token.token);

                        headers = new HttpHeaders();
                        headers.add("Authorization", "Bearer " + token.token);
                        ResponseEntity<String> response = restTemplate.exchange(
                                "http://localhost:8080/api/hello-user", HttpMethod.GET,
                                new HttpEntity<>(headers), String.class);

                        System.out.println(response.getBody());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "3": {
                    run = false;
                    break;
                }
                default: {
                    System.out.println("Illegal input!");
                    break;
                }
            }
        }
    }
}
