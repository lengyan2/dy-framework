package com.dy.framework.redis.lock.impl;

import com.dy.framework.redis.lock.RedisDistributedLock;
import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁 redisson实现
 * @author daiyuanjing
 * @date 2023-07-21
 */
@Service
@AllArgsConstructor
public class RedisDistributedLockImpl implements RedisDistributedLock {

    private static final String LOCK_KEY_PREFIX = "distributedLock:";

    private final RedissonClient redissonClient;

    @Override
    public RLock lock(String lockName, int duration) {
        return this.lock(lockName,TimeUnit.SECONDS,duration);
    }

    @Override
    public RLock lock(String lockName, TimeUnit unit, int duration) {
        RLock lock = this.getRedissonLockByName(lockName);
        lock.lock(duration,unit);
        return lock;
    }

    @Override
    public boolean tryLock(String lockName, int waitDuration, int duration) {
        return this.tryLock(lockName,TimeUnit.SECONDS,waitDuration,duration);
    }

    @Override
    public boolean tryLock(String lockName, TimeUnit unit, int waitDuration, int duration) {
        RLock lock = this.getRedissonLockByName(lockName);
        try {
            return lock.tryLock(waitDuration,duration,unit);
        }catch (InterruptedException e){
            return false;
        }
    }

    @Override
    public void unlock(String lockName) {
        RLock lock = this.getRedissonLockByName(lockName);
        this.unlock(lock);
    }

    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }

    @Override
    public void unlockSafely(String lockName) {
        RLock lock = this.getRedissonLockByName(lockName);
        this.unlockSafely(lock);
    }

    @Override
    public void unlockSafely(RLock lock) {
        if (lock == null){
            return;
        }
        if (lock.isLocked() && lock.isHeldByCurrentThread()){
            this.unlock(lock);
        }
    }

    private RLock getRedissonLockByName(String lockName){
        return redissonClient.getLock(LOCK_KEY_PREFIX + lockName);
    }
}
