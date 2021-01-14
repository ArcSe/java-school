<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../general/template.jsp"%>
<html>
<head>
    <title>Personal profile</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h1 class="display-4">${client.name} ${client.surname}</h1>
            </div>
            <div class="pt-4">
                <a class="btn btn-light" href="#" role="button">Setting</a>
            </div>
        </div>
        <dl class="row">
            <dt class="col-sm-1">Birthday:</dt>
            <dd class="col-sm-11"> 10 мая 1234</dd>

            <dt class="col-sm-1">Passport:</dt>
            <dd class="col-sm-11">${client.passport}</dd>

            <dt class="col-sm-1">Address:</dt>
            <dd class="col-sm-11">${client.address}</dd>

            <dt class="col-sm-1">Email:</dt>
            <dd class="col-sm-11">${client.email}</dd>
        </dl>
        <table class="table pt-5">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Number</th>
                <th scope="col">Tariff</th>
                <th scope="col">Options</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${client.contracts}" var="contract">
                    <tr>
                        <td>${contract.id}</td>
                        <td>${contract.number}</td>
                        <td>${contract.tariff.name}</td>
                        <td>${contract.options}</td>
                        <td>
                            <a class="btn btn-light" href="#" role="button">Change tariff</a>
                            <a class="btn btn-primary" href="#" role="button">Lock</a>
                            <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target=".multi-collapse" aria-expanded="false" aria-controls="multiCollapseExample1">Show</button>
                        </td>
                    </tr>
                </c:forEach>
                <div class="row">
                    <div class="col">
                        <div class="collapse multi-collapse" id="multiCollapseExample1">
                            <div class="card card-body">
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                            </div>
                        </div>
                    </div>
            </tbody>
        </table>
    </div>
</body>
</html>