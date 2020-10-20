const categoryBtn = document.querySelector('.category');
const menuList = document.querySelector('.menu__list');
const categoryClick = document.querySelector('.category__click');
const body = document.querySelector('body');
const logoTitle = document.querySelector('.logo__title a');

function clickHandle() {
  menuList.classList.add('categoryShow');
  categoryClick.style.display = 'none';
  body.style.backgroundColor = 'black';
  logoTitle.style.color = '#f8f8f8';
}

function init() {
  categoryBtn.addEventListener('click', clickHandle);
}
init();
