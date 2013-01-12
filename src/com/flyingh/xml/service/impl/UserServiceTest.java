package com.flyingh.xml.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
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

	public void testSave() throws Exception {
		List<User> users = Arrays.asList(new User(1, "a", 20), new User(2, "b",
				21), new User(3, "c", 22));
		new UserServiceImpl().save(users, new FileOutputStream(new File(
				getContext().getFilesDir(), "xml.xml")));
	}
}
