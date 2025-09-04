package nl.rug.aoop.messagequeue.Subscriberz;

import nl.rug.aoop.networking.client.ICommunicator;

/**
 * Handles subscriptions.
 */
public class SubscriberHandler implements ISubscriber {

    /**
     * registers a trader.
     *
     * @param id           the id.
     * @param communicator the communicator.
     */
    @Override
    public void register(String id, ICommunicator communicator) {
        throw new UnsupportedOperationException();
    }
}
