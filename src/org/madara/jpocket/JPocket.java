package org.madara.jpocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class JPocket {
	private static Thread					threadCachCleaner	= null;
	private static HashMap<Object, Object>	cachedMap			= new HashMap<Object, Object>();
	static {
		threadCachCleaner = new Thread(new Runnable() {
			private static final long	_sleepingTime	= 5000; // 5 seconds

			@Override public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Set<Object> keysCachedMap = cachedMap.keySet();
						Iterator<Object> _iteratorKeys = keysCachedMap
								.iterator();
						while (_iteratorKeys.hasNext()) {
							Object _key = _iteratorKeys.next();
							Cacheable cacheObject = (Cacheable) cachedMap
									.get(_key);
							if (cacheObject.isExpired()) {
								cachedMap.remove(_key);
							}
						}
						Thread.sleep(_sleepingTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		threadCachCleaner.setPriority(Thread.MIN_PRIORITY);
		threadCachCleaner.start();
	}

	public static void putInCache(Cacheable _cacheObject) {
		cachedMap.put(_cacheObject.getIdentifier(), _cacheObject);
	}
	public static Object getCache(Object _key) {
		if (_key == null)
			return null;
		Cacheable cacheable = (Cacheable) cachedMap.get(_key);
		if (cacheable == null)
		{
			//getObject from another datasource and put it in the map
			return null;
		}
			
		else if (cacheable.isExpired() == false)
			return cacheable;
		else
			return null;
	}
}