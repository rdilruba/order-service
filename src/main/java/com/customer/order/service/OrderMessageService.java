package com.customer.order.service;

import com.customer.order.model.Orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderMessageService {

    private static final Logger log = LoggerFactory.getLogger(OrderMessageService.class);
    @Value("${sqs.url}")
    private String sqsUrl;

    @Autowired
    private ObjectMapper objectMapper;

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    public OrderMessageService(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public void sendMessage(Orders order) {
        try {
            String message = objectMapper.writeValueAsString(order);
            queueMessagingTemplate.convertAndSend(sqsUrl, message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
