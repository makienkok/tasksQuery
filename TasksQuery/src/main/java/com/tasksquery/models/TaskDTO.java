package com.tasksquery.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class TaskDTO
{
    private Integer id;

    @NotNull
    @NotBlank
    private String userName;

    @NotNull
    @NotBlank
    private String userEmail;

    @NotNull
    @NotBlank
    @Size(max = 300)
    private String description;

    private MultipartFile img;

    private Boolean state;

    private String nameImg;

    public TaskDTO()
    {

    }

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

    public Boolean getState()
    {
        return state;
    }

    public void setState(Boolean state)
    {
        this.state = state;
    }

    public String getNameImg()
    {
        return nameImg;
    }

    public void setNameImg(String nameImg)
    {
        this.nameImg = nameImg;
    }

    public void convertDtoToEntity(Task taskEntity) throws Exception
    {
        if (taskEntity != null)
        {
            taskEntity.setUserName(getUserName());
            taskEntity.setUserEmail(getUserEmail());
            taskEntity.setDescription(getDescription());
            taskEntity.setState(getState() != null && getState() ? 1 : 0);
            if (taskEntity.getImg() == null)
                taskEntity.setImg(getImg() != null ? getImg().getBytes() : null);
            taskEntity.setImgName(getNameImg());
        }
    }

    public void convertEntityToDto(Task taskEntity)
    {
        if (taskEntity != null)
        {
            setId(taskEntity.getId());
            setUserName(taskEntity.getUserName());
            setUserEmail(taskEntity.getUserEmail());
            setDescription(taskEntity.getDescription());
            setNameImg(taskEntity.getImgName());
            setState(taskEntity.getState() > 0 ? true : false);

        }

    }

}
