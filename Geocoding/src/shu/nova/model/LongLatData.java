package shu.nova.model;

import java.sql.Timestamp;

/**
 * LongLatData entity. @author MyEclipse Persistence Tools
 */
public class LongLatData extends AbstractLongLatData implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public LongLatData() {
	}

	/** minimal constructor */
	public LongLatData(String addrName) {
		super(addrName);
	}

	/** full constructor */
	public LongLatData(String addrName, String status, String latitude,
			String longitude, Boolean precise, String confidence, String level,
			String fileName, Timestamp createTime, String uuid) {
		super(addrName, status, latitude, longitude, precise, confidence,
				level, fileName, createTime, uuid);
	}

}
