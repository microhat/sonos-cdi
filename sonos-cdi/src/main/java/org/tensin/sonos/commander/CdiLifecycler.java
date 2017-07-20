package org.tensin.sonos.commander;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tensin.sonos.SonosException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CdiLifecycler {

    private Logger log = LoggerFactory.getLogger(CdiLifecycler.class);

    @Inject
    private DaemonController controller;

    private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            controller.start();
        } catch (SonosException e) {
            log.error(e.getMessage(),e);
        }
    }

    private void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        try {
            controller.stop();
        } catch (SonosException e) {
            log.error(e.getMessage(),e);
        }
    }

}
