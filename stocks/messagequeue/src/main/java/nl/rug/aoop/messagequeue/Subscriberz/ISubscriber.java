package nl.rug.aoop.messagequeue.Subscriberz;

import nl.rug.aoop.networking.client.ICommunicator;

/**
 * Interface to register a trader.
 */
public interface ISubscriber {
    /**
     * register a trader.
     *
     * @param id           the id.
     * @param communicator communicator.
     */
    void register(String id, ICommunicator communicator);
}
