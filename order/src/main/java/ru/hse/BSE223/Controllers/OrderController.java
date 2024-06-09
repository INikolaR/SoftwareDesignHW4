package ru.hse.BSE223.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import ru.hse.BSE223.Controllers.API.*;
import ru.hse.BSE223.Repositories.Data.Station;

import java.util.List;

public interface OrderController {
    ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest request, HttpServletRequest r);
    GetInfoResponse getInfo(GetInfoRequest request, HttpServletRequest r);
    GetAllResponse getAll(HttpServletRequest r);
    List<Station> getAllStations();
}
