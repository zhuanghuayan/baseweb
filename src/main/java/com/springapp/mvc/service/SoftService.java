package com.springapp.mvc.service;

import com.springapp.mvc.APKBean;
import com.springapp.mvc.dao.SoftDao;
import com.springapp.mvc.entity.APKEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wuqinghai on 16/10/28.
 */

@Service
public class SoftService {
    @Resource
    private SoftDao dao;

    public List<APKBean> getAllSoft(){
        return dao.getAllSoft();
    }

    public List<APKEntity> getAllSoftWithTypeName(){
        return dao.getAllSoftWithTypeName();
    }

}
