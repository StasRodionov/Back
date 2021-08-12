package com.trade_accounting.services.impl.Stubs.model;

import com.trade_accounting.models.InternalOrder;
import com.trade_accounting.models.TaskComment;

import java.time.LocalDateTime;
import java.util.List;

import static com.trade_accounting.services.impl.Stubs.ModelStubs.getCompany;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getEmployee;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getTask;
import static com.trade_accounting.services.impl.Stubs.ModelStubs.getWarehouse;
import static com.trade_accounting.services.impl.Stubs.model.InternalOrderProductModelStubs.getInternalOrderProduct;

public class TaskCommentModalStubs {
    public static TaskComment getTaskComment(Long id) {
        return TaskComment.builder()
                .id(id)
                .commentContent("Comment " + id)
                .publisher(getEmployee(1L))
                .publishedDateTime(LocalDateTime.now())
                .task(getTask(1L))
                .build();
//        return InternalOrder.builder()
//                .id(id)
//                .internalOrderProducts(List.of(
//                        getInternalOrderProduct(1L),
//                        getInternalOrderProduct(2L),
//                        getInternalOrderProduct(3L)
//                ))
//                .date(LocalDateTime.now())
//                .company(getCompany(1L))
//                .warehouse(getWarehouse(1L))
//                .isSent(false)
//                .isPrint(true)
//                .comment("Comment " + id)
//                .build();
    }
}
