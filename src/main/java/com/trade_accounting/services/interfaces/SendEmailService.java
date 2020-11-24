package com.trade_accounting.services.interfaces;

public interface SendEmailService {
    public void sendEmail(String to, String body, String topic);
}
