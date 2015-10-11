package com.naicha.app.utils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * AppService 的工具类
 * @author apple
 *
 * @param Mode下的实体类
 */
public class AppUtils<T> {

	/**
	 * 工具Content_ID在List中查找对象
	 * @return
	 */
	public List<T> findByContentId(Integer friendCircleId, List<T> list) {
		List<T> rtnList = new ArrayList<T>();
		try {
			for (T t : list) {
				Class clzz = t.getClass();
				Method method = clzz.getDeclaredMethod("getFriendCircleId", null);
				Integer cid = (Integer) method.invoke(t, null);
				if(cid.intValue() == friendCircleId.intValue()) {
					rtnList.add(t);
				}
			}
		} catch (Exception e) {
		}
		return rtnList;
	}
}
