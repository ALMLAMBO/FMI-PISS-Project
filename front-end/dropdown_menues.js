$(document).ready(function () {
    let universityDropdown = $('#university-dropdown');
    let facultyDropdown = $('#faculty-dropdown');
    let degreeDropdown = $('#degree-dropdown');

    universityDropdown.empty();
    facultyDropdown.empty();
    degreeDropdown.empty();

    universityDropdown.append('<option selected="true">Университет</option>');
    universityDropdown.prop('selectedIndex', 0);

    facultyDropdown.append('<option selected="true" disabled>Факултет</option>');
    facultyDropdown.prop('selectedIndex', 0);

    degreeDropdown.append('<option selected="true" disabled>Дисциплина</option>');
    degreeDropdown.prop('selectedIndex', 0);

    const universityUrl = 'http://localhost:8080/api/home/get-universities'; // Replace with the actual URL for universities JSON

// Populate university dropdown
    $.getJSON(universityUrl)
        .done(function (data) {
            $.each(data, function (key, entry) {
                universityDropdown.append($('<option></option>').attr('value', entry.id).text(entry.name));
            });
        })
        .fail(function (jqxhr, textStatus, error) {
            const err = textStatus + ', ' + error;
            console.error('Error loading university JSON file: ' + err);
        });

// Handle university dropdown change event
    universityDropdown.change(function () {
        const selectedUniversityId = $(this).val();
        const facultyUrl = 'http://localhost:8080/api/home/get-faculties'; // Replace with the actual URL for faculties JSON

        const facultyJson=JSON.stringify({id: parseInt(selectedUniversityId)});
        //console.log(facultyJson);
        $.get(facultyUrl + '/' + selectedUniversityId)
            .done(function (data) {
                console.log('Success:', data);
                const faculties = data;
                faculties.sort(entry=>entry.id);
                $.each(faculties, function (key, entry) {
                    facultyDropdown.append($('<option></option>').attr('value', entry.id).text(entry.facultyName));
                });
            })
            .fail(function (jqxhr, textStatus, error) {
                console.error('Error:', error);
            });
    });

// Handle faculty dropdown change event
    facultyDropdown.change(function () {
        const selectedFacultyId = $(this).val();
        const degreeURL = 'http://localhost:8080/api/home/get-degrees';

        // Clear degree dropdown
        degreeDropdown.empty();

        degreeDropdown.append('<option selected="true" disabled>Дисциплина</option>');
        degreeDropdown.prop('selectedIndex', 0);


        $.get(degreeURL + '/' + selectedFacultyId)
            .done(function (data) {
                console.log('Success:', data);
                const degrees = data;
                $.each(degrees, function (key, entry) {
                    degreeDropdown.append($('<option></option>').attr('value', entry.id).text(entry.title));
                });
            })
            .fail(function (jqxhr, textStatus, error) {
                console.error('Error:', error);
            });
    });
});