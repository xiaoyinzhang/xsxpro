package shu.nova.model;

import java.sql.Timestamp;

/**
 * AbstractTaskFileData entity provides the base persistence definition of the
 * TaskFileData entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTaskFileData implements java.io.Serializable {

	// Fields

	private Integer id;
	private String filePath;
	private String uuid;
	private Timestamp crateTime;
	private String status;
	private String progress;
	private String xmlPath;
	private String txtPath;
	private String txtDownloadPath;
	private String xlsPath;
	private String xlsDownloadPath;
	private String fileName;
	private String totalCount;
	private String doneCount;

	// Constructors

	/** default constructor */
	public AbstractTaskFileData() {
	}

	/** full constructor */
	public AbstractTaskFileData(String filePath, String uuid,
			Timestamp crateTime, String status, String progress,
			String xmlPath, String txtPath, String txtDownloadPath,
			String xlsPath, String xlsDownloadPath, String fileName,
			String totalCount, String doneCount) {
		this.filePath = filePath;
		this.uuid = uuid;
		this.crateTime = crateTime;
		this.status = status;
		this.progress = progress;
		this.xmlPath = xmlPath;
		this.txtPath = txtPath;
		this.txtDownloadPath = txtDownloadPath;
		this.xlsPath = xlsPath;
		this.xlsDownloadPath = xlsDownloadPath;
		this.fileName = fileName;
		this.totalCount = totalCount;
		this.doneCount = doneCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getCrateTime() {
		return this.crateTime;
	}

	public void setCrateTime(Timestamp crateTime) {
		this.crateTime = crateTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgress() {
		return this.progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getXmlPath() {
		return this.xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public String getTxtPath() {
		return this.txtPath;
	}

	public void setTxtPath(String txtPath) {
		this.txtPath = txtPath;
	}

	public String getTxtDownloadPath() {
		return this.txtDownloadPath;
	}

	public void setTxtDownloadPath(String txtDownloadPath) {
		this.txtDownloadPath = txtDownloadPath;
	}

	public String getXlsPath() {
		return this.xlsPath;
	}

	public void setXlsPath(String xlsPath) {
		this.xlsPath = xlsPath;
	}

	public String getXlsDownloadPath() {
		return this.xlsDownloadPath;
	}

	public void setXlsDownloadPath(String xlsDownloadPath) {
		this.xlsDownloadPath = xlsDownloadPath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getDoneCount() {
		return this.doneCount;
	}

	public void setDoneCount(String doneCount) {
		this.doneCount = doneCount;
	}

}