const toggle = document.querySelector("#header .toggle-button");
const collapse = document.querySelector("#header .collapse");

toggle.addEventListener("click", () => {
    collapse.classList.toggle("active");
});


// Modal
$('#myModal').on('shown.bs.modal', function () {
  $('#myInput').trigger('focus')
})

