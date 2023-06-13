package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class CryptageProvider extends UntypedActor  {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    Procedure<Object> cryptage = new Procedure<Object>() {

        public void apply(Object msg) throws Exception {

            if(msg instanceof Message msgCast) {
                String originalMessage = msgCast.getMessage();
                if(originalMessage != null) {
                    String controlledMsg = StringUtils.ajouteCtrl(originalMessage);
                    log.info("Sending message : " + controlledMsg);
                    msgCast.getActor().tell(controlledMsg, null);

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
    public void onReceive(Object o) throws Exception {
        cryptage.apply(o);
    }
}
