package micronaut.example.mongo

import com.mongodb.reactivestreams.client.Success
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class OperationSubscriber: Subscriber<Success> {
    override fun onSubscribe(s: Subscription) {
        s.request(1)  // <--- Data requested and the insertion will now occur
    }

    override fun onNext(success: Success) {
        println("Inserted")
    }

    override fun onError(t: Throwable) {
        println("Failed")
    }

    override fun onComplete() {
        println("Completed")
    }
}