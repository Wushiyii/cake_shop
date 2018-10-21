package com.jesse.onecake.biz;

import com.jesse.onecake.mapper.base.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseBiz<M extends BaseMapper<T>, T> {
    @Autowired
    protected M mapper;

    public BaseBiz() {
    }


    public void setMapper(M mapper) {

        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return this.mapper.selectOne(entity);
    }

    public T selectById(Object id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public List<T> select(T entity) {
        return this.mapper.select(entity);
    }

    public List<T> selectAll() {
        return this.mapper.selectAll();
    }

    public Long count(T entity) {
        return (long) this.mapper.selectCount(entity);
    }

    public List<T> selectByExample(Object example) {
        return this.mapper.selectByExample(example);
    }

    public int countByExample(Object example) {
        return this.mapper.selectCountByExample(example);
    }

    public void insertSelective(T entity) {
        this.mapper.insertSelective(entity);
    }

    public int delete(T entity) {
        return this.mapper.delete(entity);
    }

    public int deleteById(Object id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    public int updateById(T entity) {
        return this.mapper.updateByPrimaryKey(entity);
    }

    public int updateSelectiveById(T entity) {
        return this.mapper.updateByPrimaryKeySelective(entity);
    }
}
