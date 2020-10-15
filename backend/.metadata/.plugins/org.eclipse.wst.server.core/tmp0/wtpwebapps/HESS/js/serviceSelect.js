let serviceEvent = document.querySelector('.service__event');
let serviceNotice = document.querySelector('.service__notice');

const color = {
  true: '#283541',
  false: '#E2CDCD',
};

function eventHandle() {
  serviceNotice.style.backgroundColor = color.false;
  serviceEvent.style.backgroundColor = color.true;
}

function noticeHandle() {
  serviceNotice.style.backgroundColor = color.true;
  serviceEvent.style.backgroundColor = color.false;
}

function init() {
  serviceEvent.addEventListener('click', eventHandle);
  serviceNotice.addEventListener('click', noticeHandle);
}
init();
