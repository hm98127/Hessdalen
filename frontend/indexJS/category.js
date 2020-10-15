const categoryBtn = document.querySelector('.category');
const navbarMenu = document.querySelector('.navbar__menu');
const categoryClick = document.querySelector('.category__click');
const body = document.querySelector('body');

function clickHandle() {
  navbarMenu.classList.add('categoryShow');
  categoryClick.style.display = 'none';
  body.style.backgroundColor = 'black';
}

function init() {
  categoryBtn.addEventListener('click', clickHandle);
}
init();
