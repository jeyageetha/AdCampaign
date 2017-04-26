package org.comcast.adserver.database;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.comcast.adserver.database.util.HibernateUtil;
import org.comcast.adserver.exception.ActiveAdNotFoundException;
import org.comcast.adserver.model.Ad;
import org.hibernate.Session;


/**
 * Repository class which contains methods that talk to HSQL db
 *
 */
public class AdDatabase {
	
	
	
	/**
	 * Return all ad campaigns
	 * @return
	 * 
	 */
	public List<Ad> getAds(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		TypedQuery<Ad> query= session.createQuery("from Ad",Ad.class);
        List<Ad> ads= query.getResultList();
        session.close();
        return ads;
	}
	
  	/**
  	 * Create Ad
  	 * @param ad
  	 */
  	public void addAd(Ad ad) {
  	
  		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ad);
        
        session.getTransaction().commit();
        session.close();
        
  	}

	
  	/**
  	 * Fetch Ad by Partner id
  	 * Iterate through all ads and update active flag if current time is greater than a campaign's creation time + duration
  	 * Fetch only active ad for the specified partner 
  	 * @param partnerId
  	 * @return
  	 */
  	public Ad getAd(String partnerId){
  		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        TypedQuery<Ad> query=session.createQuery("from Ad where partnerId=:partnerId",Ad.class);
        query.setParameter("partnerId", partnerId);
        List<Ad> adList=query.getResultList();
        for(Ad ad:adList){
        	updateActiveFlag(ad);
        }
        
        query=session.createQuery("from Ad where partnerId=:partnerId and active=true",Ad.class);
        query.setParameter("partnerId", partnerId);
        Ad ad=query.getResultList().stream().findFirst().orElse(null);
        if(ad == null){
        	throw new ActiveAdNotFoundException("Active ad is not found for partner id "+partnerId);
        }
        session.close();
        return ad;
  	}

	private void updateActiveFlag(Ad ad) {
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(ad.getDateCreated());
        cal.add(Calendar.SECOND, ad.getDuration());
        Date createdDatePlusDuration = cal.getTime();
        
        Calendar createdTime = Calendar.getInstance();
        createdTime.setTime(createdDatePlusDuration);
        
        Calendar currentTime= Calendar.getInstance();
        currentTime.setTime(new Date());
        
        if(currentTime.after(createdTime)){
        	ad.setActive(false);
        }
        
	}
	
}
