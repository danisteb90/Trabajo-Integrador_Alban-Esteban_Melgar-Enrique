window.addEventListener('load', function () {
    (function(){


        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
            .then(response => response.json())
            .then(data => {

                console.log(data);
                for(odontologo of data){
                    console.log(data);

                    var table = document.getElementById("odontologoTable");
                    var odontologoRow =table.insertRow();
                    let tr_id = 'tr_' + odontologo.id;
                    odontologoRow.id = tr_id;

                    odontologoRow.innerHTML = '<td class=\"td_id\">' + odontologo.id + '</td>' +
                        '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_matricula\">' + odontologo.matricula + '</td>';

                };

            })
    })

    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/listarOdontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })


})