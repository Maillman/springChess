# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```

## My Phase 2: Sequence Diagram:
https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5M9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdKUAGJITgwamURkwHRhOnAUaYHSQ4AAaz5HRgyQyqRgotGMGACClHDCKAAHtCNIziSyTqDcSpyvyoIycSIVKbCkdLjAFJqUMBtfUZegAKK6lTYAiJW3HXKnbLmcoAFicAGZuv1RupgOTxlMfVBvGUVR07uq3R6wvJpeg+gd0BxMEbmeoHUU7ShymgfAgECG8adqyTVKUQFLMlbaR1GQztMba6djKUFBwOHKBdp2-bO2Oaz2++7MgofGBUrDgDvUiOq6vu2ypzO59vdzawcvToCLtmEdDkWoW1hH8C607s9dc2KYwwOUqxPAeu71BAJZoJMIH7CGlB1hGGDlAATE4TgJgMAGjLBUyPFM4GpJB0F4as5acKYXi+P4ATQOw5IwAAMhA0RJAEaQZFkyDmGyv7lNUdRNK0BjqAkaAJqqKCzC8bwcAcv5sl+zr-kMgG7F8slLBpMAAuc35mg25QIKxPKwixbGouisTYnehgrkSa5khSg5SaOjlnpOHLcryVqCsKOZqeKXYmmGjrmo287WoudlsiFpIbnIKDXnuRHHvFrJedOs4uoet4RXWynZhZPKRKoH6YEVP7FCp2FBUBcFfERJGlsB+FlghILhjxKEwOhmG9HVtzqW1oGEYeLUwaN8EVlR3h+IEXgoOgzGsb4zAcekmSYMhzCGSUAnSD6TE+vUPrNC0omqOJ3TNVB6CdUp+nOnd0GVc9ILhUZMAmfY63mWtO5WRitkFQ5TLds5YApfuE33Wg7kQ6FhRTj5V55doQphK9D0ZdV30pflDZxaeLKlNIKDcFuh6wxB8OI+OmUoxyFNU4YhOYwFONoEu9kPh95QWetZUVVVYX1gdMA9ApNVdYUu1oRhCYUZWnjzbRkJzkx0IwAA4oBrKbVxO09XtX2SxUuunRd9iAbdcNvYp-NAi9DsPWL5vgj90L60m5k+wbwM2bzJMeWTMDktDNPcwza7nt5PLozenPY27PN4+LdnlBz8gh+DjOlNrsS+2osKx55zNcon+YIHrgE2hn+1eyXjIVI0efO0+gsB0mIsIJ+H345LPRTLbSZphU-RjygACS0hpgAjKhsZRk8nEDjh9x9F8OgIKA0pWncKxfNPABygETGWMCNDL1CIeLCt9UrvSjwbE9T4Bc+L8vq9TOvKCH3UpfJ4u996ANwsA1+oxz6jEvnsa+Ks5o0UCNgHwUBsDcHgP2QwJcUhbW4jkM2EtnSCQaDbO2wQ04JjPoBW+oZChFXKNzboABIEefQaEQO3rpd6LtPoSy9olTIJdYRwGwSXIOWI84ElJqSCOFIYYxxPGHCclc0a5WTvILG6o07KKRhOJuFoNFHhimDGRKiexiM3CgERnCUDl2RuybKc4S5aFzo3T2Rjp5zw7gwgWWDrESPfP3XhT4h4qSgbPee5Ql4rx4U7bqhDFYDXYd46JMBYlRh4bNNWyCAiWEpiZZIMAABSEAeR1zVAEUBIBpQm0IXxWWAkqiUmEi0ae9s6bQQTBg4ABSoBwAgCZKAsw0l0Pvn4vhTCqFoDYXMPe-TBnDJWAAdRYDPM6LQABCTEFBwAANIn0-ukzJPCPYCKMQAK3KWgERZSeRBLRCDaRMhZE9nkVHXctNiL0z0YzeOVdeQ52ANo7mfy46ZwitnDGudYr5ych82xxyHGqKceoluKcI7HPBZ5QxkUMWwrMa8ixpRKTYDcTYwCsJp7pTeQCslFLKkoDcSCgKaTfFnCmaUm5QTyohLFvtFS4y5bwFNsk5WZhKK5IWgELwfTRXulgMAbAGDCDxESHg42u1Gl32zBUI6J0zoXWMI9TuwIrh6T4TqqggjuB4FEXaqAkjQbE3hZDGAIBHWDhRUzJxrMKSGA1LXPypjXXmP0e8z1iqYY+oBf6zINdjFExtcucNBcPWOpEbGrK8bA2tiZcmjsZrnSIEVX3AeVrIW6otaaxJkYn4pMQUAA

## Time for 100+CodeQuality%:
Phase # - hour:minute:second:
- Phase 0 - 2:30:23
- Phase 1 - Phase 0 + 1:30:49
- Phase 1 Extra Credit - Phase 0 + 3:45:05
- Phase 2 - Phase 0 + Phase 1 + 0:19:52
- Phase 3 - Phase 0 + Phase 1 + Phase 2 + 3:37:28
- Phase 4 - Phase 0 + Phase 1 + Phase 2 + Phase 3 + 2:25:12
