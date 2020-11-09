const rightBtn = document.querySelector('.right__btn');
const leftBtn = document.querySelector('.left__btn');
const slideList = document.querySelector('.slide_list');
const currentSlide = document.querySelectorAll('.current__slide');
const slideWidth = 400;
const slideSpeed = 300;
const slideLen = currentSlide.length;
const startNum = 0;

slideList.style.width = slideWidth * (slideLen + 2) + 'px';
// first Last child 추가로 800px 추가  2800 px

let firstChild = slideList.firstElementChild;
let lastChild = slideList.lastElementChild;
let clonedFirst = firstChild.cloneNode(true);
let clonedLast = lastChild.cloneNode(true);

// first last child 복사

slideList.appendChild(clonedFirst); // list맨끝에 firstChild 추가
// slideList.insertBefore(clonedLast, slideList.firstElementChild); //lastChild 를 firstchild자리에 놓는다 .

slideList.style.transform =
  'translate3d(-' + slideWidth * (startNum + 1) + 'px, 0px, 0px)'; //현재 slidelist는 -400만큼 이동  그래야 first last 가 안보임

let curIndex = startNum; // current slide index (except copied slide)   현재 인덱스 첫번째 인덱스
let curSlide = currentSlide[curIndex]; // current slide dom       현재 슬라이드 인덱스 번호로

function rightHandle() {
  if (curIndex <= slideLen - 1) {
    slideList.style.transition = slideSpeed + 'ms'; //speed = 300ms
    slideList.style.transform =
      'translate3d(-' + slideWidth * (curIndex + 2) + 'px, 0px, 0px)'; // curindex 2부터 시작 2 3 4 5 6
  }

  if (curIndex === slideLen - 3) {
    setTimeout(() => {
      slideList.style.transition = '0ms';
      slideList.style.transform =
        'translate3d(-' + slideWidth + 'px, 0px, 0px)';
    }, slideSpeed);
    curIndex = -1;
  }
  curSlide = currentSlide[++curIndex];
}

leftBtn.addEventListener('click', function () {
  if (curIndex >= 0) {
    slideList.style.transition = slideSpeed + 'ms';
    slideList.style.transform =
      'translate3d(-' + slideWidth * curIndex + 'px, 0px, 0px)';
  }
  if (curIndex === 0) {
    setTimeout(function () {
      slideList.style.transition = '0ms';
      slideList.style.transform =
        'translate3d(-' + slideWidth * slideLen - 3 + 'px, 0px, 0px)';
    }, slideSpeed);
    curIndex = slideLen;
  }
  curSlide = currentSlide[--curIndex];
});

function init() {
  rightBtn.addEventListener('click', rightHandle);
}
init();
