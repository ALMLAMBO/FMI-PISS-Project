$(document).ready(function () {
    //change with fetched data
    const userData = [
        {
            "user_id":1,
            "image": "user1.png",//in real data base64 so u need to have function for this
            "name": "John Doe",
            "facultyNumber": "12345",
            "university": "Example University",
            "faculty": "Computer Science",
            "degree": "Bachelor's"
        },
        {
            "user_id":2,
            "image": "user2.jpg",
            "name": "Jane Smith",
            "facultyNumber": "67890",
            "university": "Sample University",
            "faculty": "Electrical Engineering",
            "degree": "Master's"
        }
    ];

    const reviewData=[
        {
            "review_id":1,
            "username":"Gosho",
            "discipline":"GE",
            "degree":"IS",
            "commenter":"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nec tincidunt praesent semper feugiat nibh sed pulvinar. Urna condimentum mattis pellentesque id nibh tortor. Est lorem ipsum dolor sit amet. Mi quis hendrerit dolor magna eget est. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Ipsum dolor sit amet consectetur adipiscing elit. Ultrices eros in cursus turpis massa. Ac turpis egestas maecenas pharetra convallis posuere."
        },
        {
            "review_id":2,
            "username": "Pesho",
            "discipline":"IS",
            "degree":"SE",
            "commenter":"y8uisfureujWAE$Ryhmw3uihewriyhurel wryuieauih,i,uhuiwerui,hywefuiho.wefsui.aerkinj."
        }
    ];

    // Function to generate user cards
    function generateUserCards(data) {
        const container = document.getElementById('users_pending');

        data.forEach(user => {
            const card = document.createElement('div');
            card.className = 'user-card';

            const userIdInput = document.createElement('input');
            userIdInput.type = 'hidden';
            userIdInput.name = 'user_id';
            userIdInput.value = user.user_id;
            card.appendChild(userIdInput);

            const image = document.createElement('img');
            image.src = user.image;
            card.appendChild(image);

            const name = document.createElement('p');
            name.textContent = `Име: ${user.name}`;
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

    // Call the function with your JSON data
    generateUserCards(userData);
    generateReviewCards(reviewData);

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