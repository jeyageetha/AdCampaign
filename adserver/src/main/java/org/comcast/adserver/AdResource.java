package org.comcast.adserver;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.comcast.adserver.model.Ad;
import org.comcast.adserver.service.AdService;

/**
 * Root resource
 */
@Path("/ad")
public class AdResource {

    AdService adService = new AdService();
	
	/**
	 * Return all ad campaigns
	 * @return
	 */
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ad> getAds() {
		return adService.getAds();
		 
    }
	
	/**
	 * Get ad by passing partner id
	 * @param partnerId
	 * @return
	 */
	@GET
	@Path("/{partnerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ad getAd(@PathParam("partnerId") String partnerId) {
		return adService.getAd(partnerId);
		 
    }
	
	/**
	 * To create ad
	 * @param ad
	 * @throws SQLException
	 */
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createAd(Ad ad) throws SQLException {
		 adService.createAd(ad); 	
		 
    }
}
