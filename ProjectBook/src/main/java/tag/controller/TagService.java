package tag.controller;

import java.util.HashMap;
import java.util.List;

import book.bean.BookDTO;
import tag.bean.TagDTO;

public interface TagService {
	public List<BookDTO> genreIndexList(int startNum,int endNum,String genre);
	public int bookInsert(TagDTO tagDTO);
	public List<HashMap<String, Object>> tagSelect(String tag);
	
}
