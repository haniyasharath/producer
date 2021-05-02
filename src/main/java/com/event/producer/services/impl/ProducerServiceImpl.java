package com.event.producer.services.impl;

import com.event.producer.dto.EventInputDto;
import com.event.producer.services.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class ProducerServiceImpl implements ProducerService {

    Logger log = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private static final List<String> CITIES = List.of("Bangalore", "Delhi", "Mumbai", "Chenni", "Mysure");

    private static final Random RANDOM_INDEX_GEN = new Random();

    @Autowired
    private Source source;

    @Override
    public void calclulateFuelCost(boolean fuellid, String city) {
        EventInputDto eventInputDto = new EventInputDto(fuellid, city);
        log.info("Triggered On-Demand Event {}", eventInputDto);
        source.output().send(MessageBuilder.withPayload(eventInputDto).build());
    }

    @Scheduled(cron = "${event.trigger}")
    public void send() {
        int index = RANDOM_INDEX_GEN.nextInt(CITIES.size());
        EventInputDto eventInputDto = new EventInputDto(index % 2 == 0, CITIES.get(index));
        log.info("Triggered Scheduled Event {}", eventInputDto);
        source.output().send(MessageBuilder.withPayload(eventInputDto).build());
    }
}
