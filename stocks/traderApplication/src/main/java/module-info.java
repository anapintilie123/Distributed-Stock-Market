module traderApplication {
    opens nl.rug.aoop.traderApplication to com.fasterxml.jackson.databind, com.google.gson;
    requires static lombok;
    requires org.slf4j;
    requires org.mockito;
    requires command;
    requires messagequeue;
    requires networking;
    requires stockApplication;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
}