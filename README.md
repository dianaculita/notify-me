# notify-me

# Description

This is a Java Spring Boot service that has Kafka Producer and topic configuration that publishes messages to the "running-wishes" topic. 
Then, running-wishes service is able to consume the messages from that topic. The message consists of a list of competition ids that are going to start on the date of tomorrow.
Further, the running-wishes service will use this list of competition ids in order to send upcoming competition notifications to the correspondent participants.
