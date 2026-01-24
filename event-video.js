const video = document.querySelector(".event-video");

if (video) {
  document.addEventListener("scroll", () => {
    const rect = video.getBoundingClientRect();
    const visible = rect.top < window.innerHeight && rect.bottom > 0;

    if (!visible && !video.paused) {
      video.pause();
    }
  });
}
<script>
  window.addEventListener("load", () => {
    const video = document.querySelector(".event-video");
    if (video) {
      video.play().catch(() => {});
    }
  });
</script>

