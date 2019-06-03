package com.tasksquery.models;

import org.springframework.web.multipart.MultipartFile;

public class TaskDTO
{
	private Integer id;

	private String userName;

	private String userEmail;

	private String description;

	private MultipartFile img;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public MultipartFile getImg()
	{
		return img;
	}

	public void setImg(MultipartFile img)
	{
		this.img = img;
	}

	public void convertDtoToEntity(Task taskEntity)
	{
		if (taskEntity != null)
		{
			taskEntity.setUserName(getUserName());
			taskEntity.setUserEmail(getUserEmail());
			taskEntity.setDescription(getDescription());

		}

	}

}
