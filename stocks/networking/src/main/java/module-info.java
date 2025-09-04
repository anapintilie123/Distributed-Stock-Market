module networking {
    // If you are using Mockito in another module to mock a networking item from this package,
    // then add "opens .. to ..". If we are mocking e.g. a NetworkClient interface
    // in the module messagequeue, then we need:
    exports nl.rug.aoop.networking.client;
    // Again, sub-packages have to be explicitly opened as well. Any error messages should indicate this.
    // If you want to allow this module to be used in other modules, uncomment the following line:
     //exports nl.rug.aoop.networking;
    // Note that this will not include any sub-level packages. If you want to export more, then add those as well:
    //    exports exports nl.rug.aoop.networking.example;
    requires com.google.gson;
    requires static lombok;
    requires org.slf4j;
    requires org.mockito;
    opens nl.rug.aoop.networking.client to com.google.gson;
    exports nl.rug.aoop.networking.client.messageHandlers;
    opens nl.rug.aoop.networking.client.messageHandlers to com.google.gson;
    exports nl.rug.aoop.networking.server;
}