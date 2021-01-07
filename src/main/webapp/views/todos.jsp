<%@page pageEncoding="UTF-8" %>

<%@include file="../templates/header.jsp"%>

<%@include file="../templates/navigation.jsp"%>


<div class="container contenu">
    <main class="row justify-content-center">
        <div class="col-6">
            <h4>Liste des trucs a faire</h4>
            <table class="table table-striped table-hover table-bordered">
                <thead class="table-dark">
                <tr>
                    <th>Description</th>
                    <th>Catégorie</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.category}</td>
                        <th class="text-center">
                            <a href="delete-todo?id=${todo.id}">
                                <i class="fas fa-trash-alt"></i> Supprimer
                            </a>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="d-grid gap-2">
                <a href="add-todo" class="btn btn-info">
                    <i class="fas fa-folder-plus"></i> Nouvelle note
                </a>
            </div>
        </div>
    </main>
</div>


<%@include file="../templates/footer.jsp"%>