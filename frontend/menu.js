$(document).ready(function () {
    var isAuthenticated = sessionStorage.getItem('authenticated') === 'true';
    var isAdmin = sessionStorage.getItem('isAdmin') === 'true';

    // Create menu based on user status
    createMenu(isAuthenticated, isAdmin);
});

function createMenu(isAuthenticated, isAdmin) {
    var menuList = document.getElementById('menuList');


    // Admin Panel Link (only for admin)
    if (isAdmin) {
        var adminPanelLink = createLink('Admin Panel', 'admin_page.html');
        menuList.appendChild(adminPanelLink);
    }else{
        // Home Link
        var homeLink = createLink('Home', 'home.html');
        menuList.appendChild(homeLink);

        // Profile Link
        var profileLink = createLink('Profile', 'profile.html');
        menuList.appendChild(profileLink);
    }

    // Login/Logout Link
    var loginLogoutLink = createLink(isAuthenticated ? 'Logout' : 'Login', '#');
    loginLogoutLink.addEventListener('click', function () {
        if (isAuthenticated) {
            logout();
        } else {
            window.location.href = 'login.html';
        }
    });
    menuList.appendChild(loginLogoutLink);
}

function createLink(text, href) {
    var listItem = document.createElement('li');
    var link = document.createElement('a');
    link.textContent = text;
    link.href = href;
    listItem.appendChild(link);
    return listItem;
}

function logout() {
    sessionStorage.setItem('authenticated', 'false');
    sessionStorage.setItem('isAdmin', 'false');
    window.location.href = 'login.html';
    // Additional logout logic if needed
}