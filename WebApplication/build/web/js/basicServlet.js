self.addEventListener('message', receiveMessage);

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

function receiveMessage(e) {

    var xmlhttp = new getXMLObject();
    xmlhttp.open("POST", "/WebApplication/service", true);
    xmlhttp.setRequestHeader("Content-Type", "text/xml");
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            self.postMessage(xmlhttp.responseText);
        }
    };
    xmlhttp.send(e.data);
}