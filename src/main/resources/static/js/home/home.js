





// Función para abrir el modal
function openModal() {
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modal-title');
    const modalContent = document.getElementById('modal-content');

}

// Función para cerrar el modal
function closeModal() {
    const modal = document.getElementById('modal');
    modal.classList.add('hidden');
}

// Función para mostrar/ocultar el menú
function toggleMenu() {
    var menu = document.getElementById('userMenu');
    menu.classList.toggle('hidden');
}


function toggleSection(sectionId) {
    const section = document.getElementById(sectionId);
    section.classList.toggle('hidden');
}