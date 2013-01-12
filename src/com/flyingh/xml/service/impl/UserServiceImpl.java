package com.flyingh.xml.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

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

	@Override
	public void save(List<User> users, OutputStream os) throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(os, "utf-8");
		serializer.startDocument("utf-8", true);
		for (User user : users) {
			serializer.startTag(null, "users");

			serializer.startTag(null, "user");
			serializer.attribute(null, "id", String.valueOf(user.getId()));
			
			serializer.startTag(null, "name");
			serializer.text(user.getName());
			serializer.endTag(null, "name");

			serializer.startTag(null, "age");
			serializer.text(String.valueOf(user.getAge()));
			serializer.endTag(null, "age");

			serializer.endTag(null, "user");

			serializer.endTag(null, "users");
		}
		serializer.endDocument();
	}

}
