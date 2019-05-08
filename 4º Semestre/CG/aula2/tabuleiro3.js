function main()
{
    ctx.beginPath();
        ctx.moveTo(80, 0);
        ctx.quadraticCurveTo(-50, 75, 80, 150);
        ctx.quadraticCurveTo(0, 75, 80, 0);
        ctx.closePath();

        ctx.fillStyle = "steelblue";
        ctx.fill();
}

main();z