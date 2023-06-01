package m2dl.pcr.akka.helloworld3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;


public class HelloGoodbyeActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public HelloGoodbyeActor() {
        log.info("HelloGoodbyeActor constructor");
    }

    Procedure<Object> hello = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Hello " + msg + "!");
                // Garder ou non l'ensemble des états de la pile
                getContext().become(goodbye,true);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodbye = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Good bye " + msg + "!");
                //getContext().unbecome();
                getContext().become(goodafternoon, true);
            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> goodafternoon = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof String) {
                log.info("Good afternoon " + msg + "!");
                getContext().become(goodbye, true);
                getContext().unbecome();
            } else {
                unhandled(msg);
            }
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        hello.apply(msg);
    }


}
