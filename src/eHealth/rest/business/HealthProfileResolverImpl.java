package eHealth.rest.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.HealthProfile;
import eHealth.rest.model.MeasureDefinition;

public class HealthProfileResolverImpl {
	
	/**
	 * 
	 * @param measureName
	 * @return
	 */
/**
 * The following method returns the health profile information by its meaasure name
 * Note:Measure name is unique in health profile because it will be trashed into 
 * Measure hitory if there is already one
 * @param measureName
 * @return
 */
public static HealthProfile getHealthProfile(String measureName)
{
	MeasureDefinition mDef=MeasureResolverImpl.getMeasureNameId(measureName);
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("HealthProfile.findByMesDef",MeasureDefinition.class);
	query.setParameter("measureDefinition", mDef);
	HealthProfile hprofile=(HealthProfile) query.getResultList();
	return hprofile;

}
/**
 * The following method is used to add new health profile information 
 * @param hprofile
 *        A new health profile object
 */
public static void addNewProfileInfo(HealthProfile hprofile)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	EntityTransaction tx=em.getTransaction();
	tx.begin();
	em.persist(hprofile);
	tx.commit();
}
/**
 * The following method is used to update health profile informaton 
 * @param hprofile
 *        A health profile object
 */
public static void updateHealthProfile(HealthProfile hprofile)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	EntityTransaction tx=em.getTransaction();
	tx.begin();
	em.merge(hprofile);
	tx.commit();
}
/**
 * The following method is used to remove health profile information
 * @param hprofile
 *        A health profile object
 */
public static void removeHealthProfile(HealthProfile hprofile)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	EntityTransaction tx=em.getTransaction();
	tx.begin();
	em.remove(hprofile);
	tx.commit();
}
/**
 * 
 * @param min
 *        A minimum date range to select record from
 * @param max
 *        A maximum date range to select from
 * @param measureName
 *        A measure name to select from
 * @return
 */
public static List<HealthProfile> getHealthProfileOnMinMax(Double min,Double max,String measureName)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("HealthProfileByMinMaxValue",HealthProfile.class);
	query.setParameter("measureDefinition",MeasureResolverImpl.getMeasureNameId(measureName));
	query.setParameter("min", min);
	query.setParameter("max", max);
	@SuppressWarnings("unchecked")
	List<HealthProfile>hProfiles=query.getResultList();
	return hProfiles;
}
/**
 * 
 * @param min
 *        A minimum date to select matching record from
 * @param measureName
 *        A measure name matching to select record from
 * @return
 */
public static List<HealthProfile>getHealthProfileOnMin(Double min,String measureName)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("HealthProfileByMinValue",HealthProfile.class);
	query.setParameter("measureDefinition", MeasureResolverImpl.getMeasureNameId(measureName));
	query.setParameter("min", min);
	@SuppressWarnings("unchecked")
	List<HealthProfile>hProfiles=query.getResultList();
	return hProfiles;
	
}
/**
 * 
 * @param max
 *        A maximum date range to select from
 * @param measureName
 *        A name of measure to select matching record from
 * @return
 */
public static List<HealthProfile>getHealthProfileOnMax(Double max,String measureName)
{
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("HealthProfileByMaxValue",HealthProfile.class);
	query.setParameter("measureDefinition", MeasureResolverImpl.getMeasureNameId(measureName));
	query.setParameter("min", max);
	@SuppressWarnings("unchecked")
	List<HealthProfile>hProfiles=query.getResultList();
	return hProfiles;	
}
}
