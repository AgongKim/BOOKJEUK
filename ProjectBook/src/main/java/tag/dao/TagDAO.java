package tag.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import book.bean.BookDTO;
import tag.bean.TagDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

@Repository
public class TagDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<BookDTO> genreIndexList(int startNum,int endNum,String genre){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startNum",startNum);
		map.put("endNum",endNum);
		map.put("genre", genre);
		return sqlSession.selectList("mybatis.tagMapper.genreIndexList",map);
	}
	
	public int bookInsert(TagDTO tagDTO) {
		return sqlSession.insert("mybatis.tagMapper.bookInsert",tagDTO);
	}

	public List<HashMap<String, Object>> tagSelect(String tag) {
		return sqlSession.selectList("mybatis.tagMapper.tagSelect",tag);
	}

}
