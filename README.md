# Stonks Exchange Simulator

A distributed **stock market simulator** built in **Java 17** and **Maven**.  
It showcases a **networked message queue**, **multithreaded trader bots**, and an **exchange** that matches buy/sell **limit orders** and broadcasts live updates.

> Tech highlights: Java 17, Maven multi-module, TCP client/server, thread-safe queues (`PriorityBlockingQueue`), JSON (Gson), YAML (Jackson), Command & Facade patterns, JUnit 5, Mockito, Awaitility.

---

## Features

- **Message Queue Core**: immutable messages, multiple queue implementations, producers/consumers.
- **Networking**: decoupled client/server that exchange **String/JSON** messages with a `MessageHandler` interface.
- **Thread-safe Queue**: timestamp-ordered, backed by `PriorityBlockingQueue`.
- **Stock Exchange App**:
    - Handles **buy/sell limit orders**, maintains bids/asks.
    - Updates prices based on latest matched transaction.
    - Broadcasts **stock + trader** updates ~every second to connected clients.
    - Initializes from `stocks.yaml` and `traders.yaml`.
- **Trader App**:
    - Spawns **bots on separate threads**.
    - Listens for exchange updates.
    - Sends periodic orders based on a simple **strategy**.
- **Optional UI**: basic table view for stock data (module: `stock-market-ui`).

---

## Project Structure

This is a **Maven multi-module** project (root POM in `stocks/`):

stocks/
command/ # Command pattern (pluggable handlers)
messagequeue/ # Messages, Producers/Consumers, Queues (incl. thread-safe)
networking/ # Client/Server + MessageHandler abstraction
stock-market-ui/ # Simple view (optional)
util/ # Utilities (e.g., YAML loader)
stockApplication/ # Exchange (server-side), order matching, broadcasting
traderApplication/ # Trader bots (client-side), strategies, threading
pom.xml # Aggregator/build configuration (Java 17)


---

## Quickstart

### Prerequisites
- **JDK 17**
- **Maven 3.6+**

### Build everything
From the `stocks/` folder (where `pom.xml` lives):

bash
mvn clean install
### Run the Exchange (server)
Windows PowerShell

cd stocks
$env:MESSAGE_QUEUE_PORT = 5000   # optional; app uses a default if unset
mvn -pl stockApplication -Dexec.skip=false exec:java `
-Dexec.mainClass="nl.rug.aoop.stockApplication.MainStockApp"


macOS/Linux

cd stocks
export MESSAGE_QUEUE_PORT=5000   # optional
mvn -pl stockApplication -Dexec.skip=false exec:java \
-Dexec.mainClass="nl.rug.aoop.stockApplication.MainStockApp"

Tip: If the exec plugin appears skipped, the -Dexec.skip=false flag forces it to run.

### Run the Trader Bots (clients)

Open a second terminal, use the same port:

Windows PowerShell

cd stocks
$env:MESSAGE_QUEUE_PORT = 5000
mvn -pl traderApplication -Dexec.skip=false exec:java `
-Dexec.mainClass="nl.rug.aoop.traderApplication.MainTraderApp"


macOS/Linux

cd stocks
export MESSAGE_QUEUE_PORT=5000
mvn -pl traderApplication -Dexec.skip=false exec:java \
-Dexec.mainClass="nl.rug.aoop.traderApplication.MainTraderApp"

### What you’ll see

Exchange logs: server boot + periodic broadcasts.

Trader logs: bots connect, receive updates, and send buy/sell orders every ~1–4s.

### Configuration

Ports: via MESSAGE_QUEUE_PORT env var (falls back to a default if not set).

Data: stocks.yaml, traders.yaml (place in a known path and load at startup).

Logging: Logback is included; adjust via logback config if needed.


### Testing

Unit tests use JUnit 5, Mockito, and Awaitility. Run:
mvn clean test


### Design Notes

Decoupling: networking transports only strings; message semantics handled via MessageHandler + Command pattern.

Threading: server accepts multiple clients; bots run on dedicated threads.

Extensibility: add new commands/strategies without touching networking layer.