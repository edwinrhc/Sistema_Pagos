// Variables globales
const idParent = $("#idParent").val();

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
        <input type="hidden" name="idParent" value="${idParent}"> <!-- Campo oculto para idProcesoJudiciales -->
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




function cerrarModal() {
    $('#modalParents').addClass('hidden');
}