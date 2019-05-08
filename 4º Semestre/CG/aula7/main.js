const user_score = document.getElementById("user1");
const user_score2 = document.getElementById("user2");
const ball = document.getElementById("ball");
const bar1 = document.getElementById("bar1");
const bar2 = document.getElementById("bar2");
const width = document.getElementById("svg").getAttribute("width");
const height = document.getElementById("svg").getAttribute("height");

document.addEventListener('keydown', function (event) {
    if (event.key == 's') {
        yb1[0] += 20;
        yb1[1] += 20;

        bar1.setAttribute("y1", yb1[0]);
        bar1.setAttribute("y2", yb1[1]);
        console.log(yb1);
    }
    if (event.key == 'w') {
        yb1[0] -= 20;
        yb1[1] -= 20;

        bar1.setAttribute("y1", yb1[0]);
        bar1.setAttribute("y2", yb1[1]);
    }
    if (event.keyCode == 40) {
        yb2[0] += 20;
        yb2[1] += 20;

        bar2.setAttribute("y1", yb2[0]);
        bar2.setAttribute("y2", yb2[1]);
        console.log(yb1);
    }
    if (event.keyCode == 38) {
        yb2[0] -= 20;
        yb2[1] -= 20;

        bar2.setAttribute("y1", yb2[0]);
        bar2.setAttribute("y2", yb2[1]);
        console.log(yb2);
    }

    if (event.keyCode == 32) {
        reset();
    }
});

bar1.onkeydown = leftBarMove;
let last_ts = performance.now();
var xspeed = 8;
var yspeed = 1;
var x = width / 2;
var y = height / 2;
var score1 = 0;
var score2 = 0;

var xb1 = bar1.getAttribute("x1");
var yb1 = [parseInt(bar1.getAttribute("y1")), parseInt(bar1.getAttribute("y2"))];

var xb2 = bar2.getAttribute("x1");
var yb2 = [parseInt(bar2.getAttribute("y1")), parseInt(bar2.getAttribute("y2"))];

function leftBarHit() {
    if (x == xb1 && y >= yb1[0] && y <= yb1[1]) {
        if (y < (yb1[0] + yb1[1]) / 2) {
            xspeed *= -1;
            yspeed = 1;
        }
        if (y > (yb1[0] + yb1[1]) / 2) {
            yspeed = 1;
            xspeed *= -1;
            yspeed *= -1;
        }
        if (y == (yb1[0] + yb1[1]) / 2) {
            xspeed *= -1;
            yspeed *= 0;
        }

    }
}


function leftBarMove() {
    yb1[0] += 10;
    yb1[1] += 10;

    bar1.setAttribute("y1", yb1);
    bar1.setAttribute("y1", yb2);

}

function rightBarHit() {
    if (x == xb2 && y >= yb2[0] && y <= yb2[1]) {
        if (y < (yb2[0] + yb2[1]) / 2) {
            xspeed *= -1;
            yspeed = 1;
        }
        if (y > (yb2[0] + yb2[1]) / 2) {
            xspeed *= -1;
            yspeed = 1;
            yspeed *= -1;
        }
        if (y == (yb2[0] + yb2[1]) / 2) {
            xspeed *= -1;
            yspeed *= 0;
        }

    }
}

function jogo() {
    x = x + xspeed;
    ball.setAttribute("cx", x);
    y = y + yspeed;
    ball.setAttribute("cy", y);
}

function reset() {
    x = width / 2;
    y = height / 2;
}

function hitBorder() {

    if (x <= 0) {
        score2 += 1;
        user_score2.textContent = score2;
        reset();
    }
    if (x >= width) {
        score1 += 1;
        user_score.textContent = score1;
        reset();

    } else if (y < 0 || y > parseInt(height)) {
        yspeed *= -1;
    }
}


animation_step = function () {
    //var dt = timestamp - last_ts
    if (user_score.textContent <= 5 && user_score2.textContent <= 5) {
        jogo();
        hitBorder();
        leftBarHit();
        rightBarHit();
    }
    requestAnimationFrame(animation_step);
}

function main() {
    requestAnimationFrame(animation_step);
}
main();