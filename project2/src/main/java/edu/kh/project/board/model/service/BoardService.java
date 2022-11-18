package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.vo.Board;

public interface BoardService {

	List<Map<String,Object>> selectBoardType();

	Map<String, Object> selectBoardList(int boardCode, int cp);

	Board selectBoardDetail(int boardNo);

}
