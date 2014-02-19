package com.llys.bootes.util;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericDao {
     
    @SuppressWarnings("unchecked")
    public static <T> T select(String tableName) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String hql  = "from " + tableName + " T"; 
            Query query = session.createQuery(hql);
            
            T resultList = (T)query.list();
            
            tx.commit();
            return resultList;
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T select(T tableClass) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String hql  = "from " + tableClass.getClass().getName() + " T"; 
            Query query = session.createQuery(hql);
            
            T resultList = (T)query.list();
            
            tx.commit();
            return resultList;
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T select(String tableName, String where) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String hql  = "from " + tableName + " T where " + where; 
            Query query = session.createQuery(hql);
            
            T resultList = (T)query.list();
            
            tx.commit();
            return resultList;
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    } 
    @SuppressWarnings("unchecked")
    public static <T> T selectById(Object tableObject, String pkName, Object pkValue) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String hql  = "from " + tableObject.getClass().getName() + " T where " + pkName + " = :pkValue"; 
            
            Query query = session.createQuery(hql);
            query.setParameter("pkValue", pkValue);
            
            T atd = (T)query.uniqueResult();
            tx.commit();
            return atd;
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T selectUsedById(String tableName, String pkName, Object pkValue) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            String hql  = 
                    "from " + tableName + " T where " + pkName + " = :" + pkName +
                    " and used = 1"; 
            
            Query query = session.createQuery(hql);
            query.setParameter(pkName, pkValue);
            
            T atd = (T)query.uniqueResult();
            tx.commit();
            return atd;
        } catch(Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
    public static <T> Long insert(T obj) throws Exception {

        Logger logger = LoggerFactory.getLogger(GenericDao.class);
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        Long id = null;
        try {
            tx = session.beginTransaction();
            
            id = (Long)session.save(obj);
            logger.info("save obj into db. id : {}", id);
            tx.commit();
            
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        return id;
    }
    public static <T> void update(T obj) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.update(obj);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        
    }
    public static <T> void merge(T obj) throws Exception {
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.merge(obj);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
        
    }
    public static <T> void remove(T obj) throws Exception {

        Logger logger = LoggerFactory.getLogger(GenericDao.class);
        Session session = HiberUtil.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            
            session.delete(obj);
            tx.commit();
            
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
            
        }
    }
}
