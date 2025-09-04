module stockApplication {
    exports nl.rug.aoop.stockApplication.operations;
    exports nl.rug.aoop.stockApplication.models;
    exports nl.rug.aoop.stockApplication.orders;

    exports nl.rug.aoop.stockApplication;
    opens nl.rug.aoop.stockApplication.operations to com.fasterxml.jackson.databind, com.google.gson;
    opens nl.rug.aoop.stockApplication.models to com.fasterxml.jackson.databind, com.google.gson;
    opens nl.rug.aoop.stockApplication.orders to com.fasterxml.jackson.databind, com.google.gson;
//    opens nl.rug.aoop.stockApplication.operations to com.fasterxml.jackson.databind;
//    opens nl.rug.aoop.stockApplication.models to com.fasterxml.jackson.databind;
    requires static lombok;
    requires org.slf4j;
    requires org.mockito;
    requires networking;
    requires command;
    requires messagequeue;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
}