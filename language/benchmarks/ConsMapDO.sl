function swap(x) { 
    if (x == 1) { 
        return 0; 
    } 
    else { 
        return 1;
    }
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

    i = 1;
    while (i <= numElements) {
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
    warmupReps = reps;
    if (reps > 100) {
        warmupReps = 100;
    }
    i = 0;
    while (i < warmupReps) {
        map(list, swap);
        i = i + 1;
    }

    map(list, swap);

    i = 0;
    while (i < reps) {
        beginTime = nanoTime();
        mappedList = map(list, swap);
        endTime = nanoTime();
        result = endTime - beginTime;
        println(i + ": " + result);
        i = i + 1;
    }
}
