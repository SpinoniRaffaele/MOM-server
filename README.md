# My Own Messages - Server

This is the server side of the My Own Messages application. 
It is a Java CLI application that uses Spring WebSockets to communicate bidirectionally with clients.
The server implements an access table to map clients to their addresses and information. 
The server also enable async communication by storing a mailbox of unsent messages for each client.