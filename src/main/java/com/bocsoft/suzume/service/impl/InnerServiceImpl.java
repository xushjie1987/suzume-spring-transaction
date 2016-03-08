package com.bocsoft.suzume.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bocsoft.suzume.domain.TblA;
import com.bocsoft.suzume.domain.TblB;
import com.bocsoft.suzume.mapper.TblAMapper;
import com.bocsoft.suzume.mapper.TblBMapper;
import com.bocsoft.suzume.service.InnerService;

@Service("innerService")
public class InnerServiceImpl implements InnerService {
    
    @Autowired
    private TblAMapper tblAMapper;
    
    @Autowired
    private TblBMapper TblBMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerRequiresNewFail02() {
        TblB record = new TblB();
        record.setB1(2);
        record.setB2("b");
        TblBMapper.insert(record);
        throw new RuntimeException();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerRequiresNewFail03() throws IOException {
        TblB record = new TblB();
        record.setB1(3);
        record.setB2("c");
        TblBMapper.insert(record);
        throw new IOException();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_01() {
        TblA ra = new TblA();
        ra.setA1(11);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRED");
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void case_02() {
        TblA ra = new TblA();
        ra.setA1(11);
        ra.setA2("aa");
        tblAMapper.insertSelective(ra);
        throw new RuntimeException("test for REQUIRES_NEW");
    }
    
}
