package com.llys.bootes.agent;

import java.text.SimpleDateFormat;
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
import com.llys.bootes.model.ReplyMessage;
import com.llys.bootes.model.ReplyMessageCond;
import com.llys.bootes.util.GenericDao;
import com.llys.bootes.util.HiberUtil;

public class ReplyMessageAgent {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    public void create(ReplyMessage message) throws Exception {
        initForCreate(message);
        Long id = GenericDao.insert(message);
        message.setReplyMessageId(id);
    }
    public static void initForCreate(ReplyMessage message) throws Exception {
        message.setCreateTime(new Date());
    }
    public List<ReplyMessage> find(ReplyMessageCond cond) throws Exception {
        
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        List<ReplyMessage> resultList = null;
        try {
            tx = session.beginTransaction();
            
            Criteria cr = session.createCriteria(ReplyMessage.class);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(cond.getCreateTimeBegin() != null) {
                cr.add(Restrictions.ge("createTime", sdf.parse(cond.getCreateTimeBegin())));
            }
            if(cond.getCreateTimeEnd() != null) {
                cr.add(Restrictions.le("createTime", sdf.parse(cond.getCreateTimeEnd())));
            }
            if(cond.getReplyBoardId() != null) {
                cr.add(Restrictions.eq("replyBoardId", cond.getReplyBoardId()));
            }
            if(cond.getReplyMessageIdGE() != null) {
                cr.add(Restrictions.ge("replyMessageId", cond.getReplyMessageIdGE()));
            }
            if(cond.getLimit() != null) {
                cr.setMaxResults(cond.getLimit());
            }
            cr.addOrder(Order.desc("createTime"));
            
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
