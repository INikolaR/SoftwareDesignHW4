package ru.hse.BSE223.Services;

import lombok.AllArgsConstructor;
import org.apache.catalina.valves.rewrite.RandomizedTextRewriteMap;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.Repositories.Data.Order;
import ru.hse.BSE223.Repositories.JPAOrderRepository;

import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

@Service
@AllArgsConstructor
public class OrderProcessor {
    private static final String CRON = "*/5 * * * * *";
    private final JPAOrderRepository orderRepository;
    private final Random r;
    @Scheduled(cron = CRON)
    public void processOneRandomOrder() {
        List<Order> createdOrders = orderRepository.findAllByStatus(1);
        if (createdOrders.isEmpty()) {
            return;
        }
        int id = abs(r.nextInt());
        Order order = createdOrders.get(id % createdOrders.size());
        int newStatus = 2 + r.nextInt() % 2;
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
}
