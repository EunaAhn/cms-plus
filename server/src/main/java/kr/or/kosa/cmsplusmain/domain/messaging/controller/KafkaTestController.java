package kr.or.kosa.cmsplusmain.domain.messaging.controller;

import kr.or.kosa.cmsplusmain.domain.messaging.dto.EmailMessageDto;
import kr.or.kosa.cmsplusmain.domain.messaging.dto.SmsMessageDto;
import kr.or.kosa.cmsplusmain.domain.messaging.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafkatest")
public class KafkaTestController {

    private final MessagingService messagingService;

    String topic = "message-topic";

    @PostMapping("/sms")
    public String sendSms(@RequestBody SmsMessageDto smsMessageDto) {
        messagingService.send(topic, smsMessageDto);
        return "success sms";
    }

    @PostMapping("/email")
    public String sendEmail(@RequestBody EmailMessageDto emailMessageDto) {
        messagingService.send(topic, emailMessageDto);
        return "success email";
    }

}