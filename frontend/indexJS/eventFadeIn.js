'use strict';
const eventLeft = document.querySelector('.event__left'),
  leftImage = eventLeft.querySelector('.event__left img');
const furnitureFirst = document.querySelectorAll('.right__furnitureF');


const options = { threshold: 0.8 };

const callback = (entries, observer) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      eventLeft.classList.add('event__show');
      // furnitureFirst.classList.add('event__show');
      // furnitureSecond.classList.add('event__show');
      for(let value of furnitureFirst){
        value.classList.add('event__show');
      }
      observer.unobserve(entry.target);
    }
  });
};
let observer = new IntersectionObserver(callback, options);
observer.observe(eventLeft, furnitureFirst);
// , furnitureSecond