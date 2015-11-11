package shu.nova.model;

import java.sql.Timestamp;

/**
 * TaskFileData entity. @author MyEclipse Persistence Tools
 */
public class TaskFileData extends AbstractTaskFileData implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TaskFileData() {
	}


	/** full constructor */
	public TaskFileData(String filePath, String uuid, Timestamp crateTime,
			String status, String progress, String xmlPath, String txtPath,
			String txtDownloadPath, String xlsPath, String xlsDownloadPath,
			String fileName,String totalCount,String doneCount) {
		super(filePath, uuid, crateTime, status, progress, xmlPath, txtPath,
				txtDownloadPath, xlsPath, xlsDownloadPath, fileName,totalCount,doneCount);
	}

}
