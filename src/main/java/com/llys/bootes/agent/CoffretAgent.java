package com.llys.bootes.agent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.llys.bootes.model.Coffret;
import com.llys.bootes.model.CoffretCond;
import com.llys.bootes.model.Item;
import com.llys.bootes.util.GenericDao;
import com.llys.bootes.util.HiberUtil;

public class CoffretAgent {

    public CoffretAgent() {
        // TODO Auto-generated constructor stub
    }
    public void create(Coffret coffret) throws Exception {
        coffret.setCreateTime(new Date());
        
        if(coffret.getItems() != null) {
            for(int i = 0; i < coffret.getItems().size(); i++) {
                Item curr = coffret.getItems().get(i);
                ItemAgent.initForCreate(curr, coffret);
            }
        }
        
        Long id = GenericDao.insert(coffret);
        coffret.setCoffretId(id);
    }

    public List<Coffret> findAll(CoffretCond cond) throws Exception {
        
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        List<Coffret> resultList = null;
        try {
            tx = session.beginTransaction();
            
            Criteria cr = session.createCriteria(Coffret.class);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(cond.getCreateTimeBegin() != null) {
                cr.add(Restrictions.ge("createTime", sdf.parse(cond.getCreateTimeBegin())));
            }
            if(cond.getCreateTimeEnd() != null) {
                cr.add(Restrictions.le("createTime", sdf.parse(cond.getCreateTimeEnd())));
            }
            
            if(cond.getCoffretId() != null) {
                cr.add(Restrictions.eq("coffretId", cond.getCoffretId()));
            }
            if(cond.getUserId() != null) {
                cr.add(Restrictions.eq("userId", cond.getUserId()));
            }
            
            cr.addOrder(Order.desc("createTime"));
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
