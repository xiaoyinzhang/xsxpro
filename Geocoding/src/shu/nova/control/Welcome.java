package shu.nova.control;

import java.util.ArrayList;
import java.util.List;





import org.hibernate.Transaction;

import shu.nova.model.TaskFileData;
import shu.nova.model.TaskFileDataDAO;

import com.opensymphony.xwork2.ActionSupport;

public class Welcome extends ActionSupport {
private List<TaskFileData> tasks = new ArrayList<TaskFileData>();
	
	public String execute()
	{
		try
		{
			TaskFileDataDAO tDao = new TaskFileDataDAO();
			tDao.getSession().flush();
			setTasks(tDao.findAll());
			tDao.getSession().flush();
			tDao.getSession().close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ERROR;
		}	
		return SUCCESS;
	}

	public List<TaskFileData> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskFileData> tasks) {
		this.tasks = tasks;
	}
}
