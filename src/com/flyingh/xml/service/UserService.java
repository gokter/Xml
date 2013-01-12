package com.flyingh.xml.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.flyingh.vo.User;

public interface UserService {
	List<User> getUsers(InputStream is) throws Exception;

	void save(List<User> users, OutputStream os) throws Exception;
}
