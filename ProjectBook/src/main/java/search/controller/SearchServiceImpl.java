package search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import search.bean.SearchDTO;
import search.dao.SearchDAO;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	SearchDAO searchDAO;

	//강한별
	@Override
	public int searchInsert(SearchDTO searchDTO) {
		return searchDAO.searchInsert(searchDTO);
	}
	
	public List<SearchDTO> searchList(String member_id) {
		return searchDAO.searchList(member_id);
	}

}
