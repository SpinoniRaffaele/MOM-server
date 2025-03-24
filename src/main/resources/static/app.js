const stompClient = new StompJs.Client({
  brokerURL: 'ws://localhost:8080/websocket'
});

stompClient.onConnect = (frame) => {
  const number = $("#number").val();
  setConnected(true);
  console.log('Connected: ' + frame);
  console.log(number);
  stompClient.subscribe('/topic/notifications/' + number, (greeting) => {
    showGreeting(greeting.body);
  });
};

stompClient.onWebSocketError = (error) => {
  console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
  console.error('Broker reported error: ' + frame.headers['message']);
  console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  stompClient.activate();
}

function disconnect() {
  stompClient.deactivate();
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.publish({
    destination: "/app/send/" + $("#numberTo").val(),
    body: $("#name").val()
  });
}

function showGreeting(message) {
  $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', (e) => e.preventDefault());
  $( "#connect" ).click(() => connect());
  $( "#disconnect" ).click(() => disconnect());
  $( "#send" ).click(() => sendName());
});