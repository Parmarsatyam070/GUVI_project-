/*package com.frauddetection.service;

import com.frauddetection.dao.UserDAO;
import com.frauddetection.model.User;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public boolean register(User u) {
        int id = dao.register(u);
        return id > 0;
    }

    public User authenticate(String email, String password) {
        return dao.login(email, password);
    }
}
*/


package com.frauddetection.service;

import com.frauddetection.dao.UserDAO;
import com.frauddetection.model.User;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public boolean register(User u) {
        int id = dao.register(u);
        return id > 0;
    }

    public User authenticate(String email, String password) {
        return dao.login(email, password);
    }
}
