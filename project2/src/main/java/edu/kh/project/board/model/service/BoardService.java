package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	List<Map<String,Object>> selectBoardType();

	Map<String, Object> selectBoardList(int boardCode, int cp);

}