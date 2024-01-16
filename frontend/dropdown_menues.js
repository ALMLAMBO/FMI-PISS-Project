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

    const universityUrl = '/ex_json/universities.json'; // Replace with the actual URL for universities JSON
    const facultyUrl = '/ex_json/faculties.json'; // Replace with the actual URL for faculties JSON
    const degreeURL = '/ex_json/degrees.json'
// Populate university dropdown
    $.getJSON(universityUrl)
        .done(function (data) {
            $.each(data.universities, function (key, entry) {
                universityDropdown.append($('<option></option>').attr('value', entry.id).text(entry.uni_name));
            });
        })
        .fail(function (jqxhr, textStatus, error) {
            const err = textStatus + ', ' + error;
            console.error('Error loading university JSON file: ' + err);
        });

// Handle university dropdown change event
    universityDropdown.change(function () {
        const selectedUniversityId = $(this).val();

        // Clear faculty and degree dropdowns
        facultyDropdown.empty();
        degreeDropdown.empty();

        facultyDropdown.append('<option selected="true" disabled>Факултет</option>');
        facultyDropdown.prop('selectedIndex', 0);

        degreeDropdown.append('<option selected="true" disabled>Дисциплина</option>');
        degreeDropdown.prop('selectedIndex', 0);

        // Populate faculty dropdown based on selected university
        $.getJSON(facultyUrl)
            .done(function (data) {
                const faculties = data.faculties.filter(faculty => faculty.uni_id == selectedUniversityId);
                $.each(faculties, function (key, entry) {
                    facultyDropdown.append($('<option></option>').attr('value', entry.faculty_id).text(entry.faculty_name));
                });
            })
            .fail(function (jqxhr, textStatus, error) {
                const err = textStatus + ', ' + error;
                console.error('Error loading faculty JSON file: ' + err);
            });
    });

// Handle faculty dropdown change event
    facultyDropdown.change(function () {
        const selectedFacultyId = $(this).val();

        // Clear degree dropdown
        degreeDropdown.empty();

        degreeDropdown.append('<option selected="true" disabled>Дисциплина</option>');
        degreeDropdown.prop('selectedIndex', 0);

        // Populate degree dropdown based on selected faculty
        $.getJSON(degreeURL) // Replace with the actual URL for degrees JSON
            .done(function (data) {
                const degrees = data.faculty_degrees.filter(item => item.faculty_id == selectedFacultyId);
                $.each(degrees, function (key, entry) {
                    degreeDropdown.append($('<option></option>').attr('value', entry.degree_id).text(entry.degree_title));
                });
            })
            .fail(function (jqxhr, textStatus, error) {
                const err = textStatus + ', ' + error;
                console.error('Error loading degree JSON file: ' + err);
            });
    });
});