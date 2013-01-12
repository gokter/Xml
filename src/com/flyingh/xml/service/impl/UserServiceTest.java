package com.flyingh.xml.service.impl;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.flyingh.vo.User;

public class UserServiceTest extends AndroidTestCase {
	private static final String TAG = "com.flyingh.xml.service.impl.UserServiceTest";

	public void testGetUsers() throws Exception {
		List<User> users = new UserServiceImpl().getUsers(getClass()
				.getResourceAsStream("/users.xml"));
		for (User user : users) {
			Log.i(TAG, user.toString());
		}
	}
}
