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


    const deleteForm = document.querySelector('#delete_odontologo_form');
    deleteForm.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#odontologo_select').value;
        if (!selectedId) {
            alert("Seleccione un odontólogo válido.");
            return;
        }

        const deleteSettings = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        };


        fetch(`${url}/${selectedId}`, deleteSettings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al eliminar el odontólogo');
                }

                return response.text().then(text => {
                    return text ? JSON.parse(text) : {};
                });
            })
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontólogo eliminado correctamente</div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";


                let odontologoSelect = document.querySelector('#odontologo_select');
                let optionToRemove = odontologoSelect.querySelector(`option[value="${selectedId}"]`);
                optionToRemove.remove();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error al eliminar el odontólogo. Intente nuevamente.</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                console.error('Error:', error);
            });
    });
});
