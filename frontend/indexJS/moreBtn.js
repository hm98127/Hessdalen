'use strict';

const moreBtn = document.querySelector('.more__btn p');

function moreHandle(event) {
  const target = event.target;
  const link = target.dataset.link;
  if (link === null) {
    return;
  }

  const scrollInto = document.querySelector(link);

  scrollInto.scrollIntoView({ behavior: 'smooth' });
}

function init() {
  moreBtn.addEventListener('click', moreHandle);
}
init();
