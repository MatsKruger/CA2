$("#pValues li").on("click", function () {
    $("#pSearchByValue").val($(this).text());
    $("#pSearchByValueDisplay").text($(this).text());
});

$("#cValues li").on("click", function () {
    $("#cSearchByValue").val($(this).text());
    $("#cSearchByValueDisplay").text($(this).text());
});

var resultDiv = $("#result");

function handlePerson(data) {
    var thead = resultDiv.find('thead');
    thead.html("");
    thead.append("<tr><th></th><th>ID</th><th>First name</th><th>Last name</th><th>Email</th><th>Phones</th><th>Address</th><th>Hobbies</th></tr>");
    var tbody = resultDiv.find('tbody');
    tbody.html("");
    for (var i = 0; i < data.length; i++) {
        //excluded <td><input class="selected-row" type="checkbox" value="' + data[i].id + '"></td>
        var htmlString = '<tr><td>' + data[i].id + '</td><td>' + data[i].firstName + '</td><td>' + data[i].lastName + '</td><td>' + data[i].email + '</td><td class="s">';
        if (data[i].phones.length > 0) {
            for (var j = 0; j < data[i].phones.length; j++) {
                htmlString += data[i].phones[j].number + ',' + data[i].phones[j].description + '|';
            }
            htmlString = htmlString.substring(0, htmlString.length - 1);
        }
        htmlString += '</td><td class="s">' + data[i].address.street + ' ' + data[i].address.additionalInfo + ' ' + data[i].address.city.zipCode + ' ' + data[i].address.city.city + '</td><td class="s">';

        if (data[i].hobbies.length > 0) {
            for (var j = 0; j < data[i].hobbies.length; j++) {
                htmlString += data[i].hobbies[j].name + ',' + data[i].hobbies[j].description + '|';
            }

            htmlString = htmlString.substring(0, htmlString.length - 1);
        }
        htmlString += '</tr>';
        tbody.append(htmlString);
    }
}

$("#pSubmitBtn").on("click", function () {
    searchTerm = $("#pTextSearchTerm").val();
    //if (searchTerm === "") return;
    switch ($("#pSearchByValue").val().trim()) {
        case "Name":
            $.ajax({
                type: "GET",
                url: '/CA2/api/person/n/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handlePerson(data);
                }
            });
            break;
        case "City":
            $.ajax({
                type: "GET",
                url: '/CA2/api/person/c/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handlePerson(data);
                }
            });
            break;
        case "Zip":
            $.ajax({
                type: "GET",
                url: '/CA2/api/person/z/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handlePerson(data);
                }
            });
            break;
        case "Hobby":
            console.log("unsupported");
            break;
    }
});

function handleCompany(data) {
    var thead = resultDiv.find('thead');
    thead.html("");
    thead.append("<tr><th>ID</th><th>Name</th><th>Description</th><th>CVR</th><th>Email</th><th>Phones</th><th>Address</th><th>Employees</th><th>Value</th></tr>");
    var tbody = resultDiv.find('tbody');
    tbody.html("");
    for (var i = 0; i < data.length; i++) {
        var htmlString = '<tr><td>' + data[i].id + '</td><td>' + data[i].name + '</td><td>' + data[i].description + '</td><td><td>' + data[i].cvr + '</td>' + data[i].email + '</td><td class="s">';
        if (data[i].phones.length > 0) {
            for (var j = 0; j < data[i].phones.length; j++) {
                htmlString += data[i].phones[j].number + ',' + data[i].phones[j].description + '|';
            }
            htmlString = htmlString.substring(0, htmlString.length - 1);
        }
        htmlString += '</td><td class="s">' + data[i].address.street + ' ' + data[i].address.additionalInfo + ' ' + data[i].address.city.zipCode + ' ' + data[i].address.city.city + '</td><td class="s">';
        htmlString += '</tr>';
        tbody.append(htmlString);
    }
}

$("#cSubmitBtn").on("click", function () {
    searchTerm = $("#cTextSearchTerm").val();
    //if (searchTerm === "") return;
    switch ($("#cSearchByValue").val().trim()) {
        case "Name":
            $.ajax({
                type: "GET",
                url: '/CA2/api/company/n/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handleCompany(data);
                }
            });
            break;
        case "CVR":
            $.ajax({
                type: "GET",
                url: '/CA2/api/company?cvr=' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handleCompany(data);
                }
            });
            break;
        case "More employees than":
            $.ajax({
                type: "GET",
                url: '/CA2/api/company/moreemp/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handleCompany(data);
                }
            });
            break;
        case "Less employees than":
            $.ajax({
                type: "GET",
                url: '/CA2/api/company/lessemp/' + searchTerm,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    handleCompany(data);
                }
            });
            break;
    }
});
