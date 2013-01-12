package com.flyingh.xml.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.flyingh.vo.User;
import com.flyingh.xml.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public List<User> getUsers(InputStream is) throws Exception {
		List<User> users = null;
		User user = null;
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(is, "utf-8");
		int eventType = pullParser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				users = new ArrayList<User>();
				break;
			case XmlPullParser.START_TAG:
				if ("user".equals(pullParser.getName())) {
					user = new User();
					user.setId(Integer.valueOf(pullParser.getAttributeValue(0)));
				} else if ("name".equals(pullParser.getName())) {
					user.setName(pullParser.nextText());
				} else if ("age".equals(pullParser.getName())) {
					user.setAge(Integer.valueOf(pullParser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("user".equals(pullParser.getName())) {
					users.add(user);
					user = null;
				}
				break;

			default:
				break;
			}
			eventType = pullParser.next();
		}
		return users;
	}

}
