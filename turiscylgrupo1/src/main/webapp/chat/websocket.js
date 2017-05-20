/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Conexión Websocket y variables
var wsUri = 'ws://' + document.location.host +
        document.location.pathname.substr(0, document.location.pathname.indexOf("/faces")) + '/websocket';
console.log(wsUri);

var websocket = new WebSocket(wsUri); //Inicializa el websocket var textField = document.getElementById("texto");

var textField = document.getElementById("texto");

var users = document.getElementById("users");

var chatlog = document.getElementById("chatlog");

var output = document.getElementById("output");

var username;

//Funciones

function join() {
    username = textField.value;
    websocket.send(username + " joined");
}
function send_message() {
    websocket.send(username + ": " + textField.value);
}
function disconnect() {
    websocket.close();
}


//Listeners

websocket.onopen = function (evt) {
    onOpen(evt);
};
websocket.onmessage = function (evt) {
    onMessage(evt);
};
websocket.onerror = function (evt) {
    onError(evt);
};
websocket.onclose = function (evt) {
    onClose(evt);
};


//Funciones a las que llaman los listeners

function onOpen() {
    //writeToScreen("CONNECTED");
}
function onClose() {
   // writeToScreen("DISCONNECTED");
}
function onMessage(evt) {
    //writeToScreen("RECEIVED: " + evt.data);
    if (evt.data.indexOf("joined") !== -1) {
        users.innerHTML += evt.data.substring(0, evt.data.indexOf("joined")) + "\n";
        chatlog.innerHTML += evt.data.substring(0, evt.data.indexOf("joined")) + " se ha unido \n";
    } else {
        chatlog.innerHTML += evt.data + "\n";
    }
}
function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}
function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
}