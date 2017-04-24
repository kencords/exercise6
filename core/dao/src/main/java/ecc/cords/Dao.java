package ecc.cords;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

public class Dao{

	public Session initSession(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		if(!session.getTransaction().isActive()){
			session.beginTransaction();
		}
		return session;
	}

	public <T> void save(final T t){
		Session session = initSession();
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			session.save(t);
			tx.commit();
			tx = null;
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public <T> void delete(final T t){
		Session session = initSession();
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			session.delete(t);
			tx.commit();
			tx = null;
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public <T> T get(final long id, final Class<T> type){
		Session session = initSession();
		T t = (T) session.get(type, id);
		session.close();
		return t;
	}

	public <T> T get(T t){
		Session session = initSession();
		List<T> list = session.createCriteria(t.getClass()).list();
		session.getTransaction().commit();
		session.close();
		return (T) list.get(list.indexOf((T)t));
	}

	public <T> List<T> getAll(final Class<T> type) {
      Session session = initSession();
      List<T> list = session.createCriteria(type).list();
      session.close();
      return list;
    }
}