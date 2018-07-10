function null() {}

function toF(x) { 
    return x + 10;
}

function map(list, fn) {
    aux = null();

    while (list != null()) {
        aux = consWvp(fn(head(list)), aux);
        list = tail(list);
    }

    list = null();
    while (aux != null()) {
        list = consWvp(head(aux), list);
        aux = tail(aux);
    }
    
    return list;
}

function makeList(numElements) {
    cur = null();

    i = 0;
    while (i < numElements) {
        cur = consWvp(1, cur);
        i = i + 1;
    }

    return cur;
}

function main() {
    listLength = num(readln());
    reps = num(readln());

    list = makeList(listLength);

    // warmup
    i = 0;
    while (i < 3) {
        map(list, toF);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while (i < reps) {
        gc();
        beginTime = nanoTime();
        mappedList = map(list, toF);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}
