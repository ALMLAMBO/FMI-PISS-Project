$(document).ready(function () {
    $.ajax({
        url: 'http://localhost:8080/api/admin/get-user-requests',
        type: 'get',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        },
        dataType: 'json',
        success:function (data){
            generateUserCards(data);
        }
    });

    $.ajax({
        url: 'http://localhost:8080/api/admin/get-review-requests',
        type: 'get',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + sessionStorage.getItem('token')
        },
        dataType: 'json',
        success:function (data){
            generateReviewCards(data);
        }
    });

    // Function to generate user cards
    function generateUserCards(data) {
        const container = document.getElementById('users_pending');
        console.log(data);
        data.forEach(user => {
            const card = document.createElement('div');
            card.className = 'user-card';

            const userIdInput = document.createElement('input');
            userIdInput.type = 'hidden';
            userIdInput.name = 'user_id';
            userIdInput.value = user.user_id;
            card.appendChild(userIdInput);

            const image = document.createElement('image');
            image.src = 'data:image/png;base64,' + user.image;
            card.appendChild(image);

            const name = document.createElement('p');
            name.textContent = `Име: ${user.username}`;
            card.appendChild(name);

            const facultyNumber = document.createElement('p');
            facultyNumber.textContent = `Факултетен номер: ${user.facultyNumber}`;
            card.appendChild(facultyNumber);

            const university = document.createElement('p');
            university.textContent = `Университет: ${user.university}`;
            card.appendChild(university);

            const faculty = document.createElement('p');
            faculty.textContent = `Факултет: ${user.faculty}`;
            card.appendChild(faculty);

            const degree = document.createElement('p');
            degree.textContent = `Специалност: ${user.degree}`;
            card.appendChild(degree);

            // Dropdown for status (non/Accepted/Rejected)
            const statusDropdown = document.createElement('select');
            statusDropdown.className = 'status-dropdown';
            const statusOptions = ['Статус','Потвърди', 'Отхвърли'];
            statusOptions.forEach(option => {
                const statusOption = document.createElement('option');
                statusOption.value = option.toLowerCase();
                statusOption.textContent = option;
                statusDropdown.appendChild(statusOption);
            });
            card.appendChild(statusDropdown);

            container.appendChild(card);
        });
    }
    function generateReviewCards(data) {
        const container = document.getElementById('reviews_pending');

        data.forEach(review => {
            const card = document.createElement('div');
            card.className = 'review-card';

            const reviewIdInput = document.createElement('input');
            reviewIdInput.type = 'hidden';
            reviewIdInput.name = 'review_id';
            reviewIdInput.value = review.review_id;
            card.appendChild(reviewIdInput);

            const name = document.createElement('p');
            name.textContent = `Име: ${review.username}`;
            card.appendChild(name);

            const degree = document.createElement('p');
            degree.textContent = `Специалност: ${review.degree}`;
            card.appendChild(degree);

            const discipline = document.createElement('p');
            discipline.textContent = `Дисциплина: ${review.discipline}`;
            card.appendChild(discipline);

            const comment = document.createElement('p');
            comment.textContent = `Коментар: ${review.commenter}`;
            card.appendChild(comment);

            // Dropdown for status (non/Accepted/Rejected)
            const statusDropdown = document.createElement('select');
            statusDropdown.className = 'status-dropdown';
            const statusOptions = ['Статус','Потвърди', 'Отхвърли'];
            statusOptions.forEach(option => {
                const statusOption = document.createElement('option');
                statusOption.value = option.toLowerCase();
                statusOption.textContent = option;
                statusDropdown.appendChild(statusOption);
            });
            card.appendChild(statusDropdown);

            container.appendChild(card);
        });
    }

    document.getElementById('user_statuses').addEventListener('click', function () {
        const userCards = document.querySelectorAll('.user-card');

        // Create an array to store user status data
        const userStatuses = [];

        // Iterate through user cards to get user_id and status
        userCards.forEach(card => {
            const userIdInput = card.querySelector('input[name="user_id"]');
            const userId = userIdInput.value;
            const statusDropdown = card.querySelector('.status-dropdown');
            const status = statusDropdown.value;
            if(status!='статус') {
                // Push user status data to the array
                userStatuses.push({userId, status});
            }
        });

        // Convert the array to JSON
        const jsonData = JSON.stringify(userStatuses);

        // Simulate sending the JSON data (replace this with your actual AJAX request)
        console.log(jsonData);

    });

    document.getElementById('review_statuses').addEventListener('click', function () {
        const reviewCards = document.querySelectorAll('.review-card');

        // Create an array to store user status data
        const reviewStatus = [];

        // Iterate through user cards to get user_id and status
        reviewCards.forEach(card => {
            const reviewIdInput = card.querySelector('input[name="review_id"]');
            const reviewId = reviewIdInput.value;
            const statusDropdown = card.querySelector('.status-dropdown');
            const status = statusDropdown.value;
            if(status!='статус') {
                // Push user status data to the array
                reviewStatus.push({reviewId, status});
            }
        });

        // Convert the array to JSON
        const jsonData = JSON.stringify(reviewStatus);

        // Simulate sending the JSON data (replace this with your actual AJAX request)
        console.log(jsonData);
    });

});