package edu.kh.project.chatting.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.chatting.model.dao.ChattingDAO;
import edu.kh.project.chatting.model.vo.ChattingRoom;
import edu.kh.project.chatting.model.vo.Message;
import edu.kh.project.common.Util;

@Service
public class ChattingServiceImpl implements ChattingService {

	@Autowired
	private ChattingDAO dao;

	//(1)채팅방이 있는지 확인
	@Override
	public int checkChattingNo(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return dao.checkChattingNo(map);
	}

	//(2)없는 경우 채팅방 생성하기
	@Override
	public int createChattingRoom(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return dao.createChattingRoom(map);
	}

	//참여중인 채팅방을 조회하는 코드
    @Override
    public List<ChattingRoom> selectRoomList(int memberNo) {
        return dao.selectRoomList(memberNo);
    }

    //메시지 삽입
    @Override
    public int insertMessage(Message msg) {
//        msg.setMessageContent(Util.XSSHandling(msg.getMessageContent()));
        msg.setMessageContent(Util.newLineHandling(msg.getMessageContent()));
        return dao.insertMessage(msg);
    }

    //읽음 처리
    @Override
    public int updateReadFlag(Map<String, Object> paramMap) {
        return dao.updateReadFlag(paramMap);
    }
    
    //채팅방 목록 조회
    @Override
    public List<Message> selectMessageList( Map<String, Object> paramMap) {
        System.out.println(paramMap);
        List<Message> messageList = dao.selectMessageList(  Integer.parseInt( String.valueOf(paramMap.get("chattingNo") )));
        
        if(!messageList.isEmpty()) {
            int result = dao.updateReadFlag(paramMap);
        }
        return messageList;
    }
}
