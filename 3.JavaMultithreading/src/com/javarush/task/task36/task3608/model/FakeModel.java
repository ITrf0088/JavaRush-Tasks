package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model {
    private UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();
    @Override
    public ModelData getModelData() {

        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(new ArrayList<User>(){{
            add(new User("A",1,1));
            add(new User("B",2,1));
        }
        });
    }
    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }
}
