package org.comcast.adserver.service;


import java.sql.SQLException;
import java.util.List;

import org.comcast.adserver.database.AdDatabase;
import org.comcast.adserver.exception.DataNotFoundException;
import org.comcast.adserver.model.Ad;

/**
 * Service class 
 *
 */
public class AdService {
	
	AdDatabase addb= new AdDatabase();
	
	public List<Ad> getAds(){
		List<Ad> adList= addb.getAds();
		if(adList ==null || adList.isEmpty()){
			throw new DataNotFoundException("No ads found");
		}
		return adList;
	}
	
	public void createAd(Ad ad) throws SQLException{
		if(ad == null){
			throw new DataNotFoundException("Input is null");
		}
		addb.addAd(ad);
	}
	
	public Ad getAd(String partnerId){
		
		Ad ad =addb.getAd(partnerId);
		if(ad == null){
			throw new DataNotFoundException("Ad with partner id "+partnerId+" is not found");
		}
		return ad;
	}
	

}
