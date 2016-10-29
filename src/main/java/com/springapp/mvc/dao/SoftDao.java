package com.springapp.mvc.dao;

import com.springapp.mvc.APKBean;
import com.springapp.mvc.entity.APKEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wuqinghai on 16/10/28.
 */
@Repository
public interface SoftDao {

    public List<APKBean> getAllSoft();
    public List<APKEntity> getAllSoftWithTypeName();
}

