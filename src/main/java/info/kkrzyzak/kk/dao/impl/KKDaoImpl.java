package info.kkrzyzak.kk.dao.impl;

import java.util.List;

import info.kkrzyzak.kk.model.ChatUser;
import org.springframework.stereotype.Repository;

import info.kkrzyzak.kk.dao.KKDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository("kkDao")
public class KKDaoImpl implements KKDao{

    @PersistenceContext
  	private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(ChatUser chatUser){
        entityManager.persist(chatUser);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(ChatUser cu){
        entityManager.merge(cu);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(ChatUser chatUser){
        entityManager.remove(chatUser);
    }


    public ChatUser findByCode(String code){
        String hql="select u FROM ChatUser u where u.code = :code";
        Query query = entityManager.createQuery(hql);
        query.setParameter("code", code);
        List list =  query.getResultList();
        return (ChatUser)list.get(0);
    }

}