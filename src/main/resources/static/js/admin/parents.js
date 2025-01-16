// Variables globales
const idParent = $("#idParent").val();

const regexTelefono = /^[0-9]{7,15}$/; // Solo números entre 7 y 15 dígitos
const regexNoConsecutivos = /^(?!.*(\d)\1{2,}).*$/; // Evitar números consecutivos (123456)
const regexNombre = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/; // Solo letras y espacios
const regexEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/; // Validar formato de correo


function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

function modalRegistroPadres() {
    console.log("Abriendo el formularioo")
    $.ajax({
        url: '/api/parents/get', type: 'GET', dataType: 'json', success: function (data) {
            // Genera el formulario vacío en el modal
            let content = `
 <form id="parentsNew" style="max-width: 400px;">
    <h4 class="text-2xl font-bold mb-6">Nuevo Registro - Padres</h4>
    
<div class="mb-4">
    <!-- Select para Tipo de Documento -->
    <label for="tipo_doc" class="block font-semibold">Tipo de Documento:</label>
    <select id="tipo_doc" name="tipo_doc" 
            class="w-full p-2 border rounded" 
            required>
        <option value="" disabled selected>Seleccione un tipo de documento</option>
        <option value="DNI">DNI</option>
        <option value="C.Extr">Carnet de Extranjería</option>
    </select>
</div>

    <div class="mb-4">
        <label for="num_doc" class="block font-semibold">Numero de documento:</label>
        <input id="num_doc" name="num_doc" class="w-full p-2 border rounded" oninput="upperCase(this)"/>
    </div>
    
    
    <div class="mb-4">
      
        <label for="nombre" class="block font-semibold">Nombre:</label>
        <input id="nombre" name="nombre" class="w-full p-2 border rounded"/>
    </div>
    
    <div class="mb-4">
        <label for="apellido_paterno" class="block font-semibold">Apellido Paterno:</label>
        <input id="apellido_paterno" name="apellido_paterno" class="w-full p-2 border rounded"/>
    </div>
    
    <div class="mb-4">
        <label for="apellido_materno" class="block font-semibold">Apellido Materno:</label>
        <input id="apellido_materno" name="apellido_materno" class="w-full p-2 border rounded"/>
    </div>

    <div class="mb-4">
        <label for="email" class="block font-semibold">Email:</label>
        <input id="email" name="email" class="w-full p-2 border rounded"/>
    </div>

    <div class="mb-4">
        <label for="telefono" class="block font-semibold">Teléfono:</label>
        <input id="telefono" name="telefono" class="w-full p-2 border rounded"/>
    </div>

    <div class="flex justify-end mt-4">
        <button type="button" onclick="guardarPadre()" class="bg-blue-600 text-white px-4 py-2 rounded-md mr-2">Guardar</button>
        <button type="button" onclick="cerrarModal()" class="bg-gray-500 text-white px-4 py-2 rounded-md">Cerrar</button>
    </div>
</form>

            `;
            // Genera el formulario vacío en el modal
            $('#modalParentsContent').html(content); // Inserta el formulario vacío en el modal
            $('#modalParents').removeClass('hidden'); // Muestra el modal
        }, error: function (xhr, status, error) {
            alert("Error al cargar el formulario.");
        }

    })

}


function guardarPadre() {
    const procesoRegistroPadres = {
        tipo_doc: $('#tipo_doc').val(),
        num_doc: limpiarEspacios($('#num_doc').val()),
        nombre: limpiarEspacios($('#nombre').val()),
        apellido_paterno: limpiarEspacios($('#apellido_paterno').val()),
        apellido_materno: limpiarEspacios($('#apellido_materno').val()),
        email: $('#email').val().trim(),
        telefono: $('#telefono').val().trim()
    };

    /* const nombresCampos = {
          tipo_doc: 'El tipo de documento es obligatorio',
          num_doc: 'El numero de documento es obligatorio',
          nombre: 'El nombre es obligatorio',
          apellido_paterno: 'El apellido paterno es obligatorio',
          apellido_materno: 'El apellido materno es obligatorio',
          email: 'El email es obligatorio',
          telefono: 'El telefono es obligatorio'
      };

      const limitesCampos = {
          nombre: 50,
          apellido_paterno: 50,
          apellido_materno: 50,
          email: 50,
          telefono: 9
      };

      for(const  [campo,valor] of Object.entries(procesoRegistroPadres)) {
          if (!valor.trim()) {
              Swal.fire({
                  icon: 'warning',
                  title: 'Campo obligatorio',
                  text: `${nombresCampos[campo]}`,
                  confirmButtonText: 'Entendido'
              });
              return;
          }
          // Validación de longitud máxima
          if (limitesCampos[campo] && valor.length > limitesCampos[campo]) {
              Swal.fire({
                  icon: 'error',
                  title: 'Campo demasiado largo',
                  text: `El campo ${campo} no debe exceder los ${limitesCampos[campo]} caracteres.`,
                  confirmButtonText: 'Entendido'
              });
              return;
          }

          //Validación de formatos email
          if(campo === 'email' && !regexEmail.test(valor)){
              Swal.fire({
                  icon:'error',
                  title: 'Correo inválido',
                  text: 'Por favor, ingrese un correo electrónico válido',
                  confirmButtonText: 'Ententido',
              });
              return;
          }

          // Validación de formato y longitud de teléfono
          if (campo === 'telefono') {
              if (!/^\d+$/.test(valor)) {
                  Swal.fire({
                      icon: 'error',
                      title: 'Teléfono inválido',
                      text: 'El teléfono no debe contener letras ni caracteres especiales.',
                      confirmButtonText: 'Entendido',
                  });
                  return;
              }
              if (valor.length !== limitesCampos[campo]) {
                  Swal.fire({
                      icon: 'error',
                      title: 'Teléfono inválido',
                      text: `El teléfono debe tener exactamente ${limitesCampos[campo]} dígitos.`,
                      confirmButtonText: 'Entendido',
                  });
                  return;
              }
          }

          if ((campo === 'nombre' || campo === 'apellido_paterno' || campo === 'apellido_materno') && !regexNombre.test(valor)) {

              const campoTexto = {
                  nombre: 'Nombre',
                  apellido_paterno: 'Apellido Paterno',
                  apellido_materno: 'Apellido Materno'
              }

              Swal.fire({
                  icon: 'error',
                  title: `${campoTexto[campo]} inválido`,
                  text: `El campo ${campoTexto[campo]} no debe contener caracteres especiales ni números.`,
                  confirmButtonText: 'Entendido',
              });
              return;
          }

      }

      // Validación específica para tipo de documento y número de documento
    const tipoDoc = procesoRegistroPadres.tipo_doc;
    const numDoc = procesoRegistroPadres.num_doc;

    if(tipoDoc === 'DNI'){
        if (!/^\d+$/.test(numDoc)) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El DNI debe contener solo números.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
        if (numDoc.length !== 8) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El DNI debe tener exactamente 8 dígitos.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
    }else if (tipoDoc === 'C.Extr') {
        if (!/^[a-zA-Z0-9]+$/.test(numDoc)) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El Carnet de Extranjería debe contener solo letras y números.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
        if (numDoc.length < 3 || numDoc.length > 12) { // Ajusta los límites según tus necesidades
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El Carnet de Extranjería debe tener entre 3 y 12 caracteres.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
    }*/


    if (!validarDatosPadres(procesoRegistroPadres)) {
        return;
    }

    $.ajax({
        url: '/api/parents/create',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(procesoRegistroPadres),
        success: function (response) {
            Swal.fire({
                icon: 'success',
                title: 'Registro exitoso',
                text: response,
                confirmButtonText: 'Perfecto'
            }).then(() => {
                cerrarModal();
                location.reload();

            })
        },
        error: function (xhr, status, error) {
            console.log("Error: ", xhr.responseText);
            // Manejo de errores
            Swal.fire({
                icon: 'error',
                title: 'Error al registrar',
                text: xhr.responseText || 'Hubo un problema al intentar registrar. Inténtelo nuevamente.',
                confirmButtonText: 'Entendido'
            });
        }
    });


}


function abrirModalParentEdit(idParent) {

    //Capturamos los datos
    $.ajax({
        url: '/api/parents/get/' + idParent, type: 'GET', dataType: 'json', success: function (data) {
            // Genera el formulario vacío en el modal
            let content = `
        <form id="parentsUpdate" style="max-width: 400px;">
          <h4 class="text-2xl font-bold mb-6">Editar Registro - Padres</h4>
            
        <div class="mb-4">
            <!-- Select para Tipo de Documento -->
            <input type="hidden" name="idParent" value="${data.idParent}">
            <label for="tipo_doc" class="block font-semibold">Tipo de Documento:</label>
            <select id="tipo_doc" name="tipo_doc" 
                    class="w-full p-2 border rounded" 
                    required>
                <option value="" disabled selected>Seleccione un tipo de documento</option>
             <option value="DNI" ${data.tipo_doc === "DNI" ? "selected" : ""}>DNI</option>
                <option value="C.Extr" ${data.tipo_doc === "C.Extr" ? "selected" : ""}>Carnet de Extranjería</option>
                <option value="Pasaporte" ${data.tipo_doc === "Pasaporte" ? "selected" : ""}>Pasaporte</option>
            </select>
        </div>
        
            <div class="mb-4">
                <label for="num_doc" class="block font-semibold">Numero de documento:</label>
                <input id="num_doc" name="num_doc" class="w-full p-2 border rounded" oninput="upperCase(this)" value="${data.num_doc || ''}"/>
            </div>
            
            
            <div class="mb-4">
              
                <label for="nombre" class="block font-semibold">Nombre:</label>
                <input id="nombre" name="nombre" class="w-full p-2 border rounded"  value="${data.nombre || ''}" />
            </div>
            
            <div class="mb-4">
                <label for="apellido_paterno" class="block font-semibold">Apellido Paterno:</label>
                <input id="apellido_paterno" name="apellido_paterno" class="w-full p-2 border rounded"  value="${data.apellido_paterno || ''}"/>
            </div>
            
            <div class="mb-4">
                <label for="apellido_materno" class="block font-semibold">Apellido Materno:</label>
                <input id="apellido_materno" name="apellido_materno" class="w-full p-2 border rounded" value="${data.apellido_materno || ''}"/>
            </div>
        
            <div class="mb-4">
                <label for="email" class="block font-semibold">Email:</label>
                <input id="email" name="email" class="w-full p-2 border rounded" value="${data.email || ''}"/>
            </div>
        
            <div class="mb-4">
                <label for="telefono" class="block font-semibold">Teléfono:</label>
                <input id="telefono" name="telefono" class="w-full p-2 border rounded" value="${data.telefono || ''}"/>
            </div>
        
            <div class="flex justify-end mt-4">
                <button type="button" onclick="guardarPadre()" class="bg-blue-600 text-white px-4 py-2 rounded-md mr-2">Guardar</button>
                <button type="button" onclick="cerrarModal()" class="bg-gray-500 text-white px-4 py-2 rounded-md">Cerrar</button>
            </div>
        </form>

            `;
            // Genera el formulario vacío en el modal
            $('#modalParentsContent').html(content); // Inserta el formulario vacío en el modal
            $('#modalParents').removeClass('hidden'); // Muestra el modal
        }, error: function (xhr, status, error) {
            alert("Error al cargar el formulario.");

        }
    })

}


function updatePadre() {
    const parentsData = {
        idParent: $('#idParent').val(),
        tipo_doc: $('#tipo_doc').val().trim(),
        num_doc: limpiarEspacios($('#num_doc').val()),
        nombre: limpiarEspacios($('#nombre').val()),
        apellido_paterno: limpiarEspacios($('#apellido_paterno').val()),
        apellido_materno: limpiarEspacios($('#apellido_materno').val()),
        email: $('#email').val().trim(),
        telefono: $('#telefono').val().trim()
    };

    $.ajax({
        url: 'api/parents/update',
        type: 'POST',
        contentType: 'application/json',
        success: function (response) {

        }

    });
}


function limpiarEspacios(valor) {
    return valor.replace(/\s+/g, ' ').trim();
}

function cerrarModal() {
    $('#modalParents').addClass('hidden');
}


/*
function validarDatosPadres(datos) {
    const nombresCampos = {
        tipo_doc: 'El tipo de documento es obligatorio',
        num_doc: 'El numero de documento es obligatorio',
        nombre: 'El nombre es obligatorio',
        apellido_paterno: 'El apellido paterno es obligatorio',
        apellido_materno: 'El apellido materno es obligatorio',
        email: 'El email es obligatorio',
        telefono: 'El telefono es obligatorio'
    };

    const limitesCampos = {
        nombre: 50,
        apellido_paterno: 50,
        apellido_materno: 50,
        email: 50,
        telefono: 9
    };

    for(const  [campo,valor] of Object.entries(datos)) {
        if (!valor.trim()) {
            Swal.fire({
                icon: 'warning',
                title: 'Campo obligatorio',
                text: `${nombresCampos[campo]}`,
                confirmButtonText: 'Entendido'
            });
            return;
        }
        // Validación de longitud máxima
        if (limitesCampos[campo] && valor.length > limitesCampos[campo]) {
            Swal.fire({
                icon: 'error',
                title: 'Campo demasiado largo',
                text: `El campo ${campo} no debe exceder los ${limitesCampos[campo]} caracteres.`,
                confirmButtonText: 'Entendido'
            });
            return;
        }

        //Validación de formatos email
        if(campo === 'email' && !regexEmail.test(valor)){
            Swal.fire({
                icon:'error',
                title: 'Correo inválido',
                text: 'Por favor, ingrese un correo electrónico válido',
                confirmButtonText: 'Ententido',
            });
            return;
        }

        // Validación de formato y longitud de teléfono
        if (campo === 'telefono') {
            if (!/^\d+$/.test(valor)) {
                Swal.fire({
                    icon: 'error',
                    title: 'Teléfono inválido',
                    text: 'El teléfono no debe contener letras ni caracteres especiales.',
                    confirmButtonText: 'Entendido',
                });
                return;
            }
            if (valor.length !== limitesCampos[campo]) {
                Swal.fire({
                    icon: 'error',
                    title: 'Teléfono inválido',
                    text: `El teléfono debe tener exactamente ${limitesCampos[campo]} dígitos.`,
                    confirmButtonText: 'Entendido',
                });
                return;
            }
        }

        if ((campo === 'nombre' || campo === 'apellido_paterno' || campo === 'apellido_materno') && !regexNombre.test(valor)) {

            const campoTexto = {
                nombre: 'Nombre',
                apellido_paterno: 'Apellido Paterno',
                apellido_materno: 'Apellido Materno'
            }

            Swal.fire({
                icon: 'error',
                title: `${campoTexto[campo]} inválido`,
                text: `El campo ${campoTexto[campo]} no debe contener caracteres especiales ni números.`,
                confirmButtonText: 'Entendido',
            });
            return;
        }

    }

    // Validación específica para tipo de documento y número de documento
    const tipoDoc = datos.tipo_doc;
    const numDoc = datos.num_doc;

    if(tipoDoc === 'DNI'){
        if (!/^\d+$/.test(numDoc)) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El DNI debe contener solo números.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
        if (numDoc.length !== 8) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El DNI debe tener exactamente 8 dígitos.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
    }else if (tipoDoc === 'C.Extr') {
        if (!/^[a-zA-Z0-9]+$/.test(numDoc)) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El Carnet de Extranjería debe contener solo letras y números.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
        if (numDoc.length < 3 || numDoc.length > 12) { // Ajusta los límites según tus necesidades
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: 'El Carnet de Extranjería debe tener entre 3 y 12 caracteres.',
                confirmButtonText: 'Entendido'
            });
            return;
        }
    }
    return true;
}
*/

function validarDatosPadres(datos) {
    const nombresCampos = {
        tipo_doc: 'El tipo de documento es obligatorio',
        num_doc: 'El número de documento es obligatorio',
        nombre: 'El nombre es obligatorio',
        apellido_paterno: 'El apellido paterno es obligatorio',
        apellido_materno: 'El apellido materno es obligatorio',
        email: 'El correo electrónico es obligatorio',
        telefono: 'El teléfono es obligatorio'
    };

    const limitesCampos = {
        nombre: 50,
        apellido_paterno: 50,
        apellido_materno: 50,
        email: 50,
        telefono: 9
    };

    const reglasTipoDocumento = {
        DNI: { regex: /^\d+$/, longitud: 8, mensaje: 'El DNI debe tener exactamente 8 dígitos y solo números.' },
        'C.Extr': { regex: /^[a-zA-Z0-9]+$/, longitudMin: 3, longitudMax: 12, mensaje: 'El Carnet de Extranjería debe tener entre 3 y 12 caracteres, solo letras y números.' }
    };

    for (const [campo, valor] of Object.entries(datos)) {
        if (!valor.trim()) {
            Swal.fire({
                icon: 'warning',
                title: 'Campo obligatorio',
                text: `${nombresCampos[campo]}`,
                confirmButtonText: 'Entendido'
            });
            return false;
        }

        if (limitesCampos[campo] && valor.length > limitesCampos[campo]) {
            Swal.fire({
                icon: 'error',
                title: 'Campo demasiado largo',
                text: `El campo ${campo} no debe exceder los ${limitesCampos[campo]} caracteres.`,
                confirmButtonText: 'Entendido'
            });
            return false;
        }

        if (campo === 'email' && !regexEmail.test(valor)) {
            Swal.fire({
                icon: 'error',
                title: 'Correo inválido',
                text: 'Por favor, ingrese un correo electrónico válido.',
                confirmButtonText: 'Entendido'
            });
            return false;
        }

        if (campo === 'telefono') {
            if (!/^\d+$/.test(valor)) {
                Swal.fire({
                    icon: 'error',
                    title: 'Teléfono inválido',
                    text: 'El teléfono no debe contener letras ni caracteres especiales.',
                    confirmButtonText: 'Entendido'
                });
                return false;
            }
            if (valor.length !== limitesCampos[campo]) {
                Swal.fire({
                    icon: 'error',
                    title: 'Teléfono inválido',
                    text: `El teléfono debe tener exactamente ${limitesCampos[campo]} dígitos.`,
                    confirmButtonText: 'Entendido'
                });
                return false;
            }
        }

        if ((campo === 'nombre' || campo === 'apellido_paterno' || campo === 'apellido_materno') && !regexNombre.test(valor)) {
            Swal.fire({
                icon: 'error',
                title: `${campo} inválido`,
                text: `El campo ${campo} no debe contener caracteres especiales ni números.`,
                confirmButtonText: 'Entendido'
            });
            return false;
        }
    }

    const tipoDoc = datos.tipo_doc;
    const numDoc = datos.num_doc;
    const reglas = reglasTipoDocumento[tipoDoc];

    if (reglas) {
        if (!reglas.regex.test(numDoc)) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: reglas.mensaje,
                confirmButtonText: 'Entendido'
            });
            return false;
        }
        if (
            (reglas.longitud && numDoc.length !== reglas.longitud) ||
            (reglas.longitudMin && numDoc.length < reglas.longitudMin) ||
            (reglas.longitudMax && numDoc.length > reglas.longitudMax)
        ) {
            Swal.fire({
                icon: 'error',
                title: 'Número de documento inválido',
                text: reglas.mensaje,
                confirmButtonText: 'Entendido'
            });
            return false;
        }
    }

    return true;
}


function upperCase(input) {
    input.value = input.value.toUpperCase();
}


