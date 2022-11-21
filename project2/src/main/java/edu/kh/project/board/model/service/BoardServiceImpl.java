package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.vo.Board;
import edu.kh.project.board.model.vo.Pagination;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;

	/**게시판 이름 목록 조회
	 *@return boardTypeList
	 */
	@Override
	public List<Map<String,Object>> selectBoardType() {
		
		return dao.selectBoardType();
	}

	/**특정 게시판 목록 조회 + 페이징 처리 계산
	 *
	 */
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 1단계 : 특정 게시판의 전체 게시글 수를 조회한다(단, 삭제된 글 제외)
		int listCount = dao.getListCount(boardCode);
		// 2단계 : 전체 게시글 수 + cp(현재 페이지- currentPage)를 이용해서 
		// 페이징 처리 객체를 생성한다.
		Pagination pagination = new Pagination(listCount, cp);
		
		// 3단계 : 페이징 처리 객체를 이용해서 게시글 목록을 조회하겠다.
		List<Board> boardList = dao.selectBoardType(pagination,boardCode);
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

	// 게시글 상세 조회 + 이미지 목록 조회 + 댓글 목록 조회 (select를 3번 한번에 하는 myBatis)
	/**
	 *
	 */
	@Override
	public Board selectBoardDetail(int boardNo) {
		
		
		
		return dao.selectBoardDetail(boardNo);
	}

	@Override
	public int updateReadCount(int boardNo) {
		
		return dao.updateReadCount(boardNo);
	}

	// 좋아요 여부 체크
	@Override
	public int boardLikeCheck(Map<String, Object> map) {
		
		return dao.boardLikeCheck(map);
	}

	// 좋아요 수 증가
	@Override
	public int boardLikeUp(Map<String, Object> paramMap) {
		
		return dao.boardLikeUp(paramMap);
	}

	// 좋아요 수 감소
	@Override
	public int boardLikeDown(Map<String, Object> paramMap) {
		
		return dao.boardLikeDown(paramMap);
	}
}
