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


    const formulario = document.querySelector('#search_odontologo_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let selectedId = document.querySelector('#odontologo_select').value;
        if (!selectedId) {
            alert("Seleccione un odontólogo válido.");
            return;
        }


        fetch(`${url}/${selectedId}`, settings)
            .then(response => response.json())
            .then(odontologo => {

                document.querySelector('#odontologo_id').innerText = odontologo.id;
                document.querySelector('#odontologo_nombre').innerText = odontologo.nombre;
                document.querySelector('#odontologo_apellido').innerText = odontologo.apellido;
                document.querySelector('#odontologo_matricula').innerText = odontologo.matricula;


                document.querySelector('#odontologo_info').style.display = "block";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Error al obtener la información del odontólogo. Intente nuevamente.");
            });
    });
});
