package com.securevault.user;

import java.util.List;

public class UserRegistry
{
    List<User> users;

    public List<User> getUsers()
    {
        return users;
    }
    public void setUsers(List<User> userList)
    {
        users = userList;
    }
}
