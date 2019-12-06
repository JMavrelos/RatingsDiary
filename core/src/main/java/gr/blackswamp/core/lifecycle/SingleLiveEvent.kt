package gr.blackswamp.core.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import gr.blackswamp.core.logging.AppLog
import gr.blackswamp.core.logging.ILog
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T>(val log: ILog = AppLog) : MutableLiveData<T>() {
    companion object {
        const val TAG = "SingleLiveEvent"
    }

    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        super.observe(owner, Observer {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        pending.set(true)
        super.postValue(value)
    }
}
