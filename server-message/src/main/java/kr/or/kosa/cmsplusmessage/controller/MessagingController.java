package kr.or.kosa.cmsplusmessage.controller;



import kr.or.kosa.cmsplusmessage.dto.MessageDto;
import kr.or.kosa.cmsplusmessage.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/messagingtest")
@RequiredArgsConstructor
public class MessagingController {

    private final MessagingService messagingService;

    @PostMapping("/sms")
    public String sendSms(@RequestBody List<MessageDto> messages) {
        messagingService.sendSms(messages);
        return "[sms 전송 성공]";
    }

    @PostMapping("/email")
    public String sendEmail(@RequestBody MessageDto message) {
        messagingService.sendEmail(message);
        return "[이메일 전송 성공]";
    }

}
