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
import ru.hse.BSE223.Services.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final JwtService jwtService;
    private final OrderService orderService;
    private final StationService stationServiceImpl;
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
        return stationServiceImpl.getAllStations();
    }
}
