const slider = document.getElementById("slider");
const scrollModal = document.querySelector("#carruselModal")

dragSlider(slider)
dragSlider(scrollModal)

function dragSlider(dom) {
    let isDown = false;
    let startX;
    let scrollLeft;
    dom.addEventListener('mousedown', (e) => {
        isDown = true;
        dom.classList.add('active');
        startX = e.pageX - dom.offsetLeft;
        scrollLeft = dom.scrollLeft;
    });

    dom.addEventListener('mouseleave', () => {
        isDown = false;
        dom.classList.remove('active');
    });
    dom.addEventListener('mouseup', () => {
        isDown = false;
        dom.classList.remove('active');
    });
    dom.addEventListener('mousemove', (e) => {
        if (!isDown) return;
        e.preventDefault();
        const x = e.pageX - dom.offsetLeft;
        const walk = (x - startX) * 3; //scroll-fast
        dom.scrollLeft = scrollLeft - walk;

    });
    window.addEventListener("wheel", (e) => {
        if (e.deltaY > 0) {
            dom.scrollLeft += 100
        } else {
            dom.scrollLeft -= 100
        }
    });
}
