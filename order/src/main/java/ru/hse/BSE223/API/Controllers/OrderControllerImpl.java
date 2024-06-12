package ru.hse.BSE223.API.Controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.BSE223.API.*;
import ru.hse.BSE223.Data.Station;
import ru.hse.BSE223.Services.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final JwtService jwtService;
    private final OrderService orderService;
    private final StationService stationServiceImpl;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= CreateOrderResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
    @PostMapping("/create")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request, HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request, email));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= GetInfoResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
    @GetMapping("/get-order-info")
    public GetInfoResponse getInfo(GetInfoRequest request, HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return orderService.getInfo(request, email);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=GetAllResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
    @GetMapping("/get-all-orders")
    public GetAllResponse getAll(HttpServletRequest r) {
        String email = jwtService.checkAndExtractEmail(r);
        return orderService.getAll(email);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class)
                            )
                    })
    })
    @GetMapping("/get-stations")
    public List<Station> getAllStations() {
        return stationServiceImpl.getAllStations();
    }
}
