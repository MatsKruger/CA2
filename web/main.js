var address;
$("#newPersonHobbies").select2({
    placeholder: 'Find hobbies',
    theme: "bootstrap",
    width: '100%',
    ajax: {
        url: "/CA2/api/hobbies",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                q: params.term // search term
                        //page: params.page
            };
        },
        processResults: function (data, params) {
            return {
                results: data
            };
        },
        cache: true
    },
    minimumInputLength: 1,
    templateResult: (obj) => obj.text || obj.name,
    templateSelection: (obj) => obj.text || obj.name
});
$('#newPersonAddress').dawaautocomplete({
    appendTo: $('#newPersonAddress').parent(),
    select: function (event, addresss) {
        address = addresss;
    }
});

$('#newCompanyAddress').dawaautocomplete({
    appendTo: $('#newCompanyAddress').parent(),
    select: function (event, addresss) {
        address = addresss;
    }
});

//$('#newCompanyModal').modal('show');

$('#saveNewPerson').on('click', function () {
    var form = $('#newPersonModal').find('form');
    var phones = ($('#newPersonPhones').val() ? $('#newPersonPhones').val().split('|').map(function (item) {
        var items = item.split(',');
        return {
            number: items[0],
            description: items[1]
        };
    }) : null);
    var json = {
        firstName: $('#newPersonFirstName').val(),
        lastName: $('#newPersonLastName').val(),
        email: $('#newPersonEmail').val(),
        phones: phones,
        hobbies: $.map($('#newPersonHobbies').select2('data'), function (obj) {
            return {
                name: obj.name,
                description: obj.description
            };
        }),
        address: {
            street: address.data.vejnavn,
            additionalInfo: address.data.husnr + (address.data.etage ? ', ' + address.data.etage : '') + (address.data.dør ? '. ' + address.data.dør : ''),
            city: {
                zipCode: address.data.postnr,
                city: address.data.postnrnavn
            }
        }
    };
    console.log(JSON.stringify(json));
    $.ajax({
        type: "POST",
        url: '/CA2/api/person',
        data: JSON.stringify(json),
        contentType: 'application/json',
        dataType: 'json',
        success: function () {
            console.log('nu');
        }
    });
});

$('#saveNewCompany').on('click', function () {
    console.log("in save comp")
    var form = $('#newCompanyModal').find('form');
    var phones = ($('#newCompanyPhones').val() ? $('#newCompanyPhones').val().split('|').map(function (item) {
        var items = item.split(',');
        return {
            number: items[0],
            description: items[1]
        };
    }) : null);
    var json = {
        name: $('#newCompanyName').val(),
        cvr: $('#newCompanyCvr').val(),
        email: $('#newCompanyEmail').val(),
        description: $('#newCompanyDescription').val(),
        numEmployees: $('#newCompanyNumEmployees').val(),
        marketValue: $('#newCompanyMarketValue').val(),
        phones: phones,
        address: {
            street: address.data.vejnavn,
            additionalInfo: address.data.husnr + (address.data.etage ? ', ' + address.data.etage : '') + (address.data.dør ? '. ' + address.data.dør : ''),
            city: {
                zipCode: address.data.postnr,
                city: address.data.postnrnavn
            }
        }
    };
    console.log(JSON.stringify(json));
    $.ajax({
        type: "POST",
        url: '/CA2/api/company',
        data: JSON.stringify(json),
        contentType: 'application/json',
        dataType: 'json',
        success: function () {
            console.log('nu');
        }
    });
});

$('#saveNewHobby').on('click', function () {
    var json = {
        name: $('#newHobbyName').val(),
        description: $('#newHobbyDescription').val()
    };
    $.ajax({
        type: "POST",
        url: '/CA2/api/hobbies',
        data: JSON.stringify(json),
        contentType: 'application/json',
        dataType: 'json',
        success: function () {
            console.log('nu');
        }
    });
});

$('#newCompanySearch').on('keypress', function (e) {
    if (e.keyCode === 13) {
        $.getJSON('http://cvrapi.dk/api?search=' + this.value + '&country=DK', function (data) {
            console.log(data);
            $('#newCompanyName').val(data.name);
            $('#newCompanyCvr').val(data.vat);
            $('#newCompanyDescription').val(data.companydesc);
            $('#newCompanyEmail').val(data.email);
            $('#newCompanyPhones').val(data.phone);
            var maxEmployees = (data.employees ? Math.max.apply(Math, data.employees.split('-').map(function (item) {
                return parseInt(item);
            })) : null);
            $('#newCompanyNumEmployees').val(maxEmployees);
            getAddress(data.address, data.zipcode, function (addr) {
                address = addr;
                $('#newCompanyAddress').val(addr.adressebetegnelse);
            });
        });
    }
});

function getAddress(q, zipcode, callback) {
    $.getJSON('http://dawa.aws.dk/adresser?q=' + q + '&postnr=' + zipcode, function (data) {
        callback(data[0]);
    });
}


var hobbiesDiv = $('#hobbies');
var personsDiv = $('#persons');
var companiesDiv = $('#companies');

$.ajax({
    type: "GET",
    url: '/CA2/api/hobbies',
    contentType: 'application/json',
    dataType: 'json',
    success: function (data) {
        var tbody = hobbiesDiv.find('tbody');

        tbody.html("");
        for (var i = 0; i < data.length; i++) {
            tbody.append('<tr><td>' + data[i].id + '</td><td>' + data[i].name + '</td><td>' + data[i].description + '</td></tr>');
        }
    }
});

$.ajax({
    type: "GET",
    url: '/CA2/api/person',
    contentType: 'application/json',
    dataType: 'json',
    success: function (data) {
        var tbody = personsDiv.find('tbody');

        tbody.html("");

        for (var i = 0; i < data.length; i++) {
            var htmlString = '<tr><td><input class="selected-row" type="checkbox" value="' + data[i].id + '"></td><td>' + data[i].id + '</td><td>' + data[i].firstName + '</td><td>' + data[i].lastName + '</td><td>' + data[i].email + '</td><td class="s">';
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
});

$.ajax({
    type: "GET",
    url: '/CA2/api/company',
    contentType: 'application/json',
    dataType: 'json',
    success: function (data) {
        var tbody = companiesDiv.find('tbody');

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
});

$('#delete-btn').on('click', function (e) {
    e.preventDefault();
    e.stopPropagation();
    var selected = $('.selected-row:checked');
    var ids = $.map(selected, function (obj) {
        return parseInt(obj.value);
    });
    $.ajax({
        url: '/CA2/api/person?ids=' + ids.join(','),
        type: 'DELETE',
        contentType: 'application/json',
        dataType: 'json',
        success: function (result) {
            selected.closest('tr').remove();
        }
    });

//    $.each(selected, function(i,v) {
//        
//    });
});