package kr.or.kosa.cmsplusmain.domain.messaging.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailMessageDto extends MessageDto {

    private String emailAddress;

}
