package search.controller;

import java.util.List;

import search.bean.SearchDTO;

public interface SearchService {
	
	//강한별
	public int searchInsert(SearchDTO searchDTO);
	public List<SearchDTO> searchList(String member_id);
}
