window.addEventListener('load', function () {

    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologoSelect = document.querySelector('#odontologo_select');
            data.forEach(odontologo => {
                let option = document.createElement('option');
                option.value = odontologo.id;
                option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                odontologoSelect.appendChild(option);
            });
        });

    const selectForm = document.querySelector('#select_odontologo_form');
    selectForm.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#odontologo_select').value;
        if (!selectedId) {
            alert("Seleccione un odontólogo válido.");
            return;
        }

        fetch(`${url}/${selectedId}`, settings)
            .then(response => response.json())
            .then(odontologo => {
                document.querySelector('#odontologo_id').value = odontologo.id;
                document.querySelector('#odontologo_nombre').value = odontologo.nombre;
                document.querySelector('#odontologo_apellido').value = odontologo.apellido;
                document.querySelector('#odontologo_matricula').value = odontologo.matricula;

                document.querySelector('#odontologo_update_form').style.display = "block";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Error al cargar la información del odontólogo. Intente nuevamente.");
            });
    });

    const updateForm = document.querySelector('#update_odontologo_form');
    updateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const updatedData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#odontologo_nombre').value,
            apellido: document.querySelector('#odontologo_apellido').value,
            matricula: document.querySelector('#odontologo_matricula').value
        };

        const putSettings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedData)
        };

        fetch(`${url}/${updatedData.id}`, putSettings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontólogo actualizado correctamente</div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error al actualizar el odontólogo. Intente nuevamente.</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                console.error('Error:', error);
            });
    });
});
