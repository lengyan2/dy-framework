package com.dy.framework.redis.lock;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式可重入锁
 * @author daiyuanjing
 * @date 2023-07-21
 */
public interface RedisDistributedLock {

    /**
     * 加锁 主动设置锁时长
     * @param lockName 锁名称
     * @param duration 锁持有时长 单位秒
     * @return 锁对象
     */
    RLock lock(String lockName,int duration);

    /**
     * 加锁， 主动设置锁时长和时间单位
     * @param lockName 锁名称
     * @param unit 时间单位
     * @param duration 锁持有时长
     * @return 锁对象
     */
    RLock lock(String lockName, TimeUnit unit,int duration);


    /**
     * 尝试获取锁 在等待时长内获取到锁则返回true，否则返回false ，如果获取到锁 ，在锁持有时长结束后释放锁
     * @param lockName 锁名称
     * @param waitDuration 等待时长，等到最大时间 ，[强行获取锁]
     * @param duration 锁持有时长， 单位 秒
     * @return 是否获取锁
     */
    boolean tryLock(String lockName,int waitDuration , int duration);

    /**
     * 尝试获取锁 在等待时长内获取到锁则返回true，否则返回false，如果获取到锁，在锁持有时长结束后释放锁
     * @param lockName 锁名称
     * @param unit 时间单位
     * @param waitDuration 等待时长，等待最大时间 【强行获取锁】
     * @param duration 锁持有时长
     * @return 是否获取锁
     */
    boolean tryLock(String lockName, TimeUnit unit, int waitDuration, int duration);

    /**
     * 主动释放锁 若锁非当前线程持有，会抛出IllegalMonitorStateException
     * 建议使用unlockSafely
     * @param lockName 锁名称
     */
    void unlock(String lockName);

    /**
     * 主动释放锁 若锁非当前线程持有，会抛出IllegalMonitorStateException
     * 建议使用unlockSafely
     * @param lock 锁对象
     */
    void unlock(RLock lock);

    /**
     * 安全主动释放锁
     * @param lockName 锁名称
     */
    void unlockSafely(String lockName);

    /**
     * 安全主动释放锁
     * @param lock 锁对象
     */
    void unlockSafely(RLock lock);
}
