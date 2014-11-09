package utils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import eHealth.rest.dao.HealthInfoDao;
import eHealth.rest.model.MeasureDefinition;

public class MeasureResolverUtil {
public int getMeasureNameId(String MeasureName)
{
	int measureId;
	EntityManager em=HealthInfoDao.instance.getEntityManager();
	Query query=em.createNamedQuery("MeasureDefinition.getMeasureId",MeasureDefinition.class).setParameter("measureDefName", MeasureName);
	MeasureDefinition mDef=new MeasureDefinition();
	mDef=(MeasureDefinition) query.getSingleResult();
	measureId=mDef.getMeasureDefId();
	return measureId;
	
}
}
