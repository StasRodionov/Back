package com.trade_accounting.mock_mvc_rest_doc;

import org.springframework.test.web.servlet.MvcResult;

public interface ResultHandler {
    void handle(MvcResult result) throws Exception;
}
