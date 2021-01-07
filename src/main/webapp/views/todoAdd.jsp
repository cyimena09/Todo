<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>


<div class="container contenu">
    <main class="row justify-content-center">
        <div class="col-6">
            <h4>Ajout d'une note</h4>

            <form action="add-todo" method="post">
                <input class="form-control mb-3" type="text" name="description" placeholder="Description">
                <div class="form-group">
                    <label for="categories">Sélectionnez une catégorie</label>
                    <select class="form-control" name="categories" id="categories">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="d-grid gap-2 mt-4">
                    <input type="submit" class="btn btn-primary btn-block" value="Ajouter">
                </div>
            </form>

        </div>
    </main>
</div>


<%@include file="../templates/footer.jsp"%>