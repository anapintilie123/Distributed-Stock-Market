
<br />
<p align="center">
  <h1 align="center">Stock Market Simulation</h1>

<p align="center">
    < Traders send orders to an exchange to boy or sell stocks.>
</p>

## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Running](#running)
* [Modules](#modules)
* [Notes](#notes)
* [Evaluation](#evaluation)
* [Extras](#extras)

## About The Project

This project is a simulation of a stock market. The idea is that there are  number of traders, and stocks. The traders
send orders to the exchange to try to buy new stocks, or sell the ones that they own depending on the strategy they use. 

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
* [Maven 3.6](https://maven.apache.org/download.cgi) or higher

### Installation

1. Navigate to the `stocks` directory
2. Clean and build the project using:
3. 
```sh
mvn install
```

### Running

1. navigate to the nl.rug.aoop.stockApplication package in the stockApplication module and open the MainStockApp class.
2. Run the main function in the MainStockApp class.
3. navigate to the nl.rug.aoop.traderApplication package in the traderApplication module and open the MainTraderApp 
class.
4. Run the main function in the MainTraderApplication class. (this starts everything, and the bots will begin trading)

## Modules

<!--
Describe each module in the project, what their purpose is and how they are used in your program. Try to aim for at least 100 words per module.
-->
1. Command - This module uses the command pattern to deal with messages. It will use the head of a message to determine wht kind of command to execute. For example,
it can receive a message with "MqPut" as the header, and will enqueue the message into the given message queue.

2. Data - This module contains all the data for both the stocks, and the traders inside yaml files. We use these yaml files to initialize our traders and stock info.

3. Images - this module contains various images related to the project, including our diagram for the project.

4. messageQueue - This module has four sections - Consumerz, Messasgez, Producerz, and Queuez. Its purpose is for orders to be added as first come first serve, so taht they can be checked against other orders, and be resolved.
- The Consumerz is used to poll the queue and remove the fist message in the queue.
- The Messagez contains the JsonAdapter, which is used to convert the parts of a message into a format that can be converted to Json. the rest of the classes are used to create a message, which has a header, body and time. then that message can be wrapped in a json, and put into a message queue.
- The Producerz are for putting a message into the queue.
- The Queuez are used as normal data structures where the first message put in the queue is the first message polled from the queue. The priority message queue is a thread safe queue where the messages are ordered by time stamp.

5. networking - This module is used so that any number of clients can connect to a server, and be able to send messages across the network. 
- The client is what actually connects to the server, and each trader has its own client. This client then can be used to send messages through that connection.
- The server is what everything connects to using separate threads.

6. stock-market-ui - this module adds a user interface that can interact with the stock market, but we have not implemented this in our project.

7. stockApplication - This module is where all the orders are received and completed. The trader application send it orders, which go into a queue, which are sent to another command handler to be executed.
- Models are our data structures for the traders and the stocks which are initialized from the yaml files.
- Operations has stocks which can choose a random stock for orders
- Order is th structure for and order, which can be a buy or sell order.
- The StackApp is where every thing is initialized, such as the queue, traders, and stocks.
- The stocksCommands is what executes commands.
- The stocksManager handles all  the orders.

8. traderApplication - This is where all the trader bots are created. They then send register messages to the stock application. then they get updates from the stockApplication, which they use to create orders.
- each trader bot uses the interactor as a facade interface, which connects it to clients and send orders. Each trader bot uses the strategy class to decide what kind of order to crete, and then 
- the trader manager initializes each trader bot, and start them all on a new thread.
- Update trader command is used to update the traders.


## Design

<!--
List all the design patterns you used in your program. For every pattern, describe the following:
- Where it is used in your application.
- What benefit it provides in your application. Try to be specific here. For example, don't just mention a pattern improves maintainability, but explain in what way it does so.
-->

In the trader application, the Interactor is a facade pattern. The trader bots use this as an interface, so that they can sue methods without knowing how they are implemented.

The command module uses the command pattern. It accepts messages which have a header and a body. The header of the message is a command, which the command handler uses to execute that command with hte body of the message.
## Evaluation

The implementation we made for networking module and the connection to the queue works well as we have debbuged and tested properly . Each TraderBot runs on  aseparate threads and has information about its own trader
and also about all the stocks. Creating orders every second works prperly as well as wrapping the orders in JSOn s and Message and sending them using tyhe producer through the network.
Also the message arrives as we expect on StockApplication, is unwrapped and , by using the header, the command handler properly enqueues the new message. A consumer continuously polls and the new message is unwrapped nad the command is executed.
The Stocks Command has a field StocksManager which deals with the functionality behind the stocks exchanges and all traders and stocks are properly updated. Our strongest asset is the fact that messages, order and updates of any kind go to and from the two applications correctly. TheHowever we would like to improve the functionality behind
stock exchanges as it lacks some edge cases.

## Extras

An incomplete diagram of the project in the images module.
___

<!-- ## Acknowledgements  -->

Neils - Our lord and savior