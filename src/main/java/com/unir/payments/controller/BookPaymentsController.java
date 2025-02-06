package com.unir.payments.controller;

import com.unir.payments.controller.model.FilterBaseRequest;
import com.unir.payments.data.model.Order;
import com.unir.payments.data.model.PaymentType;
import com.unir.payments.data.model.Status;
import com.unir.payments.service.OrderService;
import com.unir.payments.service.PaymentTypeService;
import com.unir.payments.service.StatusService;
import com.unir.payments.service.model.OrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
@Tag(name = "Book Payments Controller", description = "Microservicio encargado de exponer operaciones para la gestion de una orden de compra.")
@ControllerAdvice
public class BookPaymentsController {

    private final StatusService statusService;

    private final PaymentTypeService paymentTypeService;

    private final OrderService orderService;

    @PostMapping("/status")
    @Operation(
            operationId = "Insertar un estado",
            description = "Operacion de escritura",
            summary = "Se crea  un estado a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del estado a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Status.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Status.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se pudo crear el estdo con los datos registrados.")
    public ResponseEntity<Status> createStatus(@RequestBody @Valid Status status) {
        Status newStatus = statusService.save(status);
        if (newStatus == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newStatus);
    }

    @GetMapping("/status")
    public ResponseEntity<Page<Status>> getAllStatus(@QueryParam("page") @Valid Integer page, @QueryParam("pageSize") @Valid Integer pageSize) {
        Page<Status> all = statusService.findAll(FilterBaseRequest.builder().page(page).pageSize(pageSize).build());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable("id") @Valid Long id) {
        Status status = statusService.findById(id);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/paymentType")
    @Operation(
            operationId = "Insertar un metodo de pago",
            description = "Operacion de escritura",
            summary = "Se crea  un metodo de pago a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de un metodo de pago.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentType.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentType.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se pudo crear el metodo de pago con los datos registrados.")
    public ResponseEntity<PaymentType> createPaymentType(@RequestBody @Valid PaymentType paymentType) {
        PaymentType newPaymentType = paymentTypeService.save(paymentType);
        if (newPaymentType == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newPaymentType);
    }

    @GetMapping("/paymentType")
    public ResponseEntity<Page<PaymentType>> getAllPaymentType(@QueryParam("page") @Valid Integer page, @QueryParam("pageSize") @Valid Integer pageSize) {
        Page<PaymentType> all = paymentTypeService.findAll(FilterBaseRequest.builder().page(page).pageSize(pageSize).build());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/paymentType/{id}")
    @Operation(
            operationId = "Obtener un metodo de pago",
            description = "Operacion de lectura",
            summary = "Se devuelve un metodo de pago a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentType.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el metodo de pago con el identificador indicado.")
    public ResponseEntity<PaymentType> getPaymentTypeById(@PathVariable("id") @Valid Long id) {
        PaymentType paymentType = paymentTypeService.findById(id);
        return ResponseEntity.ok(paymentType);
    }


    @PostMapping
    @Operation(
            operationId = "Insertar una orden",
            description = "Operacion de escritura",
            summary = "Se crea una orden a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la orden a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Order.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se pudo crear la orden con la informacion ingresada.")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid Order order) throws ValidationException {
        Order newOrder = orderService.saveOrder(order);
        if (newOrder == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/detail/{id}")
    @Operation(
            operationId = "Eliminar un registro del detalle de la orden",
            description = "Operacion de escritura",
            summary = "Se elimina un registro del detalle de la orden a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado el detalle con el identificador indicado.")
    public ResponseEntity<Void> deleteOrderDetails(@PathVariable("id") @Valid Long id) {
        Boolean removed = orderService.deleteOrderDetail(id);
        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(
            operationId = "Obtener una orden de pedido",
            description = "Operacion de lectura",
            summary = "Se devuelve una orden de pedido a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentType.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la orden de pedido con el identificador indicado.")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") @Valid Long id) {
        OrderDTO order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

}
