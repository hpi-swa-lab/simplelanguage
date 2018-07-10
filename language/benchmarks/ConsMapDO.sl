function toZero(x) { 
    return 0;
}

function map(list, fn) {
    aux = 0;

    while (list != 0) {
        aux = consDo(fn(list.head), aux);
        list = list.tail;
    }

    list = 0;
    while (aux != 0) {
        list = consDo(aux.head, list);
        aux = aux.tail;
    }
    
    return list;
}

function makeList(numElements) {
    cur = 0;

    i = 0;
    while (i < numElements) {
        cur = consDo(1, cur);
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
        map(list, toZero);
        i = i + 1;
    }

    i = 0;
    sum = 0;
    while (i < reps) {
        gc();
        beginTime = nanoTime();
        mappedList = map(list, toZero);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
        sum = sum + result;
    }
    println(sum / reps);
}
