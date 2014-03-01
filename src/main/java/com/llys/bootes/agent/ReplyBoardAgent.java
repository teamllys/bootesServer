package com.llys.bootes.agent;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llys.bootes.model.ReplyBoard;
import com.llys.bootes.model.ReplyBoardCond;
import com.llys.bootes.util.GenericDao;
import com.llys.bootes.util.HiberUtil;

public class ReplyBoardAgent {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public ReplyBoardAgent() {
        // TODO Auto-generated constructor stub
    }
    
    public void create(ReplyBoard rb) throws Exception {
        initForCreate(rb);
        Long id = GenericDao.insert(rb);
        rb.setReplyBoardId(id);
    }
    public static void initForCreate(ReplyBoard rb) throws Exception {
        rb.setCreateTime(new Date());
        rb.setValid(1);
    }
    public List<ReplyBoard> findAll(ReplyBoardCond cond) throws Exception {
        
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        List<ReplyBoard> resultList = null;
        try {
            tx = session.beginTransaction();
            
            Criteria cr = session.createCriteria(ReplyBoard.class);
            
            cr.addOrder(Order.desc("createTime"));
            
            if(cond.getValid() != null) {
                cr.add(Restrictions.eq("valid", cond.getValid()));
            }
        
            cr.addOrder(Order.asc("name"));
            
            resultList = cr.list();
            tx.commit();
        
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return resultList;
    }
}
