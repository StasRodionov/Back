package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.Movement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.*;
import static com.trade_accounting.services.impl.Stubs.model.MovementProductModelStubs.getMovementProduct;

public class MovementModelStubs {
    public static Movement getMovement(Long id) {
        return new Movement(
                id, LocalDateTime.now(), LocalDate.now(), getWarehouse(), getWarehouse(), getCompany(id), getProject(id), getEmployee(id),
                false, false, false,
                "Комментарий 1",
                List.of(getMovementProduct(1L),
                        getMovementProduct(2L),
                        getMovementProduct(3L))
        );
    }
}
