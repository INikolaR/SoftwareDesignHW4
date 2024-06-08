package ru.hse.BSE223.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.hse.BSE223.Controllers.API.*;
import ru.hse.BSE223.Exceptions.UnauthorizedException;

@RestController
public class OrderController {
    @Value("${auth.url.get-user-data}")
    private String url;
    @PostMapping("/create")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        return new CreateOrderResponse("Created");
    }

    @PostMapping("/get-info")
    public GetInfoResponse getInfo(@RequestBody GetInfoRequest request) {
        return new GetInfoResponse();
    }

    @GetMapping("/get-all")
    public GetAllResponse getAll() {
        return new GetAllResponse();
    }
    @GetMapping("/is-authenticated")
    public ResponseEntity<UserDataResponse> testHello(HttpServletRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Authorization", request.getHeader("Authorization"));
            HttpEntity<?> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(url, HttpMethod.GET, entity, UserDataResponse.class);
        } catch (Exception e) {
            throw new UnauthorizedException("Unauthorized!");
        }
    }
}
