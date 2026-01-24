document.addEventListener("DOMContentLoaded", () => {

  const slides = document.querySelector(".slides");
  const slideItems = document.querySelectorAll(".slide");
  const nextBtn = document.querySelector(".next");
  const prevBtn = document.querySelector(".prev");

  let index = 0;
  const totalSlides = slideItems.length;

  function showSlide(i) {
    if (i >= totalSlides) index = 0;
    if (i < 0) index = totalSlides - 1;
    slides.style.transform = `translateX(${-index * 100}%)`;
  }

  nextBtn.addEventListener("click", () => {
    index++;
    showSlide(index);
  });

  prevBtn.addEventListener("click", () => {
    index--;
    showSlide(index);
  });

  // Auto slide every 5 seconds
  setInterval(() => {
    index++;
    showSlide(index);
  }, 5000);

});
