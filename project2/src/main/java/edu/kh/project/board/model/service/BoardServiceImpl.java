package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.vo.Board;
import edu.kh.project.board.model.vo.BoardImage;
import edu.kh.project.board.model.vo.Pagination;
import edu.kh.project.common.Util;

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

	@Override
	public int boardDelete(int boardNo) {
		
		return dao.boardDelete(boardNo);
	}

	//@Transactional : 예외 발생 시 서비스 내에서 수행한 모든 DML을 ROLLBACK을 한다.
	//					단, RuntimeException 발생 시에만 롤백한다.
	@Transactional(rollbackFor = Exception.class) //모든 예외 발생시 롤백한다는 뜻.
	@Override
	public int boardWrite(Board board, List<MultipartFile> imageList, String webPath, String folderPath) throws  IOException {
		
		//1. 게시글만 먼저 삽입을 해볼게!
		// 1) ~~처리  (xss크로스 사이트 스크립트 공격)처리하고 그 후에 개행문자 처리하기!!!!
		//왜냐면 개행문자 먼저 처리해버리면, 그 후에xss처리하면서 이제 <br>이 안 먹을 수 있음.
		board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()));
		//board.getBoardTitle()를 얻어와서 handling처리하고 그걸 다시 title에 집어 넣는다....
		board.setBoardContent(Util.XSSHandling(board.getBoardContent()));
		
		board.setBoardContent(Util.newLineHandling(board.getBoardContent()));
		// 2) 게시글 삽입 DAO호출 후
		// 		결과로 삽입된 게시글의 번호를 반환 받기
		int boardNo = dao.boardWrite(board); // 0 또는 삽입된 게시글 번호가 담겨있는 상황
		
		//2. 이미지만 삽입
		if(boardNo > 0) {
			
			// imageList : 실제 파일이 담겨있는 리스트
			// boardImageList : DB에 삽입할 이미지 정보만 담겨있는 리스트
			// renameList : 변경된 파일명만 담겨있는 리스트
			
			List<BoardImage> boardImageList = new ArrayList<BoardImage>();
			List<String> reNameList = new ArrayList<String>();
			
			// imageList에 담겨있는 파일 중
			// 실제로 업로드된 파일만 분류하는 작업을 진행한다.
			
			for(int i = 0 ; i<imageList.size(); i++) {
				
				// i번째 파일의 크기가 0보다 크다 == 업로드된 파일이 있다!
				if(imageList.get(i).getSize() > 0) {
					 
					// BoardImage 객체 생성
					BoardImage img = new BoardImage();
					
					// BoardImage에 값 세팅
					img.setImagePath(webPath);
					
					//String reName = Util.fileRename(원본파일명);
					String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
					
					img.setImageReName(reName);
					reNameList.add(reName); //변경파일명 리스트에 추가
					
					//원본 파일명
					img.setImageOriginal(imageList.get(i).getOriginalFilename());
					img.setBoardNo(boardNo); // 첨부된 게시글 번호
					img.setImageOrder(i); //이미지 순서
					
					// boardImageList에 추가
					boardImageList.add(img);
				}//if끝
			}//for문 끝
			
			//boardImageList가 비어있지 않다면
			// == 업로드된 파일이 있어서 분류된 내용이 존재
			if(!boardImageList.isEmpty()) {
				
				//DB에 업로드된 파일 정보 INSERT
				int result = dao.insertBoardImageList(boardImageList);
				
				// 삽입 결과 행의 수 == DB에 삽입하려고 분류한 리스트의 크기
				//즉, 전부 다 삽입된 경우!!!!에
				if(result == boardImageList.size()) {
					
					//파일 변환 작업을 할 것이다.
					for(int i = 0; i<boardImageList.size(); i++) {
						
						// 순서 == imageList의 인덱스
						int index = boardImageList.get(i).getImageOrder();
						
						// 실제 파일로 변환할 거임
						imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));
					}
				}
			}
		}
		
		return boardNo;
	}
}
