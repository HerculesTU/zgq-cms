package com.ruoyi.portal.mapper;

import com.ruoyi.portal.domain.InterResponseBody;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InterResponseBodyMapper {
    int deleteByPrimaryKey(String id);

    int insert(InterResponseBody record);

    int insertSelective(InterResponseBody record);

    InterResponseBody selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InterResponseBody record);

    int updateByPrimaryKey(InterResponseBody record);

    List<InterResponseBody> selectByfid(String fid);
}