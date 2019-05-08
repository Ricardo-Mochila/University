function criarArray(n) 
{
    var arr = [n];
    for(var i = 0; i < n; i++){
        arr[i] = Math.floor(Math.random(0,1)*100);
    }
    return arr;
    
}

function average(arr){
    var av = 0;
    var len = arr.length;
    for(var i = 0; i < len; i++){
        av = av + arr[i];
    }
    return av/len;
}

function stdDeviation(){
     
}

function main(){

    var array = criarArray(100);
    document.write("Array size= " + array.length);
    document.write("<br> Midium = "+average(array));
    console.log(array);
    console.log(array.length)
    console.log(average(array));
    
}
console.log(main());


