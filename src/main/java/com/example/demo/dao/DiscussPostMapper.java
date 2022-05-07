package com.example.demo.dao;

import com.example.demo.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    /**
     * 通过用户id搜索讨论贴
     * 为了分页规定每页的起始行号offset和显示行数limit
     */
     List<DiscussPost> selectDiscussPost(int userId,int offset,int limit);

    /**
     * 计算讨论贴的总行数
     * 功能：为了分页方便，总行数/显示行数=页数
     * Param用于给参数起别名，如果在<if>里使用则必须要加别名
     * xml映射中#{}里的参数名就是Param起的别名
     */
      int selectDiscussPostRows(@Param("userId") int userId);

}
