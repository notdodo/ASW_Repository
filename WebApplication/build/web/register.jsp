<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="WEB-INF/jspf/header.jspf"/>
<div class="left-column">
    <h2>Register</h2>
    <form class="register" action="service" method="POST" accept-charset="utf-8">
        <input type="hidden" name="submitPost" value="register">
        <p>
            <label>Nome</label>
            <input type="text" name="nome" value="" required>
        </p>
        <p>
            <label>Cognome</label>
            <input type="text" name="cognome" value="" required>
        </p>
        <p>
            <label>Nickname</label>
            <input type="text" name="nickname" value="" required>
        </p>
        <p>
            <label>Password</label>
            <input id="pass1" type="password" name="password1" value="" required onkeyup="checkPass();">
        </p>
        <p>
            <label>Ripeti Password</label>
            <input id="pass2" type="password" name="password2" value="" required onkeyup="checkPass();"><br/>
            <span id="check"></span>
        </p>
        <p>
            <label>E-Mail</label>
            <input type="email" name="email" value="" required>
        </p>
        <p>
            <label>Indirizzo</label>
            <input type="text" name="indirizzo" value="" required>
        </p>
        <button name="registerButton" type="submit">Registrati</button>
    </form>
</div>
<jsp:include page="WEB-INF/jspf/rightcol.jspf"/>
<jsp:include page="WEB-INF/jspf/footer.jspf"/>