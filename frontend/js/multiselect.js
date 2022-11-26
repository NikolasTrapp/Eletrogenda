function configurateDropdown() {
    const selectBtn = document.querySelector(".select-btn"),
        items = document.querySelectorAll(".item");

    selectBtn.addEventListener("click", () => {
        selectBtn.classList.toggle("open");
    });

    items.forEach(item => {
        item.addEventListener("click", () => {
            item.classList.toggle("checked");

            let checked = document.querySelectorAll(".checked"),
                btnText = document.querySelector(".btn-text");

            if (checked && checked.length > 0) {
                btnText.innerText = `${checked.length} Selected`;
            }
            else {
                btnText.innerText = "Select Language";
            }
        });
    });
}

document.querySelectorAll('#nav-tab>[data-bs-toggle="tab"]').forEach(el => {
    el.addEventListener('shown.bs.tab', () => {
        const target = el.getAttribute('data-bs-target');
        const scrollElem = document.querySelector(`${target} [data-bs-spy="scroll"]`);
        bootstrap.ScrollSpy.getOrCreateInstance(scrollElem).refresh();
    });
});