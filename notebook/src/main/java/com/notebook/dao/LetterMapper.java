package com.notebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.notebook.config.mybatis.plus.SuperMapper;
import com.notebook.entities.SysLetter;
import com.notebook.model.common.LetterModel;
  

/**
 * 
 * @author 2ing
 * @createTime 2018-01-22
 * @remarks 站内信DAO
 */

public interface LetterMapper extends SuperMapper<SysLetter>{
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks 根据pageModel和condition获取站内信数据
	 */
	List<LetterModel> selectLetterByPageAndCondition(RowBounds rowBounds, @Param("ew") Wrapper<LetterModel> wrapper) throws Exception;
	
	/**
	 * 
	 * @author 2ing
	 * @createTime 2018年1月25日
	 * @remarks	更新站内信状态，根据站内信ID数组
	 */
	int updateLettersStateTOnoReply(@Param("letterIDs") String[] letterIDs) throws Exception;
}
