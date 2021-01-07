<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>


<div class="container contenu">
    <div class="row justify-content-center">
        <div class="col-4">
            <h3 class="p-3 bg-primary text-white text-center">Login Todo App</h3>

            <form action="login" method="post">
                <p class="bg-danger text-white">${errorMessage}</p>
                <input class="form-control mb-3" type="text" name="pseudo" placeholder="Pseudo">
                <input class="form-control mb-3" type="password" name="password" placeholder="Mot de passe">
                <input class="btn btn-primary btn-block" type="submit" value="Login">
            </form>
        </div>
    </div>
</div>


<%@include file="../templates/footer.jsp"%>