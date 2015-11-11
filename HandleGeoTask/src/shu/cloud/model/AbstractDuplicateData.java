package shu.cloud.model;

import java.sql.Timestamp;

/**
 * AbstractDuplicateData entity provides the base persistence definition of the
 * DuplicateData entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDuplicateData implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addrName;
	private String uuid;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public AbstractDuplicateData() {
	}

	/** minimal constructor */
	public AbstractDuplicateData(String addrName, String uuid) {
		this.addrName = addrName;
		this.uuid = uuid;
	}

	/** full constructor */
	public AbstractDuplicateData(String addrName, String uuid,
			Timestamp createTime) {
		this.addrName = addrName;
		this.uuid = uuid;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddrName() {
		return this.addrName;
	}

	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}