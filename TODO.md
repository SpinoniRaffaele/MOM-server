## Registration
the client generates a random uuid and stores it locally (the random uuid is the device identifier). 
It sends the phone number and the  uuid to the server. (Phone number Should be verified). 
The server stores phone numbers and uuid to database. 
(If the phone number was already there it will substitute the uuid)
After it has been registered, the user generate and stores a signal Entity with the prekey 
and sends it to the server which stores it together with phone num and uuid.

## Connection
at startup the connection call sends the phone number and the uuid. 
The server validate, if the data is good, it open the websocket and sends eventual queued messages. 
If its not good, the client needs to register again. 
If the client cannot find the stored Entity it will need to register again.

## New contact
When the user wants to save a contact it ask the server for the prekey corresponding to a phone number and stores it.
After that the client can create and store a new session used to encrypt/decrypt messages in that chat. 
It can also store a name for the contact

## Messaging
When the user wants to send a message he has to contact the server 
to check if the prekey of the receiver is still valid, if it is he encrypt the message and send, 
the server will try to send the message to the receiver, if it doesnt respond the server will queue the message.
If the prekey is not valid, the client creates a new session with the new value and stores it.

The DB has a table for phone numbers to their uuid and preKey. 
And a table for unsent messages with their sender, receiver and the message itself.
