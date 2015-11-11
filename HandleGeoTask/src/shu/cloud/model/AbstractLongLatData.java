package shu.cloud.model;

import java.sql.Timestamp;

/**
 * AbstractLongLatData entity provides the base persistence definition of the
 * LongLatData entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLongLatData implements java.io.Serializable {

	// Fields

	private Integer id;
	private String addrName;
	private String status;
	private String latitude;
	private String longitude;
	private Boolean precise;
	private String confidence;
	private String level;
	private String fileName;
	private Timestamp createTime;
	private String uuid;

	// Constructors

	/** default constructor */
	public AbstractLongLatData() {
	}

	/** minimal constructor */
	public AbstractLongLatData(String addrName) {
		this.addrName = addrName;
	}

	/** full constructor */
	public AbstractLongLatData(String addrName, String status, String latitude,
			String longitude, Boolean precise, String confidence, String level,
			String fileName, Timestamp createTime, String uuid) {
		this.addrName = addrName;
		this.status = status;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precise = precise;
		this.confidence = confidence;
		this.level = level;
		this.fileName = fileName;
		this.createTime = createTime;
		this.uuid = uuid;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getPrecise() {
		return this.precise;
	}

	public void setPrecise(Boolean precise) {
		this.precise = precise;
	}

	public String getConfidence() {
		return this.confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}