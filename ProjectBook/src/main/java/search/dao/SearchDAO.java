package search.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import search.bean.SearchDTO;



@Repository
public class SearchDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int searchInsert(SearchDTO searchDTO) {
		return sqlSession.insert("mybatis.searchMapper.searchInsert",searchDTO);
	}
	
	public List<SearchDTO> searchList(String member_id) {
		return sqlSession.selectList("mybatis.searchMapper.searchList", member_id);
	}
}
