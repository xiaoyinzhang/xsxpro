package shu.cloud.model;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * LongLatData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see shu.cloud.model.LongLatData
 * @author MyEclipse Persistence Tools
 */
public class LongLatDataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LongLatDataDAO.class);
	// property constants
	public static final String ADDR_NAME = "addrName";
	public static final String STATUS = "status";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String PRECISE = "precise";
	public static final String CONFIDENCE = "confidence";
	public static final String LEVEL = "level";
	public static final String FILE_NAME = "fileName";
	public static final String UUID = "uuid";

	public void save(LongLatData transientInstance) {
		log.debug("saving LongLatData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LongLatData persistentInstance) {
		log.debug("deleting LongLatData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LongLatData findById(java.lang.Integer id) {
		log.debug("getting LongLatData instance with id: " + id);
		try {
			LongLatData instance = (LongLatData) getSession().get(
					"shu.cloud.model.LongLatData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LongLatData instance) {
		log.debug("finding LongLatData instance by example");
		try {
			List results = getSession()
					.createCriteria("shu.cloud.model.LongLatData")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding LongLatData instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LongLatData as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAddrName(Object addrName) {
		return findByProperty(ADDR_NAME, addrName);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByPrecise(Object precise) {
		return findByProperty(PRECISE, precise);
	}

	public List findByConfidence(Object confidence) {
		return findByProperty(CONFIDENCE, confidence);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findByUuid(Object uuid) {
		return findByProperty(UUID, uuid);
	}

	public List findAll() {
		log.debug("finding all LongLatData instances");
		try {
			String queryString = "from LongLatData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LongLatData merge(LongLatData detachedInstance) {
		log.debug("merging LongLatData instance");
		try {
			LongLatData result = (LongLatData) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LongLatData instance) {
		log.debug("attaching dirty LongLatData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LongLatData instance) {
		log.debug("attaching clean LongLatData instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}