package edu.kh.project.chatting.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ChattingRoom {
	
    private int chattingNo;
    private String lastMessage;
    private String sendTime;
    private int targetNo;
    private String targetNickName;
    private String targetProfile;
    private int notReadCount;
}
