package tag.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.bean.BookDTO;
import book.dao.BookDAO;
import tag.bean.TagDTO;
import tag.dao.TagDAO;
@Service
public class TagServiceImpl implements TagService{
	@Autowired
	TagDAO tagDAO;
	@Autowired
	BookDAO bookDAO;
	
	@Override
	public List<BookDTO> genreIndexList(int startNum,int endNum,String genre){
		return tagDAO.genreIndexList(startNum, endNum, genre);
	}


	@Override
	public int bookInsert(TagDTO tagDTO) {
		return tagDAO.bookInsert(tagDTO);
	}
	@Override
	public List<HashMap<String, Object>> tagSelect(String tag){
		return tagDAO.tagSelect(tag);
	}
}
