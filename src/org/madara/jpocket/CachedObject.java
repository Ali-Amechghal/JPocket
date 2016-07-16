package org.madara.jpocket;

import java.util.Calendar;
import java.util.Date;

public class CachedObject implements Cacheable {
	public Date		dateOfExpireaton	= null;
	public Object	object;
	public Object	indetifier;

	public CachedObject(Object _instance, Object _identifier, int _minutesToLive) {
		// TODO Auto-generated constructor stub
		if (_instance == null || _identifier == null)
			throw new IllegalArgumentException(
					"les instance nepeuvent pas tres null ..!!!");
		this.indetifier = _identifier;
		this.object = _instance;
		if (_minutesToLive > 0) {
			Calendar _calendar = Calendar.getInstance();
			_calendar.add(Calendar.MINUTE, _minutesToLive);
			dateOfExpireaton = _calendar.getTime();
		}
	}
	@Override public Object getIdentifier() {
		return this.getIdentifier();
	}
	@Override public boolean isExpired() {
		if (dateOfExpireaton == null)
			return false;
		if (dateOfExpireaton.before(new Date()))
			return true;
		else
			return false;
	}
}