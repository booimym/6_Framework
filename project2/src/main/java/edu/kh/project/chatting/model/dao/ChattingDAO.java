package edu.kh.project.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.vo.ChattingRoom;
import edu.kh.project.chatting.model.vo.Message;

@Repository
public class ChattingDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	
    /**(1)채팅방이 있는지 확인
     * @param map
     * @return
     */
    public int checkChattingNo(Map<String, Integer> map) {
        return sqlSession.selectOne("chattingMapper.checkChattingNo", map);
    }

    /**(2)없는 경우 채팅방 생성하기
     * @param map
     * @return
     */
    public int createChattingRoom(Map<String, Integer> map) {
        
    	//인서트 하고나서 결과 result
    	int result = sqlSession.insert("chattingMapper.createChattingRoom", map);
        
        int chattingNo = 0;
        
        //selectkey를 통해 튀어나온 애를...
        if(result > 0)  chattingNo = (int)map.get("chattingNo");
        return chattingNo;
    }

    
    /** 참여중인 채팅방
     * @param memberNo
     * @return roomList
     */
    public List<ChattingRoom> selectRoomList(int memberNo) {
        return sqlSession.selectList("chattingMapper.selectRoomList", memberNo);
    }
    
    /** 게시글 삽입
     * @param msg
     * @return
     */
    public int insertMessage(Message msg) {
        return sqlSession.insert("chattingMapper.insertMessage", msg);
    }

    /** 읽음 처리
     * @param paramMap
     * @return
     */
    public int updateReadFlag(Map<String, Object> paramMap) {
        return sqlSession.update("chattingMapper.updateReadFlag", paramMap);
    }

    /** 특정 채팅방 메시지 목록 조회하기
     * @param chattingNo
     * @return
     */
    public List<Message> selectMessageList(int chattingNo) {
       return sqlSession.selectList("chattingMapper.selectMessageList", chattingNo);
    }
}
