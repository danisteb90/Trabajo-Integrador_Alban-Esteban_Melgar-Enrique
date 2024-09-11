window.addEventListener('load', function () {
    (function(){

        //con fetch invocamos a la API de peliculas con el método GET
        //nos devolverá un JSON con una colección de peliculas
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                //recorremos la colección de peliculas del JSON
                console.log(data);
                for(paciente of data){
                    console.log(data);
                    //por cada pelicula armaremos una fila de la tabla
                    //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
                    var table = document.getElementById("pacienteTable");
                    var pacienteRow =table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    //armamos cada columna de la fila
                    //como primer columna pondremos el boton modificar
                    //luego los datos de la pelicula
                    //como ultima columna el boton eliminar
                    pacienteRow.innerHTML =
                        '<td class="td_id">' +
                        paciente.id +
                        "</td>" +
                        '<td class="td_nombre">' +
                        paciente.nombre.toUpperCase() +
                        "</td>" +
                        '<td class="td_apellido">' +
                        paciente.apellido.toUpperCase() +
                        "</td>" +
                        '<td class="td_dni">' +
                        paciente.dni +
                        "</td>" +
                        '<td class="td_fechaAlta">' +
                        paciente.fechaAlta +
                        "</td>" +
                        '<td class="td_direccion">' +
                        paciente.domicilio.calle +
                        " " +
                        paciente.domicilio.numero +
                        ", " +
                        paciente.domicilio.localidad +
                        ", " +
                        paciente.domicilio.provincia +
                        "</td>";
                };

            })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/listarPacientes.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })


})