$(document).ready(function () {
    //var currentUserId = getCurrentUserId();

    const profileData = {
        "username":"Gosho",
        "email":"gosho@gs.bg",
        "university":"SU",
        "faculty":"FMI",
        "degree":"SE",
        "discipline":[{
            "id":1,
            "name":"GE"
        },{
            "id":2,
            "name":"IS"
        }],
        "reviews":[{
            "id":1,
            "discipline":"IS",
            "comment":"jnrejnkfskjnmfkmmlkdfckmjdfco"
        },{
            "id":2,
            "discipline":"GE",
            "comment":"jnrejnkfskjnmfkmmlkdfckmjdfco"
        }]
    };
    console.log(profileData);
    displayUserInfo(profileData);
    displayUserDisciplines(profileData.discipline);
    displayUserReviews(profileData.reviews);
});

function getCurrentUserId() {
   //what we need to get, so we make a request for user data
    return sessionStorage.getItem('userId');
}
function displayUserInfo(userData) {
    // Update the #user-info element with user information
    $('#user-info').html('<h2>' + userData.username + '</h2><p>Имейл: ' + userData.email + '</p>' +
        '<p>Университет: ' + userData.university + '</p><p>Факултет: ' + userData.faculty + '</p><p>Специалност: ' + userData.degree + '</p>');

}

function displayUserDisciplines(disciplines) {
    var disciplinesList = '<h3>Дисциплини:</h3><ul>';
    disciplines.forEach(function (discipline) {
        //may need a change depending on how we need to route it
        disciplinesList += '<li><a href="/create_review/id=' + discipline.id + '">' + discipline.name + '</a></li>';
    });
    disciplinesList += '</ul>';
    $('#user-disciplines').html(disciplinesList);
}

function displayUserReviews(reviews) {
    var reviewsList = '<h3>Рецензии:</h3><ul>';
    //change with the logic for making review cards
    reviews.forEach(function (review) {
        reviewsList += '<div class="review-card">';
        reviewsList +='<h3>'+review.discipline+'</h3>';
        reviewsList +='<p>'+review.comment+'</p>'
        reviewsList += '</div>';
    });
    $('#user-reviews').html(reviewsList);
}