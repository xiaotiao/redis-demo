package com.wondersoft.redis;

public interface RedisService {

	/**
	 * ���keyֵ��ȡvalue
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * �����ֵ��
	 * @param key
	 * @param value
	 * @throws GeneralException
	 */
	public void set(String key,String value);
	
	/**
	 * ���¼�ֵ��
	 * @param key
	 * @param value
	 * @throws GeneralException
	 */
	public void update(String key,String value);
	
	/**
	 * ɾ���ֵ��
	 * @param key
	 * @throws GeneralException
	 */
	public void delete(String key) ;
	
	/**
	 * �鿴���Ƿ����
	 * @param key
	 * @return
	 */
	public boolean exist(String key);
	
	
	/**
	 * ���redis�е��������
	 * @return
	 */
	public boolean cleanRedis();
	
	
	public Long lPush(String key,String... args);
	
	public String rPop(String key);
}
