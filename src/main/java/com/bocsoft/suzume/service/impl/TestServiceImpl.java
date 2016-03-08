package com.bocsoft.suzume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bocsoft.suzume.domain.TblA;
import com.bocsoft.suzume.domain.TblB;
import com.bocsoft.suzume.mapper.TblAMapper;
import com.bocsoft.suzume.mapper.TblBMapper;
import com.bocsoft.suzume.service.InnerService;
import com.bocsoft.suzume.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TblAMapper   tblAMapper;
    
    @Autowired
    private TblBMapper   tblBMapper;
    
    @Autowired
    private InnerService innerService;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void requiredSuccess01() {
        TblA record = new TblA();
        record.setA1(1);
        record.setA2("a");
        tblAMapper.insert(record);
        try {
            requiresNewFail01();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
    public void requiresNewFail01() {
        TblB record = new TblB();
        record.setB1(1);
        record.setB2("a");
        tblBMapper.insert(record);
        throw new RuntimeException();
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredSuccess02() {
        TblA record = new TblA();
        record.setA1(2);
        record.setA2("b");
        tblAMapper.insert(record);
        try {
            innerService.innerRequiresNewFail02();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredSuccess03() {
        TblA record = new TblA();
        record.setA1(3);
        record.setA2("c");
        tblAMapper.insert(record);
        try {
            innerService.innerRequiresNewFail03();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.case_01();
        } catch (Exception e) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void case_02() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            innerService.case_02();
        } catch (Exception e) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void single_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            TblA ra2 = new TblA();
            ra2.setA1(1);
            ra2.setA2("aa");
            tblAMapper.insertSelective(ra2);
        } catch (Exception e) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_01() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            TblA ra2 = new TblA();
            ra2.setA1(1);
            ra2.setA2("aa");
            tblAMapper.insertSelective(ra2);
        } catch (Exception e) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void multi_02() {
        TblA ra = new TblA();
        ra.setA1(1);
        ra.setA2("a");
        tblAMapper.insertSelective(ra);
        try {
            TblA ra2 = new TblA();
            ra2.setA1(1);
            ra2.setA2("aa");
            tblAMapper.insertSelective(ra2);
        } catch (Exception e) {
            // skip
        }
        TblB rb = new TblB();
        rb.setB1(2);
        rb.setB2("b");
        tblBMapper.insertSelective(rb);
    }
    
}
