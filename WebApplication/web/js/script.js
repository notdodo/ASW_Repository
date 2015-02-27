function setVisible(who) {
    if (document.getElementById(who).style.visibility !== 'visible') {
        document.getElementById(who).style.visibility = 'visible';
        document.getElementsByClassName("columns-container")[0].style.opacity = 0.2;
        document.getElementsByTagName("header")[0].style.opacity = 0.2;
    }
}

function setHidden(who) {
    if (document.getElementById(who).style.visibility !== 'hidden') {
        document.getElementById(who).style.visibility = 'hidden';
        document.getElementsByClassName("columns-container")[0].style.opacity = 1;
        document.getElementsByTagName("header")[0].style.opacity = 1;
    }
}

function resize(logged) {
    /* set the width */
    var maxW = parseInt(window.innerWidth),
            maxH = parseInt(window.innerHeight),
            headerH = parseInt(document.getElementsByTagName("header")[0].clientHeight),
            rightW = parseInt(document.getElementsByClassName("right-column")[0].clientWidth),
            leftCol = document.getElementsByClassName("left-column")[0],
            left = maxW - rightW - 45;
    leftCol.style.width = left + "px";
    if (leftCol.clientHeight < 500)
        leftCol.style.minHeight = (maxH - headerH - 70) + "px";
    /* display/hide logout/register/sign in button */
    if (logged) {
        var hideElems = document.querySelectorAll(".user-menu");

        for (var i = 0; i < hideElems.length; i++) {
            hideElems[i].style.display = 'none';
        }
        var showElems = document.querySelectorAll(".user");
        for (var i = 0; i < showElems.length; i++) {
            showElems[i].style.display = 'inherit';
        }
    } else {
        var showElems = document.querySelectorAll(".user");
        for (var i = 0; i < showElems.length; i++) {
            showElems[i].style.display = 'none';
        }
    }
}

function getXMLObject() {
    var xmlHttp = false;
    try {
        // Old version of IE
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            // For IE 6+
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e2) {
            xmlHttp = false;
        }
    }
    if (!xmlHttp && typeof XMLHttpRequest !== "undefined") {
        // For gecko based browser
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp;
}

var xmlhttp = new getXMLObject();

function sendServlet(how, what, carUser) {
    if (xmlhttp) {
        lock = true;
        xmlhttp.open(how, "service", true);
        xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        var data = document.implementation.createDocument(null, what, null);
        if (carUser !== null) {
            var name = data.createElement("nome");
            name.appendChild(data.createTextNode(carUser));
            var root = data.getElementsByTagName(what)[0];
            root.appendChild(name);
        }
        xmlhttp.onreadystatechange = handleServerResponse;
        console.log("send");
        xmlhttp.send(data);
    }
}

function handleServerResponse() {
    if (xmlhttp.readyState < 4)
        document.getElementById('main').innerHTML = "<h3>Loading...</h3>";
    if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
        document.getElementById("main").innerHTML = xmlhttp.responseText;
    }
}

function infoCar(car) {
    sendServlet("POST", "auction", car);
}

function infoUser(user) {
    sendServlet("POST", "showUser", user);
}

function checkPass() {
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    var message = document.getElementById('check');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    if (pass1.value === pass2.value) {
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!";
        document.getElementsByName("registerButton")[0].disabled = false;
    } else {
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!";
        document.getElementsByName("registerButton")[0].disabled = true;

    }
}

function logout() {
    var form = document.createElement("form");
    var log = document.createElement("input");
    form.method = "POST";
    form.action = "service";
    log.value = "logout";
    log.name = "submitPost";
    form.appendChild(log);
    document.body.appendChild(form);
    form.submit();
}

function getNotifications() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "service", true);
    var data = document.implementation.createDocument(null, "getNotification", null);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var answer = xmlhttp.responseXML;
            if (answer.documentElement.tagName === "newOffer") {
                var msg = answer.documentElement.childNodes[1].textContent;
                document.getElementById("offer").innerHTML = "<label><b>Prezzo</b>: " + msg + "</label>";
                document.getElementById("makeOffer").value = msg;
            } else
                return;
            getNotifications();
        }
    };
    console.log("send");
    xmlhttp.send(data);
}

function check(car, price) {
    var offer = document.getElementById("makeOffer").value;
    if (offer === "" || parseInt(offer) <= parseInt(price)) {
        alert("Devi inserire un valore valido");
    } else
        sendOffer(car, offer);
}

function sendOffer(car, offer) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "service", true);
    xmlhttp.setRequestHeader("Content-Type", "text/xml");
    var data = document.implementation.createDocument("", "newOffer", null);
    var name = data.createElement("name");
    var newOffer = data.createElement("offer");
    name.appendChild(data.createTextNode(car));
    newOffer.appendChild(data.createTextNode(offer));
    var root = data.documentElement;
    root.appendChild(name);
    root.appendChild(newOffer);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var answer = xmlhttp.responseXML;
            if (answer.documentElement.tagName === "ok") {
                document.getElementById("result").innerHTML = "Offerta Inviata!";
                document.getElementById("offer").innerHTML = "<label><b>Prezzo</b>: " + offer + "</label>";
                getNotifications();
            } else
                document.getElementById("result").innerHTML = "Devi inserire un valore maggiore dell'offerta precedente";
        }
    };

    xmlhttp.send(data);
}

function showOffer(car, price) {
    //sendservlet startAuction
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "service", true);
    xmlhttp.setRequestHeader("Content-Type", "text/xml");
    var data = document.implementation.createDocument("", "startAuction", null);
    var name = data.createElement("name");
    name.appendChild(data.createTextNode(car));
    var root = data.documentElement;
    root.appendChild(name);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var answer = xmlhttp.responseXML;
            if (answer.documentElement.tagName === "ok") {
                var msg = answer.documentElement.childNodes[0].textContent;
                document.getElementById("offer").innerHTML = "<label><b>Prezzo</b>: " + msg + "</label>";
                getNotifications();
            } else
                return;
        }
    };
    xmlhttp.send(data);
    var sendAuction = document.getElementById("sendAuction");
    var inp = document.getElementById("makeOffer");
    if (inp.type === "hidden") {
        inp.type = "number";
        var send = document.createElement("button");
        send.name = "sendOffer";
        send.innerHTML = "Invia";
        var label = document.createElement("p");
        label.id = "result";
        sendAuction.appendChild(send);
        sendAuction.appendChild(label);
        send.onclick = function () {
            check(car, price);
        };
    }
}

function sellCar(car) {
    sendServlet("POST", "sellCar", car);
}

function send(what, where) {
    var worker = new Worker('js/basicServlet.js');
    worker.addEventListener('message', receiveMessage);
    function receiveMessage(e) {
        document.getElementById(where).innerHTML = e.data;
        worker.terminate();
        worker = undefined;
    }
    worker.postMessage(what);
}