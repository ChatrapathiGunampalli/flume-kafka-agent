package com.flume;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Sink;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;


public class FlumeSink extends AbstractSink implements Configurable {

    public void configure(Context context) {

    }

    public Status process() throws EventDeliveryException {
        Sink.Status status = null;
        Channel ch = getChannel();
        String body = null;
        Transaction txn = ch.getTransaction();
        try {
            txn.begin();
            Event event = ch.take();
            if (event != null) {
                body = new String(event.getBody());
            }
            System.out.println("Content::" + body);
            txn.commit();
            status = Sink.Status.READY;
        } catch (Throwable t) {
            txn.rollback();
            status = Sink.Status.BACKOFF;
        } finally {
            if (txn != null) {
                txn.close();
            }
        }
        return status;
    }
}
