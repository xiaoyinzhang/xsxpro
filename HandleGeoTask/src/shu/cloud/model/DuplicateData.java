package shu.cloud.model;

import java.sql.Timestamp;

/**
 * DuplicateData entity. @author MyEclipse Persistence Tools
 */
public class DuplicateData extends AbstractDuplicateData implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public DuplicateData() {
	}

	/** minimal constructor */
	public DuplicateData(String addrName, String uuid) {
		super(addrName, uuid);
	}

	/** full constructor */
	public DuplicateData(String addrName, String uuid, Timestamp createTime) {
		super(addrName, uuid, createTime);
	}

}
