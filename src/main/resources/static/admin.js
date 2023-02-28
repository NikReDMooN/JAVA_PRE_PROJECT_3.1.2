
const tbody = $('#AllUsers');
getTableUser()

function getTableUser() {
    alert("HI")
    tbody.empty()
    fetch("http://localhost:8080/adminApi/users")
        .then(res => res.json())
        .then(usersData => {

            usersData.forEach(user => {
                alert(user)
                const tableOfUsers = `$(
                    <tr>
                        <td class="pt-3" id="userID">${user.id}</td>
                        <td class="pt-3" >${user.firstName}</td>
                        <td class="pt-3" >${user.lastName}</td>  
                        <td class="pt-3" >${user.age}</td>
                        <td class="pt-3" >${user.email}</td>
                        <td class="pt-3" >${user.roles.map(role => role.name)}</td>
                        <td>
                            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#edit" onclick="editModal(${user.id})">
                            Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>)`;
                tbody.append(tableOfUsers)
            })
        })
}


let form = document.forms["create"];
createNewUser()

function createNewUser() {
    form.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles= [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) roles.push({

                id: form.roles.value,
                name: "ROLE_" + form.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/adminApi/user", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.age.value,
                email: form.email.value,
                notEncodePass: form.password.value,
                roles: roles
            })
        }).then(() => {
            form.reset();
            $('#home-tab').click();
            getTableUser();
        });
    });
}




async function openAndFillInTheModal(form, modal, id){
    modal.show();
    let user = await getOneUser(id);
    form.id.value = user.id;
    form.name.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.email.value = user.email;
    form.roles.value = user.roles;
}

async function getOneUser(id) {
    let url = "http://localhost:8080/adminApi/user/" + id;
    let response = await fetch(url);
    return await response.json();
}

let formEdit = document.forms["formEdit"];
editUser();

async function editModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#edit'));
    await openAndFillInTheModal(formEdit, modal, id);
}

function editUser() {
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) roles.push({
                id: formEdit.roles.value,
                name: "ROLE_" + formEdit.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/adminApi/user/" + formEdit.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                firstName: formEdit.firstName.value,
                lastName: formEdit.lastName.value,
                age: formEdit.age.value,
                email: formEdit.email.value,
                notEncodePass: formEdit.password.value,
                roles: roles
            })
        }).then(() => {
            $('#closeEdit').click();
            getTableUser()
        });
    });
}


let deleteForm = document.forms["formDelete"]

async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await openAndFillInTheModal(deleteForm, modal, id);
    switch (deleteForm.roles.value) {
        case '1':
            deleteForm.roles.value = 'ADMIN';
            break;
        case '2':
            deleteForm.roles.value = 'USER';
            break;
    }
    deleteUser()
}

function deleteUser() {
    deleteForm .addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/adminApi/user/" + deleteForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#closeDelete').click();
            getTableUser();
        });
    });
}



