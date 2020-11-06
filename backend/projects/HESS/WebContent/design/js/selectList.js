'use strict';

const productsList = document.querySelector('.products__list');
const bedProducts = document.querySelector('.bed__products');
const productsLeftTitle = document.querySelector('.products__Ltitle');

function listhandle(evnet) {
  const target = evnet.target;
  const link = target.dataset.link;
  if (link == null) {
    return;
  }
  const select = document.querySelector(link);
  const currentSelect = document.querySelector('.productsShow');
  if (currentSelect) {
    currentSelect.classList.remove('productsShow');
    select.classList.add('productsShow');
  } else {
    select.classList.add('productsShow');
  }
}

function init() {
  productsList.addEventListener('click', listhandle);
}
init();
