package ru.hse.BSE223.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.API.*;
import ru.hse.BSE223.API.Enums.OrderStatus;
import ru.hse.BSE223.Exceptions.BadOrderIdException;
import ru.hse.BSE223.Exceptions.BadStationIdException;
import ru.hse.BSE223.Exceptions.EqualSourceDestinationException;
import ru.hse.BSE223.Data.Order;
import ru.hse.BSE223.Data.Repositories.JPAOrderRepository;
import ru.hse.BSE223.Data.Repositories.JPAStationRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final JPAOrderRepository orderRepository;
    private final JPAStationRepository stationRepository;
    public CreateOrderResponse create(CreateOrderRequest request, String email) {
        if (request.getFromStationId() == request.getToStationId()) {
            throw new EqualSourceDestinationException("Source and destination points cannot be equal");
        }
        try {
            Order order = new Order(email, request.getFromStationId(), request.getToStationId(), 1);
            orderRepository.save(order);
            return new CreateOrderResponse(order.getId());
        } catch (Exception e) {
            throw new BadStationIdException("No station with such an id!");
        }
    }
    public GetInfoResponse getInfo(GetInfoRequest request, String email) {
        Optional<Order> o = orderRepository.findById(request.getId());
        if (o.isPresent()) {
            Order oo = o.get();
            if (!Objects.equals(oo.getEmail(), email)) {
                throw new BadOrderIdException("No order with such an id!");
            }
            return new GetInfoResponse(oo.getCreated(), stationRepository.getReferenceById(oo.getFromStationId()).getName(), stationRepository.getReferenceById(oo.getToStationId()).getName(), OrderStatus.values()[oo.getStatus()]);
        } else {
            throw new BadOrderIdException("No order with such an id!");
        }
    }
    public GetAllResponse getAll(String email) {
        List<Integer> l = orderRepository.findAllByEmail(email).stream().map(Order::getId).toList();
        return new GetAllResponse(l);
    }
}
