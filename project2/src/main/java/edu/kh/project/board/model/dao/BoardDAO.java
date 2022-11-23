package edu.kh.project.board.model.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.vo.Board;
import edu.kh.project.board.model.vo.BoardImage;
import edu.kh.project.board.model.vo.Pagination;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 게시판 이름 목록 조회
	 * @return boardTypeList
	 */
	public List<Map<String,Object>> selectBoardType() {
		
		return sqlSession.selectList("boardMapper.selectBoardType");
	}

	/** 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {
		
		return sqlSession.selectOne("boardMapper.getListCount",boardCode);
	}

	/** 특정 게시판 목록 조회
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardType(Pagination pagination, int boardCode) {
		
		// RowBounds 객체(마이바티스)
		// - 여러 행 조회 결과 중 특정 위치부터 지정된 행의 개수만 조회하는 객체
		//					(몇 행을 건너뛸 것인가?)
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		return sqlSession.selectList("boardMapper.selectBoardList",boardCode, rowBounds);
																// 파라미터가 없을 경우, null 대입
		
		
	}

	public Board selectBoardDetail(int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoardDetail",boardNo);
	}

	public int updateReadCount(int boardNo) {
		
		return sqlSession.update("boardMapper.updateReadCount", boardNo);
	}

	/** 좋아요 여부 체크
	 * @param map
	 * @return
	 */
	public int boardLikeCheck(Map<String, Object> map) {
		
		return sqlSession.selectOne("boardMapper.boardLikeCheck", map);
	}

	/**
	 * @param paramMap
	 * @return
	 */
	public int boardLikeUp(Map<String, Object> paramMap) {
		
		return sqlSession.insert("boardMapper.boardLikeUp",paramMap);
	}

	/**
	 * @param paramMap
	 * @return
	 */
	public int boardLikeDown(Map<String, Object> paramMap) {
		
		return sqlSession.delete("boardMapper.boardLikeDown", paramMap);
	}

	/**게시글 삭제
	 * @param boardNo
	 * @return
	 */
	public int boardDelete(int boardNo) {
		
		return sqlSession.update("boardMapper.boardDelete",boardNo);
	}

	public int boardWrite(Board board) {
		
		int result = sqlSession.insert("boardMapper.boardWrite",board);
		
		//board의 boardNo 필드
		//	-> <selectKey>로 인해서 생성된 시퀀스 값이 세팅되어 있음.
		
		//메인 쿼리(INSERT) 성공 시
		if(result > 0) result = board.getBoardNo();
		
		
		return result; // 0 또는 삽입된 게시글 번호
	}

	/**게시글 첨부 이미지 삽입(리스트 형식)
	 * @param boardImageList
	 * @return result(INSERT된 행의 개수)
	 */
	public int insertBoardImageList(List<BoardImage> boardImageList) {
		
		return sqlSession.insert("boardMapper.insertBoardImageList",boardImageList);
	}

	public int boardUpdate(Board board) {
		
		return sqlSession.update("boardMapper.boardUpdate",board);
	}

	public int boardImageDelete(String condition) {
		
		return sqlSession.delete("boardMapper.boardImageDelete",condition);
	}

	/** 이미지 수정
	 * @param img
	 * @return
	 */
	public int boardImageUpdate(BoardImage img) {
		
		return sqlSession.update("boardMapper.boardImageUpdate",img);
	}

	/** 이미지 삽입
	 * @param img
	 * @return
	 */
	public int boardImageInsert(BoardImage img) {
		
		return  sqlSession.insert("boardMapper.boardImageinsert",img);
	}
	
	
}
