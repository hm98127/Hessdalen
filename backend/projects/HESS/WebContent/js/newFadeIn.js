'use strict';

const newContainer = document.querySelector('.new__container');
const newBoxFirst = document.querySelector('.new__boxf');
const topBox = document.querySelector('.top__box');
const bottomBox = document.querySelector('.bottom__box');
const newBoxSecond = document.querySelector('.new__boxs');

const view = { threshold: 0.6 };

const play = (entries, observer) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      newBoxFirst.classList.add('event__show');
      setInterval(() => {
        topBox.classList.add('event__show');
      }, 300);
      setInterval(() => {
        bottomBox.classList.add('event__show');
      }, 300);
      setInterval(() => {
        newBoxSecond.classList.add('event__show');
      }, 600);

      observer.unobserve(entry.target);
    }
  });
};

const newObserver = new IntersectionObserver(play, view);
newObserver.observe(newContainer);
