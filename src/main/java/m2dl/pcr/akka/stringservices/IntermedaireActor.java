package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class IntermedaireActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef actor1;
    private ActorRef actor2;

    public IntermedaireActor(ActorRef actor1, ActorRef actor2) {
        log.info("Created Intemediaire actor");
        this.actor1 = actor1;
        this.actor2 = actor2;
    }


    Procedure<Object> procedureIntermediaire = new Procedure<Object>() {

        public void apply(Object msg) throws Exception {
            if(msg instanceof String msgCast) {
                Message message = new Message(actor2, msgCast);
                log.info("Sending to first actor with second actor value");
                actor1.tell(message, null);

            }
            else {
                unhandled(msg);
            }
        }

    };


    @Override
    public void onReceive(Object o) throws Exception {
        procedureIntermediaire.apply(o);
    }

}
