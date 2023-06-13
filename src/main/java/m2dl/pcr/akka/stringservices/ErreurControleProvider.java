package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class ErreurControleProvider extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    Procedure<Object> controle = new Procedure<>() {

        public void apply(Object msg) throws Exception {

            if(msg instanceof Message msgCast) {
                String originalMessage = msgCast.getMessage();
                if(originalMessage != null) {
                    String controlledMsg = StringUtils.crypte(originalMessage);
                    msgCast.getActor().tell(controlledMsg, null);
                    log.info("Sent message : " + controlledMsg);
                }
                else {
                    unhandled(msg);
                }

            }
            else {
                unhandled(msg);
            }
        }

    };

    @Override
    public void onReceive(Object msg) throws Exception {
        controle.apply(msg);
    }
}
