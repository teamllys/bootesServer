package com.llys.bootes.agent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.llys.bootes.model.Coffret;
import com.llys.bootes.model.CoffretCond;
import com.llys.bootes.model.Item;
import com.llys.bootes.model.ItemCond;
import com.llys.bootes.util.GenericDao;
import com.llys.bootes.util.HiberUtil;

public class ItemAgent {

    public ItemAgent() {
        // TODO Auto-generated constructor stub
    }
    public void create(Item item) throws Exception {
        initForCreate(item, null);
        Long id = GenericDao.insert(item);
        item.setItemId(id);
    }
    public static void initForCreate(Item item, Coffret coffret) throws Exception {
        item.setCreateTime(new Date());
        if(coffret != null) {
            item.setCoffret(coffret);
        }
    }
    public List<Item> findAll(ItemCond cond) throws Exception {
        
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        List<Item> resultList = null;
        try {
            tx = session.beginTransaction();
            
            String queryStr = "from Item IT where 1=1 ";
            if(cond.getItemId() != null) {
                queryStr += "and item_id = :itemId";
            }
            if(cond.getCoffretId() != null) {
                queryStr += "and coffret_id = :coffretId";
            }
            
            Query query = session.createQuery(queryStr);
            
            if(cond.getItemId() != null) {
                query.setParameter("itemId", cond.getItemId());
            }
            
            if(cond.getCoffretId() != null) {
                query.setParameter("coffretId", cond.getCoffretId());
            }
            
            
            try {
                resultList = query.list();
            } catch(ObjectNotFoundException noObj) {
                resultList = null;
            }
            
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
