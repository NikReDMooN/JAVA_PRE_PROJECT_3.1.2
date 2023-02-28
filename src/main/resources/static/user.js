getCurrentUser()

function getCurrentUser() {
    alert('ALERT USER SOLO')
    fetch("http://localhost:8080/userApi/auth")
        .then(res => res.json())
        .then(user => {
            alert(user.id)
            alert(user.firstName)
            $('#emailCurrentUser').append(`<span>${user.email}</span>`)
            $('#roleCurrentUser').append(`<span>${user.roles.map(role => role.name)}</span>`)
            const userdata  = `$(
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.roles.map(role => role.name)}</td>
                    </tr>)`;
            $('#oneUser').append(userdata   )
            alert(user)
        })
}