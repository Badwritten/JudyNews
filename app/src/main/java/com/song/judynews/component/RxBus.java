package com.song.judynews.component;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by Judy on 2017/6/25.
 */

public class RxBus {
    private final FlowableProcessor<Object> mBus;

    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    public static RxBus get() {
        return Holder.BUS;
    }

    public void post(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return mBus;
    }

    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }
}
