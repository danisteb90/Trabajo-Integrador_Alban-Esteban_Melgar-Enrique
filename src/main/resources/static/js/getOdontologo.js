window.addEventListener('load', function () {

    // Al cargar la página, obtener la lista de odontólogos y llenar el dropdown
    const url = '/odontologos';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologoSelect = document.querySelector('#odontologo_select');
            // Llenar el dropdown con los odontólogos obtenidos
            data.forEach(odontologo => {
                let option = document.createElement('option');
                option.value = odontologo.id;
                option.text = `${odontologo.nombre} ${odontologo.apellido}`;
                odontologoSelect.appendChild(option);
            });
        });

    // Al enviar el formulario, mostrar la información del odontólogo seleccionado
    const formulario = document.querySelector('#search_odontologo_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#odontologo_select').value;
        if (!selectedId) {
            alert("Seleccione un odontólogo válido.");
            return;
        }

        // Obtener la información del odontólogo seleccionado
        fetch(`${url}/${selectedId}`, settings)
            .then(response => response.json())
            .then(odontologo => {
                // Mostrar la información del odontólogo
                document.querySelector('#odontologo_id').innerText = odontologo.id;
                document.querySelector('#odontologo_nombre').innerText = odontologo.nombre;
                document.querySelector('#odontologo_apellido').innerText = odontologo.apellido;
                document.querySelector('#odontologo_matricula').innerText = odontologo.matricula;

                // Mostrar la sección de información
                document.querySelector('#odontologo_info').style.display = "block";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Error al obtener la información del odontólogo. Intente nuevamente.");
            });
    });
});
