package ru.hse.BSE223.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.BSE223.Controllers.API.*;
import ru.hse.BSE223.Repositories.Data.Station;
import ru.hse.BSE223.Services.JwtServiceImpl;
import ru.hse.BSE223.Services.OrderServiceImpl;
import ru.hse.BSE223.Services.StationService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {
    private final JwtServiceImpl jwtService;
    private final OrderServiceImpl orderService;
    private final StationService stationService;
    @PostMapping("/create")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request, HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request, email));
    }

    @GetMapping("/get-order-info")
    public GetInfoResponse getInfo(GetInfoRequest request, HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return orderService.getInfo(request, email);
    }

    @GetMapping("/get-all-orders")
    public GetAllResponse getAll(HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return orderService.getAll(email);
    }
    @GetMapping("/get-stations")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }
}
