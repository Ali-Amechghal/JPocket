package org.madara.jpocket;

// caching interface , every object should implement this interface to be
// cachable
public interface Cacheable {
	public boolean isExpired();
	public Object getIdentifier();
}