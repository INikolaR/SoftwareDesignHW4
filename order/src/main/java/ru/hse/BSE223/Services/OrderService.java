package ru.hse.BSE223.Services;

import ru.hse.BSE223.Controllers.API.*;

public interface OrderService {
    CreateOrderResponse create(CreateOrderRequest request, String email);
    GetInfoResponse getInfo(GetInfoRequest request, String email);
    GetAllResponse getAll(String email);
}
