package com.zjc.concurrent.chapter01.customthreadpool;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/4 21:47
 */
public interface DenyPolicy {

    void reject(Runnable runnable,ThreadPool threadPool);

    // 该拒绝策略会直接将任务丢弃
    class DisCardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    // 该拒绝策略会向任务提交者发出异常
    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    // 该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
