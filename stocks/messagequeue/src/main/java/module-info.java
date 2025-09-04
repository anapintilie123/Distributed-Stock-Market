module messagequeue {
    // Needed for gson to work. If your message queue resides in a sub-package,
    // be sure to open this to com.google.gson as well.
    requires com.google.gson;
    opens nl.rug.aoop.messagequeue.Messagez to com.google.gson;
    exports nl.rug.aoop.messagequeue.Messagez;
    exports nl.rug.aoop.messagequeue.Consumerz;
    exports nl.rug.aoop.messagequeue.Producerz;
    exports nl.rug.aoop.messagequeue.Queuez;

    requires static lombok;
    requires org.slf4j;
    requires org.mockito;
    requires networking;
    requires command;
}