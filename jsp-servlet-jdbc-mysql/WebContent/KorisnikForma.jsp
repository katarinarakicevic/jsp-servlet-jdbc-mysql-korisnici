<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Aplikacija upravljanja korisnicima</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style type="text/css">
        
        body {
  background-color: linen;
}

        
        </style>
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #ff3333">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand" style= "color:#1f1f2e")> Upravljanje korisnicima </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link" style= "color:#1f1f2e;">Korisnici</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                       <c:if test="${korisnik != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${korisnik == null}">
                            <form action="insert" method="post">
                        </c:if>
                        
                       <caption>
                            <h2>
                                <c:if test="${korisnik != null}">
                                    Izmeni korisnika
                                </c:if>
                                <c:if test="${korisnik == null}">
                                    Dodaj novog korisnika
                                </c:if>
                               
                            </h2>
                        </caption>


                <c:if test="${korisnik != null}">
                            <input type="hidden" name="id" value="<c:out value='${korisnik.id}' />" />
                        </c:if>

                            
                           <fieldset class="form-group">
                            <label>Korisničko ime</label> <input type="text" value="<c:out value='${korisnik.ime}' />" class="form-control" name="ime" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label> Email korisnika</label> <input type="text" value="<c:out value='${korisnik.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Zemlja korisnika</label> <input type="text" value="<c:out value='${korisnik.zemlja}' />" class="form-control" name="zemlja">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Sačuvaj</button>
                        </form>
                           
                        
                    
                    </div>
                
                </div>
            </div>
        </body>

        </html>