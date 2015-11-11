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
 * DuplicateData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see shu.cloud.model.DuplicateData
 * @author MyEclipse Persistence Tools
 */
public class DuplicateDataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DuplicateDataDAO.class);
	// property constants
	public static final String ADDR_NAME = "addrName";
	public static final String UUID = "uuid";

	public void save(DuplicateData transientInstance) {
		log.debug("saving DuplicateData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DuplicateData persistentInstance) {
		log.debug("deleting DuplicateData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DuplicateData findById(java.lang.Integer id) {
		log.debug("getting DuplicateData instance with id: " + id);
		try {
			DuplicateData instance = (DuplicateData) getSession().get(
					"shu.cloud.model.DuplicateData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DuplicateData instance) {
		log.debug("finding DuplicateData instance by example");
		try {
			List results = getSession()
					.createCriteria("shu.cloud.model.DuplicateData")
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
		log.debug("finding DuplicateData instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DuplicateData as model where model."
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

	public List findByUuid(Object uuid) {
		return findByProperty(UUID, uuid);
	}

	public List findAll() {
		log.debug("finding all DuplicateData instances");
		try {
			String queryString = "from DuplicateData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DuplicateData merge(DuplicateData detachedInstance) {
		log.debug("merging DuplicateData instance");
		try {
			DuplicateData result = (DuplicateData) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DuplicateData instance) {
		log.debug("attaching dirty DuplicateData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DuplicateData instance) {
		log.debug("attaching clean DuplicateData instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}